package com.nsa.team9.timesheetmanager.controllers;


import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.controllers.util.ManagerNotes;
import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.domain.Manager;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import com.nsa.team9.timesheetmanager.services.ManagerSearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@SessionAttributes({"note"})
public class ManagerController {

    static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

    private TimeSheetSearchImpl timeSheetSearch;
    private LoginSearchImpl loginSearch;
    private ManagerSearchImpl managerSearch;

    public ManagerController(TimeSheetSearchImpl aRepo, LoginSearchImpl aLoginSearch, ManagerSearchImpl aManagerSearch) {

        timeSheetSearch = aRepo;
        loginSearch = aLoginSearch;
        managerSearch = aManagerSearch;
    }

    @GetMapping("/loginSuccess")
    public String successfulLogin(Authentication authentication) {
        //attempt to use my principle
        try {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

            boolean isContractor = false;
            boolean isManager = false;
            boolean isAdmin = false;

            if (principal.getUser().getAccessLevel().equals(0)) {
                isContractor = true;
            } else if (principal.getUser().getAccessLevel().equals(1)) {
                isManager = true;
            } else if (principal.getUser().getAccessLevel().equals(2)) {
                isAdmin = true;
            }


            if (isContractor) {
                return "redirect:/TimeSheetForm";
            } else if (isManager) {
                return "redirect:/manager";
            } else if (isAdmin) {

                return "redirect:/admin/timesheets";
            } else {
                String error = new IllegalStateException().getMessage();
                LOG.debug(error);
                return "redirect:/login";
            }
        }catch (NullPointerException n) {
            return "redirect:/login";
        }
    }





    @GetMapping("/manager")
    public String showDashboard(Model model, Authentication authentication) {

        //once login is implemented change inputs the which ever manager is logged in
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

        LOG.debug("The principal is " + principal);
        System.out.println("The principal is " + principal.getUser().getEmail());

        Manager manager = managerSearch.findManagerByEmail(principal.getUser().getEmail()).get();
        String firstName = manager.getFirstName();
        String lastName = manager.getLastName();

        List<TimeSheet> timeSheets = timeSheetSearch.getTimeSheetsByManager(lastName,firstName);

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
                                    Model model,
                                    HttpSession session,
                                    SessionStatus status) {
        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Manager addNotes has errors");
            return "redirect:/manager";
        }

//        System.out.println(timesheet_id);

        timeSheetSearch.updateTimesheetStatus("rejected", timesheet_id);

        //invalidate session without removing login
        status.setComplete();
        session.removeAttribute("note");

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
