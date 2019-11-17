package com.nsa.team9.timesheetmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerController {

    @GetMapping("manager")
    public String showDashboard(Model model) {
        List<String> tests = new ArrayList<>();
        tests.add("Test1");
        tests.add("Test2");
        model.addAttribute("tests",tests);
        return "manager";
    }
}
