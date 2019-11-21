package com.nsa.team9.timesheetmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;

@Controller
public class ContractorController {

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
}
