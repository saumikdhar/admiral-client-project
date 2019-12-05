//package com.nsa.team9.timesheetmanager.controllers;
//
//import com.nsa.team9.timesheetmanager.domain.Login;
//import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.Optional;
//
//@Service
//@Controller
//public class LoginController {
//
//    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);
//
//    private LoginSearchImpl loginSearch;
//
//    public LoginController(LoginSearchImpl aLoginSearch) {
//        loginSearch = aLoginSearch;
//    }
//
//    @GetMapping("/login")
//    public String getLoginPage(Model model) {
//
//        model.addAttribute("Login", new LoginForm());
//        return "login.html";
//    }
//
//    ;
//
//    @PostMapping("/signIn")
//    public String signIn(@ModelAttribute("Login") @Valid LoginForm Login,
//                         BindingResult bindingResult,
//                         Model model) {
//
//        if (bindingResult.hasErrors()) {
//            LOG.error(bindingResult.toString());
//            LOG.error("Login Form has binding errors");
//            model.addAttribute("Login", Login);
//            return "login";
//        }
//
//        Optional<Login> loginDetails = loginSearch.getLoginByEmail(Login.getEmail());
//
//        if (loginDetails.get().getPassword().toUpperCase().equals(Login.getPassword().toUpperCase())) {
//
//            // check user role and decide the redirect URL
//            if (loginDetails.get().getAccessLevel() == 0) {
////                url = "/TimeSheetForm";
//            } else if (loginDetails.get().getAccessLevel() == 1) {
////                url = "/manager";
//            } else if (loginDetails.get().getAccessLevel() == 2) {
////                url = "/admin";
////            return url;
//                //do something
//                //set accesslevel and get persons first name and last name then set as session
//                LOG.debug("LOGIN SUCCESSFUL");
//                return "redirect:/admin";
//            }
//
//
//            LOG.debug("LOGIN FAIL");
//            return "login";
//        }
//        return "";
//    }
//}
