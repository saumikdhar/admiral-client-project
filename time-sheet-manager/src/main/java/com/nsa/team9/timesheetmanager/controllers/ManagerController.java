package com.nsa.team9.timesheetmanager.controllers;


import com.nsa.team9.timesheetmanager.controllers.util.ManagerNotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes({"note"})
public class ManagerController {

    static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

    @GetMapping("/manager")
    public String showDashboard(Model model) {
        //remove once jpa added
        List<String> tests = new ArrayList<>();
        tests.add("Test1");
        tests.add("Test2");
        model.addAttribute("tests",tests);
        //
        List<String> rejectOptions = Arrays.asList("Bank Holiday", "Incorrect Days Selected", "Incorrect Overtime recorded");
        System.out.println(rejectOptions);
        model.addAttribute("rejectOptions",rejectOptions);
        model.addAttribute("note", new ManagerNotes());
        return "manager";
    }

    @RequestMapping(value = "manager/addNotes", method = RequestMethod.POST)
    public String saveRejectedNotes(@ModelAttribute("note") @Valid ManagerNotes note,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Manager addNotes has errors");
            //remove once jpa added
            List<String> tests = new ArrayList<>();
            tests.add("Test1");
            tests.add("Test2");
            model.addAttribute("tests",tests);
            //
            return "redirect:/manager";
        }
        return "redirect:/manager";
    }

}
