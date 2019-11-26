package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.repositories.AgencyContractorRepositry;
import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRepositoryJpa;
import com.nsa.team9.timesheetmanager.services.AgencyContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDate;

@Service
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;

    private AgencyContractorSearchImpl AgencyContractorCreator;


    public ContractorController(TimeSheetSearch aCreator,AgencyContractorSearchImpl aRepo){
        TimeSheetCreator = aCreator;
        AgencyContractorCreator = aRepo;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){

        model.addAttribute("TimeSheet", new TimeSheetForm());
        return "contractor_timesheet";
    };

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model, @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet, BindingResult bindingResult) {

        Agency a = new Agency(37L,"KP Limited");
        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque","1");
        Manager m = new Manager(2L,"Felix","Shaffer",l);
        Contractor c = new Contractor(7L,"gabriel","agius",l,m);
        AgencyContractor agencyContractor = new AgencyContractor(2L,a,c);

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Timesheet has binding errors");
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }

        TimeSheet t = new TimeSheet(
                agencyContractor,
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
                "Pending");



        System.out.println("saved timesheet " + t.toString());
        TimeSheetCreator.createTimeSheet(t);
        return "timesheet_confirmation";
    }

}
