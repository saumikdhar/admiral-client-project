package com.nsa.team9.timesheetmanager.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.team9.timesheetmanager.controllers.AdminController.class)
public class AdminControllerTest {

    @Test
    public void ShouldReturnPendingTimeSheets() throws Exception{

    }
}