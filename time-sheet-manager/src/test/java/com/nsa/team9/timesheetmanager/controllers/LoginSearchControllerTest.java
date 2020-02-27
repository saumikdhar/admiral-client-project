package com.nsa.team9.timesheetmanager.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.nsa.team9.timesheetmanager.domain.ConfirmationToken;
import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.ConfirmationTokenRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@Transactional
public class LoginSearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    EmailService emailSenderService;

    @MockBean
    ConfirmationToken confirmationToken;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    /*BAD PATH*/
    @Test
    public void invalidEmailForgotPasswordTwo() throws Exception {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "hbjb@dsds")) // INVALID email input (without the .com or .co.uk etc)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Please provide a valid email address")))
                .andExpect(content().string(Matchers.containsString("The email address you entered doesn&#39;t exist in Admiral")));
        //should return invalid email but is well formed as it contains a string followed by @ followed by string
    }

    /*BAD PATH*/
    @Test
    public void forgotUserPasswordEmailDoesntExist() throws Exception  {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "hbjb@aohsai.com")) // invalid email
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("The email address you entered doesn&#39;t exist in Admiral")));
        //should return email doesn't exist error message
    }

    /*GOOD PATH*/
    @Test
    public void forgotUserPassword() throws Exception  {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(0L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);//generating a token for Daniel's account
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        //test if random uuid is generated with a length of 36

    }

    /*BAD PATH*/
    @Test
    public void invalidResetToken() throws Exception{
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(47L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken); //save the generate token for Daniel

        //get request to confirm reset password page
        mvc.perform(get("/confirm-reset?token=hwh3kjws-j34h34h3-34jhb3j4hl-3bh3f3fi"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Error")));
        //should show an error page if the token is invalid
    }


    /*GOOD PATH*/
    @Test
    public void validResetToken() throws Exception{
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(47L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken); //save the generate token for Daniel

        //get request to confirm reset password page
        mvc.perform(get("/confirm-reset?token="+confirmationToken.getConfirmationToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("urna@pedeultrices.co.uk")));
        //should show the email address if the token is valid
    }

    /*BAD PATH*/
    @Test
    public void ResetUserPasswordMismatch() throws Exception {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(47L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken); //save the generate token for Daniel

        //get request to confirm reset password page
        mvc.perform(get("/confirm-reset?token="+confirmationToken.getConfirmationToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("urna@pedeultrices.co.uk")));
        //should show the email address if the token is valid

        //post request to confirm reset password page
        mvc.perform(post("/reset-password")
                .param("newPassword", "Password_1")//new password input
                .param("confirmPassword", "Password_2")// wrong confirm password input
                .sessionAttr("token", confirmationToken.getConfirmationToken()))//session attribute
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Confirm password did not match new password")));
        //should show error message that confirm password input did not match new password input
    }


    /*BAD PATH*/
    @Test
    public void ResetUserPasswordInvalidPasswordStrength() throws Exception {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(47L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken); //save the generate token for Daniel

        //get request to confirm reset password page
        mvc.perform(get("/confirm-reset?token="+confirmationToken.getConfirmationToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("urna@pedeultrices.co.uk")));
        //should show the email address if the token is valid

        //post request to confirm reset password page
        mvc.perform(post("/reset-password")
                .param("newPassword", "password")//new password input
                .param("confirmPassword", "password")// wrong confirm password input
                .sessionAttr("token", confirmationToken.getConfirmationToken()))//session attribute
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Password must contain at least 1 uppercase characters.,Password must contain at least 1 digit characters.,Password must contain at least 1 special characters.")));
        //should show error message that password input doesn't meet the password strength requirements
    }

    /*GOOD PATH*/
    @Test
    public void resetUserPassword() throws Exception {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "urna@pedeultrices.co.uk")) // valid email
                .andDo(print())
                .andExpect(status().isOk());

        Login daniel = new Login(47L,"urna@pedeultrices.co.uk","password", 2);

        ConfirmationToken confirmationToken = new ConfirmationToken(daniel);
        assertEquals(confirmationToken.getConfirmationToken().length(), 36);
        System.out.println(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken); //save the generate token for Daniel

        //get request to confirm reset password page
        mvc.perform(get("/confirm-reset?token="+confirmationToken.getConfirmationToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("urna@pedeultrices.co.uk")));
        //should show the email address if the token is valid

        //post request to confirm reset password page
        mvc.perform(post("/reset-password")
                .param("newPassword", "Password_1")//new password input
                .param("confirmPassword", "Password_1")// confirm password input
                .sessionAttr("token", confirmationToken.getConfirmationToken()))//session attribute
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Password has been reset successfully")));
    }
}