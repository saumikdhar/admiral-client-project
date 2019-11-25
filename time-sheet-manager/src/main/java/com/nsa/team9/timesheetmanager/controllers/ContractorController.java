package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRepositoryJpa;
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

    private TimeSheetRepositoryJpa TimeSheetCreator ;

    public ContractorController(TimeSheetRepositoryJpa aCreator){
        TimeSheetCreator = aCreator;
    }

    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

@GetMapping("/TimeSheetForm")
public String ReturnTimeSheet(Model model){

    model.addAttribute("TimeSheet", new TimeSheetForm());
    return "contractor_timesheet";
};

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model, @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }

        return "timesheet_confirmation";
    }

    @RequestMapping(path = "/TimeSheetConfirm", method = RequestMethod.POST)
    public String confirmTimeSheet(@ModelAttribute("TimeSheetKey") @Valid TimeSheetForm timeSheet,
                                 BindingResult bindingResult,
                                 Model model,
                                 HttpSession session) {

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("TimeSheet Form has binding errors");
            return "contractor_timesheet";
        }

        TimeSheet t = new TimeSheet(
                null,
                null,
                timeSheet.getMonday_worked(),
                timeSheet.getTuesday_worked(),
                timeSheet.getWednesday_worked(),
                timeSheet.getThursday_worked(),
                timeSheet.getFriday_worked(),
                timeSheet.getSaturday_worked(),
                timeSheet.getSunday_worked(),
                timeSheet.getOvertime(),
                timeSheet.getStart_date(),
                "Pending");



        TimeSheetCreator.save(t);
        return "timesheet_confirmation";
    }
}
