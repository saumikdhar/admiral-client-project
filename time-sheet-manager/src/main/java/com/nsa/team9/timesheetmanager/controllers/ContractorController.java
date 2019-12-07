package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.services.AgencySearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.nsa.team9.timesheetmanager.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencySearchImpl agencySearch;
    private TimeSheetSearchImpl TimeSheetValidation;
    private ContractorSearchImpl contractorSearch;

    public ContractorController(TimeSheetSearch aCreator,
                                AgencySearchImpl aAgencyRepo, TimeSheetSearchImpl timeSheetValidation,
                                ContractorSearchImpl cRepo){
        TimeSheetCreator = aCreator;
        agencySearch = aAgencyRepo;
        TimeSheetValidation = timeSheetValidation;
        contractorSearch = cRepo;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    }

    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.password}")
    private String password;

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
        System.out.println(c);


        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Timesheet has binding errors");
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }
        TimeSheet t = new TimeSheet(
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
                "pending",c);


        Optional<TimeSheet> timeSheets2 = TimeSheetValidation.CheckIfTimeSheetExists(TimeSheet.getStart_date(),c.getId());
        System.out.println(timeSheets2);
        model.addAttribute("timesheets2", timeSheets2);

        //this checks if the start date thats entered exists within the timesheet2 list
        if(!timeSheets2.isPresent()){
            TimeSheetCreator.createTimeSheet(t);
        } else {
            model.addAttribute("TimeSheet", TimeSheet);
            return "duplicate_timesheet";
        }
        System.out.println("saved timesheet " + t.toString());

        //invalidate session without removing login
        status.setComplete();
        session.removeAttribute("agencies");

        //Code here to get the post request running when page is returned
        return "timesheet_confirmation";
    }

//    @GetMapping("/PreviousTimesheets")
//    public String ShowPreviousTimesheets(Model model){
//        model.addAttribute("TimeSheets", imeSheets);
//    }
}
