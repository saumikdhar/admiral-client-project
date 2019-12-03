package com.nsa.team9.timesheetmanager.controllers;


import com.nsa.team9.timesheetmanager.controllers.util.ManagerNotes;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@SessionAttributes({"note"})
public class ManagerController {

    static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

    private TimeSheetSearchImpl timeSheetSearch;

    public ManagerController(TimeSheetSearchImpl aRepo) {
        timeSheetSearch = aRepo;
    }

    @GetMapping("/manager")
    public String showDashboard(Model model) {

        //once login is implemented change inputs the which ever manager is logged in
//        String firstName = "Cyrus";
//        String lastName = "Moreno";


        String firstName = "Colt";
        String lastName = "Montgomery";

        List<TimeSheet> timeSheets = timeSheetSearch.getTimeSheetsByManager(lastName,firstName);

        /* REMOVE WHEN PUSHED TO MASTER */
//        System.out.println("Timesheets....");
//        System.out.println(timeSheets.size());
//        System.out.println(timeSheets.get(0));
//        System.out.println("Tuesday is" + timeSheets.get(0).isTuesdayWorked());

        model.addAttribute("timesheets", timeSheets);

//        Scanner input = new Scanner(new File("rejectedTimesheetReasons"));
        List<String> rejectOptions = Arrays.asList("Bank Holiday", "Incorrect Days Selected", "Incorrect Overtime recorded");
        System.out.println(rejectOptions);
        model.addAttribute("rejectOptions",rejectOptions);
        model.addAttribute("note", new ManagerNotes());
        return "manager";
    }

    //reject timesheets
    @RequestMapping(value = "manager/addNotes", method = RequestMethod.POST)
    public String saveRejectedNotes(@ModelAttribute("note") @Valid ManagerNotes note,
                                    @RequestParam(value = "timesheet_id") Long timesheet_id,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Manager addNotes has errors");
            return "redirect:/manager";
        }

//        System.out.println(timesheet_id);

        timeSheetSearch.updateTimesheetStatus("rejected", timesheet_id);

        return "redirect:/manager";
    }

    //accept timesheets
    @PostMapping("manager/accept")
    public String acceptTimesheets(@RequestParam(value = "timesheet_id") Long timesheet_id) {

//        System.out.println("ACCEPT ID: " + timesheet_id);
        timeSheetSearch.updateTimesheetStatus("accepted", timesheet_id);

        return "redirect:/manager";
    }

}
