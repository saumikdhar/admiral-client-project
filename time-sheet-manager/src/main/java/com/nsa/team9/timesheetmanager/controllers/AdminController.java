package com.nsa.team9.timesheetmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class AdminController {

@GetMapping("/admin")
public String showtimesheets(Model model){
    return "adminshowtimesheets";
}
}