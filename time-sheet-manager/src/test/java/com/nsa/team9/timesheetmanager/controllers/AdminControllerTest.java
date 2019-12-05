package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.services.AdminSearchImpl;
import com.nsa.team9.timesheetmanager.services.ContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.ManagerSearchImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.team9.timesheetmanager.controllers.AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTest {

@Autowired
private MockMvc mvc;

@MockBean
AdminSearchImpl adminSearch;

@MockBean
ManagerSearchImpl managerSearch;

@MockBean
ContractorSearchImpl contractorSearch;


@Test
    public void ShouldShowAdminPage() throws Exception{
    mvc.perform(
            get("/admin")
    ).andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void ShouldShowJohnwithApprovedStatus() throws Exception{
        mvc.perform(
                get("/admin/timesheets")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<td><span style=\"color: green\">approved</span></td>")))
                .andExpect(content().string(containsString("<td>John Smith</td>")));
    }
}