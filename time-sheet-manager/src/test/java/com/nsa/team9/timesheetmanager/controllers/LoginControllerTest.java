package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserDetailsService;
import com.nsa.team9.timesheetmanager.domain.ConfirmationToken;
import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.ConfirmationTokenRepository;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.team9.timesheetmanager.controllers.LoginController.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MyUserDetailsService userDetailsService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    LoginSearchImpl loginSearch;

    @MockBean
    ConfirmationTokenRepository confirmationTokenRepository;

    @MockBean
    EmailService emailSenderService;

    /*BAD PATH*/
    @Test
    public void invalidEmailForgotPassword() throws Exception {
        mvc.perform(post("/forgot-password") // post to forgot password page
                .param("emailAddress", "hbjb")) // invalid email input (without @, .com etc)
                .andDo(print())
                .andExpect(status().isOk())
                //expected error messages
                .andExpect(content().string(Matchers.containsString("Please provide a valid email address")))
                .andExpect(content().string(Matchers.containsString("must be a well-formed email address")))
                .andExpect(content().string(Matchers.containsString("The email address you entered doesn&#39;t exist in Admiral")));

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


}