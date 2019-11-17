package com.nsa.team9.timesheetmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContractorController {

@GetMapping("/TimeSheetForm")
public String ReturnTimeSheet(){

    return "contractor_timesheet";
};


}
