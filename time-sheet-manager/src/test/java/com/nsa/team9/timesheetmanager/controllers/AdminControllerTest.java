package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserDetailsService;
import com.nsa.team9.timesheetmanager.services.AdminSearchImpl;
import com.nsa.team9.timesheetmanager.services.ContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.ManagerSearchImpl;

import com.nsa.team9.timesheetmanager.services.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.team9.timesheetmanager.controllers.AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    LoginSearchImpl loginSearch;

    @MockBean
    AdminSearchImpl adminSearch;

    @MockBean
    ManagerSearchImpl managerSearch;

    @MockBean
    ContractorSearchImpl contractorSearch;

    @MockBean
    AgencySearchImpl agencySearch;

    @MockBean
    MyUserDetailsService userDetailsService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    public void ShouldRedirectToLoginPage() throws Exception{
    mvc.perform(
            get("/admin"))
            .andDo(print())
            .andExpect(redirectedUrlPattern("**/login"));
    }


    @Test
    public void forgottenPasssword() throws Exception {
        mvc.perform(get("/forgot-password") //get forgot password page
        )
                .andDo(print())
                .andExpect(status().isOk()) //check the status
                .andExpect(content().string(Matchers.containsString("Forgot Your Password?")))
//        expecting the page with a string of forgot password
        ;

    }



}