package com.nsa.team9.timesheetmanager.controllers;

import jdk.internal.jline.internal.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class AdminController {


@GetMapping("admin")
public String showtimesheets(Model model){
    String[] james = {"Co","Gavin Meredith","worked", "worked", "not worked", "not worked", "worked", "approved", "Ben Davies"};
    ArrayList<String[]> timesheets = new ArrayList<>();
    timesheets.add(james);
    model.addAttribute("timesheets", timesheets);
    String[] timesheet = timesheets.get(0);
    ;
//    Log.debug(timesheet[0]);
    return "adminshowtimesheets";
}
}