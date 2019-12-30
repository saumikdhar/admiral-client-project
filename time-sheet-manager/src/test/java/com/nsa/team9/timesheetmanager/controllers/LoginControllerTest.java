package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserDetailsService;
import com.nsa.team9.timesheetmanager.repositories.ConfirmationTokenRepository;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
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

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.team9.timesheetmanager.controllers.LoginController.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

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


}