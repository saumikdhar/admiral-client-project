package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.domain.ConfirmationToken;
import com.nsa.team9.timesheetmanager.domain.EmailMessage;
import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.ConfirmationTokenRepository;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
@SessionAttributes({"token", "emailAddress"})
@Service
@Controller
public class LoginController {

    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);
    private final PasswordEncoder encoder;
    private LoginSearchImpl loginSearch;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailSenderService;

    public LoginController(PasswordEncoder encoder, LoginSearchImpl loginSearch, ConfirmationTokenRepository tokenRepository,
                           EmailService emailSenderService) {
        this.encoder = encoder;
        this.loginSearch = loginSearch;
        confirmationTokenRepository = tokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.password}")
    private String password;


    @GetMapping("/loginSuccess")
    public String successfulLogin(Authentication authentication) {
        //try if someone is logged in
        try {
            //get user
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

            boolean isContractor = false;
            boolean isManager = false;
            boolean isAdmin = false;
            // identify the type of user
            if (principal.getUser().getAccessLevel().equals(0)) {
                isContractor = true;
            } else if (principal.getUser().getAccessLevel().equals(1)) {
                isManager = true;
            } else if (principal.getUser().getAccessLevel().equals(2)) {
                isAdmin = true;
            }

            //redirect depending on the type of user
            if (isContractor) {
                return "redirect:/TimeSheetForm";
            } else if (isManager) {
                return "redirect:/manager";
            } else if (isAdmin) {

                return "redirect:/admin/timesheets";
            } else {
                String error = new IllegalStateException().getMessage();
                LOG.debug(error);
                return "redirect:/login";
            }
        }catch (NullPointerException n) {
            //if not logged in redirect to login page
            return "redirect:/login";
        }
    }

//    https://stackoverflow.com/questions/22557741/logout-link-with-spring-and-thymeleaf
//    by Tonechas 3/4/17, accessed at 10/12/2019
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        //get if authentication is set - someone is logged in
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if not null ,someone is logged in, log out
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("user/change-password")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("changePassword", new ChangePasswordForm());

        return "changePassword";
    }

    @PostMapping("user/change-password/confirm")
    public String ChangePassword(Model model, @ModelAttribute("changePassword") @Valid ChangePasswordForm changePassword, BindingResult bindingResult , Authentication authentication) {

        //Get the logged in user
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        boolean result = encoder.matches(changePassword.getCurrentPassword(), principal.getUser().getPassword());

        if (!result){
            bindingResult.rejectValue("currentPassword", "error.ChangePasswordForm", "Current password did not match");
        }

        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.ChangePasswordForm", "Confirm password did not match new password");
        }

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "changePassword";
        }
        loginSearch.updateUserPassword(principal.getUser().getId(), encoder.encode(changePassword.getNewPassword()));
        return "passwordChangeConfirmation";
    }

    @GetMapping(value="/forgot-password")
    public String displayResetPassword(Model model) {
        model.addAttribute("forgotPasswordF", new ForgotPasswordForm());
        return "forgotPassword";
    }

    // Receive the address and send an email
    @PostMapping(value="/forgot-password")
    public String forgotUserPassword(@ModelAttribute("forgotPasswordF") @Valid ForgotPasswordForm user, BindingResult bindingResult) throws IOException, MessagingException {

        if (!loginSearch.getLoginByEmail(user.getEmailAddress()).isPresent()){
            bindingResult.rejectValue("emailAddress", "error.ForgotPasswordForm", "The email address you entered doesn't exist in Admiral");
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "forgotPassword";
        }

        Login existingUser = loginSearch.getLoginByEmail(user.getEmailAddress()).get();

        // Create token
        ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

        // Save it
        confirmationTokenRepository.save(confirmationToken);


        // Create the email
        EmailMessage mailMessage = new EmailMessage();
        mailMessage.setTo_address(existingUser.getEmail());
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setBody("A reset password for this account was requested, to complete the password reset process" + "<p> Please click here: "
        + "\n<a href='http://localhost:8000/confirm-reset?token="+confirmationToken.getConfirmationToken() + "'>reset password"+"</a></p>"
                + "<br>"+
                "<p><b>If this wasn't you just ignore this email or delete the email</b></p>");

        // Send the email
        emailSenderService.sendEmail(mailMessage);
        return "successForgotPassword";
    }

    // Endpoint to confirm the token
    @GetMapping(value="/confirm-reset")
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken, Model model) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        model.addAttribute("resetpassword", new ResetPasswordForm());
        model.addAttribute("token", confirmationToken);
        if (token != null) {
            Login user = loginSearch.getLoginByEmail(token.getUser().getEmail()).get();
            modelAndView.addObject("user", user);
            modelAndView.addObject("emailAddress", user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
                modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Endpoint to update a user's password
    @PostMapping(value = "/reset-password")
    public String resetUserPassword(ModelAndView modelAndView, @ModelAttribute("resetpassword") @Valid ResetPasswordForm resetPasswordForm, BindingResult bindingResult, @SessionAttribute("token") String confirmationToken, Login user ) {

        if (!resetPasswordForm.getNewPassword().equals(resetPasswordForm.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "error.ResetPasswordForm", "Confirm password did not match new password");
        }

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "resetPassword";
        }

            // Use email to find user
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        Login tokenUser = loginSearch.getLoginByEmail(token.getUser().getEmail()).get();
            tokenUser.setPassword(encoder.encode(resetPasswordForm.getConfirmPassword()));
            loginSearch.createLogin(tokenUser);
            System.out.println("login saved"+ tokenUser);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        return "successResetPassword";
    }
}
