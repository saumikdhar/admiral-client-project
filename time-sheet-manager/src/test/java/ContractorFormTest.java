
import com.nsa.team9.timesheetmanager.controllers.TimeSheetForm;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class ContractorFormTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void makeTimeSheetAndCheckIfFormSubmits() throws Exception {

        String sDate1="13/11/2019";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

        TimeSheetForm TestTimeSheetForm = new TimeSheetForm(date1,Boolean.TRUE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,2);

        mvc.perform
                (get
                        ("/TimeSheetDetails") //final click in journey is a get.  everything is on the session at this point
                        .sessionAttr("TimeSheetForm", TestTimeSheetForm)
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(containsString("Thank you for submitting your time sheet")) //check for text from receipt page
                )
        ;
    }




}
