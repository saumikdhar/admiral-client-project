package com.nsa.team9.timesheetmanager.controllers;


import com.nsa.team9.timesheetmanager.controllers.util.ManagerNotes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"note"})
public class ManagerController {

    @GetMapping("/manager")
    public String showDashboard(Model model) {
        List<String> tests = new ArrayList<>();
        tests.add("Test1");
        tests.add("Test2");
        model.addAttribute("tests",tests);
        model.addAttribute("note", new ManagerNotes());
        return "manager";
    }

    @RequestMapping(value = "manager/addNotes", method = RequestMethod.POST)
    public String saveRejectedNotes(@ModelAttribute("note") @Valid ManagerNotes note,
                                    Model model,
                                    BindingResult bindingResult) {

        return "redirect:/manager";
    }

}
