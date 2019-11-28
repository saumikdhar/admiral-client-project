package com.nsa.team9.timesheetmanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Service
@Controller
public class LoginController {

    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/login")
    public String getLoginPage(Model model){

        model.addAttribute("Login", new LoginForm());
        return "login.html";
    };

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("Login") @Valid LoginForm Login,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Login Form has binding errors");
            model.addAttribute("Login", Login);
            return "login";
        }

        return null;
    }
}
