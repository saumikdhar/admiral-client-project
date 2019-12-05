package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @RunWith(SpringRunner.class)
    @WebMvcTest(com.nsa.team9.timesheetmanager.controllers.ContractorController.class)
    public class TimeSheetFormTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        TimeSheetSearch timeSheetSearch;

        @Test
        public void makeTimeSheetAndCheckIfFormSubmits() throws Exception {

            LocalDate inputDate = LocalDate.of(2019, 11, 13);


//            TimeSheetForm TestTimeSheetForm = new TimeSheetForm(inputDate, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 2.0);

            mvc.perform
                    (post
                            ("/TimeSheetDetails")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("start_date", "2019-11-19")
                            .param("monday_worked", "true")
                            .param("tuesday_worked", "false")
                            .param("wednesday_worked", "false")
                            .param("thursday_worked", "false")
                            .param("friday_worked", "false")
                            .param("saturday_worked", "false")
                            .param("sunday_worked", "false")
                            .param("overtime", "2")
                    )
                    .andDo(
                            print()
                    )
                    .andExpect(
                            status().isOk()
                    )
                    .andExpect(
                            content().string(containsString("Thank you for submitting your time sheet"))
                    )
            ;
        }

    }