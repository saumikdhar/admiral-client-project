package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.domain.Contractor;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.AgencyContractorRepositry;
import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRepositoryJpa;
import com.nsa.team9.timesheetmanager.services.AgencyContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        Agency a = new Agency(null,"KP Limited");
        Contractor c = new Contractor(null,"gabriel","agius",null,null);
        AgencyContractor agencyContractor = new AgencyContractor(null,null,null);

        if (bindingResult.hasErrors()) {
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



        TimeSheetCreator.createTimeSheet(t);

        return "timesheet_confirmation";
    }

}
