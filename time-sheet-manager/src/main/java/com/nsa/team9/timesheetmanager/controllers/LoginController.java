package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Service
@Controller
public class LoginController {

    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

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

}
