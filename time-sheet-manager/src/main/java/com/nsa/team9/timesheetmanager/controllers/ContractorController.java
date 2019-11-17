package com.nsa.team9.timesheetmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContractorController {

@RequestMapping(path = "/TimeSheetConfirmation", method = RequestMethod.POST)
public String TimeSheetConfirmation(){

    return "timesheet_confirmation";
};


}
