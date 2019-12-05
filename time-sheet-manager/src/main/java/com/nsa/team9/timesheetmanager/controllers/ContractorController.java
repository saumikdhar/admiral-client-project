package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.domain.*;
<<<<<<< HEAD
import com.nsa.team9.timesheetmanager.services.AgencyContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.AgencySearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
=======
import com.nsa.team9.timesheetmanager.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
>>>>>>> develop
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
>>>>>>> develop

import javax.mail.MessagingException;
import javax.validation.Valid;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> develop
import java.util.List;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencySearchImpl agencySearch;
    private ContractorSearchImpl contractorSearch;


    public ContractorController(TimeSheetSearch aCreator,
                                AgencySearchImpl aAgencyRepo,
                                ContractorSearchImpl aContractorSearch){
        TimeSheetCreator = aCreator;
        agencySearch = aAgencyRepo;
        contractorSearch = aContractorSearch;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    }

<<<<<<< HEAD
    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.password}")
    private String password;

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult) throws IOException, MessagingException {


        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque","1");
        Manager m = new Manager(2L,"Felix","Shaffer",l);
        Contractor c = new Contractor(7L,"gabriel","agius",l,m);
=======
    @PostMapping("/TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult,
                                   HttpSession session,
                                   SessionStatus status,
                                   Authentication authentication) {
        //Get the logged in user
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        LOG.debug("The principal is " + principal);
        Contractor c = contractorSearch.findContractorByEmail(principal.getUser().getEmail()).get();

//        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque",1);
//        Manager m = new Manager(2L,"Felix","Shaffer",l);
>>>>>>> develop
        Agency a = agencyContractorForm.getAgency_id();

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Timesheet has binding errors");
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }
        TimeSheet t = new TimeSheet(
                c,
                null,
                TimeSheet.getMonday_worked(),
                TimeSheet.getTuesday_worked(),
                TimeSheet.getWednesday_worked(),
                TimeSheet.getThursday_worked(),
                TimeSheet.getFriday_worked(),
                TimeSheet.getSaturday_worked(),
                TimeSheet.getSunday_worked(),
                TimeSheet.getOvertime(),
                TimeSheet.getStart_date(),
                "pending");

        System.out.println("saved timesheet " + t.toString());
        TimeSheetCreator.createTimeSheet(t);

<<<<<<< HEAD
=======
        //invalidate session without removing login
        status.setComplete();
        session.removeAttribute("agencies");

>>>>>>> develop
        return "timesheet_confirmation";

        //Code here to get the post request running when page is returned
    }


}
