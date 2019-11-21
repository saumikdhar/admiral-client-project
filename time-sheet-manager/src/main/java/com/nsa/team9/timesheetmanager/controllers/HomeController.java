package com.nsa.team9.timesheetmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//import java.security.Principal;

@Controller
public class HomeController {

//    @GetMapping("/")
//    String index(Principal principal) {
//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
//    }

    @GetMapping("/")
    String getHomePage() {
        return "homeSignedIn";
    }
}
