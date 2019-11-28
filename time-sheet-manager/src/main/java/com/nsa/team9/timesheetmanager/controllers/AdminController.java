package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.controllers.util.DateContainer;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.services.AdminSearchImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    private AdminSearchImpl adminSearch;

    public AdminController(AdminSearchImpl aRepo) {
        adminSearch = aRepo;
    }

    /*Map to admin page*/
    @GetMapping("/admin")
    public String showtimesheets(Model model, DateContainer dateContainer){
    List<TimeSheet> timesheets = adminSearch.getAllTimeSheets();
    model.addAttribute("timesheets", timesheets);
    return "adminshowtimesheets";
    }

    /*map to admin page with date range filter*/
    @RequestMapping("/admin/date")
    public String findTimeSheetsByDate(Model model, DateContainer dateContainer){
        List<TimeSheet> timesheets = adminSearch.findTimeSheetsByDate(dateContainer.getDateFrom(), dateContainer.getDateTo());
        model.addAttribute("timesheets", timesheets);
        model.addAttribute("searchTerm", dateContainer);
        return "adminshowtimesheets";
    }
}
