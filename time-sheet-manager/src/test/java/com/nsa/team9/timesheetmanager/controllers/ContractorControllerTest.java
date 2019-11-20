package com.nsa.team9.timesheetmanager.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ContractorControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void CheckTimeSheetFormShows() throws Exception {

        this.mockMvc.perform
                (get
                        ("/TimeSheetForm")
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                )
        ;
    }

}