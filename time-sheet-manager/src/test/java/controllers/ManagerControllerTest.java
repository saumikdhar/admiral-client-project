package controllers;

import com.nsa.team9.timesheetmanager.controllers.ManagerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void showDashboard() throws Exception{
//
//
//        mvc.perform
//                (get
//                        ("/manager")
//                )
//                .andDo(
//                        print()
//                )
//                .andExpect(
//                        content().string(containsString("Table Headers"))
//                );
//    }

//    @Test
//    public void saveRejectedNotes() {
//
//
//    }
}