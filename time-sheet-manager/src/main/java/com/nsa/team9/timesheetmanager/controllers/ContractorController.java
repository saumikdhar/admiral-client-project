package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencySearchImpl agencySearch;
    private TimeSheetSearchImpl TimeSheetValidation;


    public ContractorController(TimeSheetSearch aCreator,
                                AgencySearchImpl aAgencyRepo, TimeSheetSearchImpl timeSheetValidation){
        TimeSheetCreator = aCreator;
        agencySearch = aAgencyRepo;
        TimeSheetValidation = timeSheetValidation;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    };

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult) {


        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque","1");
        Manager m = new Manager(21L,"Felix","Shaffer",l);
        Agency a = agencyContractorForm.getAgency_id();
        Contractor c = new Contractor(7L,"gabriel","agius",l,m,a);

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


        Optional<TimeSheet> timeSheets2 = TimeSheetValidation.CheckIfTimeSheetExists(TimeSheet.getStart_date());
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
        return "timesheet_confirmation";
        
    }
}
