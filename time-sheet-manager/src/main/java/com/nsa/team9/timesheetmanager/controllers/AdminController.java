package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.services.AdminSearchImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    private AdminSearchImpl adminSearch;

    public AdminController(AdminSearchImpl aRepo) {
        adminSearch = aRepo;
    }

    @GetMapping("/admin")
    public String showtimesheets(Model model){
    List<TimeSheet> timesheets = adminSearch.getAllTimeSheets();
//    System.out.println(timesheets.get(0).getStart_date());
    model.addAttribute("timesheets", timesheets);
    return "adminshowtimesheets";
    }

    @GetMapping("/admin/agency")
    public String findTimeSheetsByAgency(Model model,@RequestParam("search") String searchTerm){
        List<TimeSheet> timesheets = adminSearch.findTimeSheetsByAgencyName(searchTerm);
//    System.out.println(timesheets.get(0).getStart_date());
        model.addAttribute("timesheets", timesheets);
        model.addAttribute("searchTerm", searchTerm);
        return "adminshowtimesheets";
    }
}