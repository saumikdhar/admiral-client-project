package com.nsa.team9.timesheetmanager.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class AdminSearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void userCanLog() throws Exception {
        mvc.perform(post("/login")
                .param("username", "urna@pedeultrices.co.uk") //admin's email address
                .param("password", "password")) // admin's password
                .andExpect(redirectedUrl("/loginSuccess")) //success url redirection
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        assert session != null;
                        SecurityContext securityContext = (SecurityContext) session.
                                getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
                        assertEquals(securityContext.getAuthentication().getName(), "urna@pedeultrices.co.uk");
                    }
                })
        ;
    }

    @Test
    public void adminCanLog() throws Exception {
        mvc.perform(post("/login")
                .param("username", "urna@pedeultrices.co.uk") //admin's email address
                .param("password", "password")) // admin's password
                .andExpect(redirectedUrl("/loginSuccess")) //success url redirection
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpServletRequest request;
                        HttpSession session = mvcResult.getRequest().getSession();
                        assert session != null;
                        SecurityContext securityContext = (SecurityContext) session.
                                getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
                        assertEquals(securityContext.getAuthentication().getAuthorities().toString(), "[ROLE_2]");
                        // Admin access role is 2 so authentication should return 2 if t he user logged in is the admin
                    }
                })
        ;
    }


    @Test
    public void incorrectPasswordRedirection() throws Exception {
        mvc.perform(post("/login")
                .param("username", "urna@pedeultrices.co.uk") //admin's email address
                .param("password", "password1")) // admin's wrong password
                .andExpect(redirectedUrl("/login?error")) //login error url redirection
        ;

    }


}
