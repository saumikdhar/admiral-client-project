package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.controllers.util.DateContainer;
import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;
import com.nsa.team9.timesheetmanager.services.ManagerSearchImpl;
import com.nsa.team9.timesheetmanager.services.AdminSearchImpl;
import com.nsa.team9.timesheetmanager.services.ContractorSearchImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminSearchImpl adminSearch;
    private ContractorSearchImpl contractorSearch;
    private ManagerSearchImpl managerSearch;

    static final Logger LOG = LoggerFactory.getLogger(AdminController.class);


    public AdminController(AdminSearchImpl aRepo, ContractorSearchImpl cRepo, ManagerSearchImpl mRepo) {
        adminSearch = aRepo;
        contractorSearch = cRepo;
        managerSearch = mRepo;
    }

    /*Map to admin page*/
    @GetMapping("/timesheets")
    public String showtimesheets(Model model, DateContainer dateContainer){
    List<TimeSheet> timesheets = adminSearch.getAllTimeSheets();
    model.addAttribute("timesheets", timesheets);
    return "adminshowtimesheets";
    }

    /*map to admin page with date range filter*/
    @RequestMapping("/timesheets/date")
    public String findTimeSheetsByDate(Model model, DateContainer dateContainer){
        List<TimeSheet> timesheets = adminSearch.findTimeSheetsByDate(dateContainer.getDateFrom(), dateContainer.getDateTo());
        model.addAttribute("timesheets", timesheets);
        model.addAttribute("searchTerm", dateContainer);
        return "adminshowtimesheets";
    }

    /*map to from admin page to assign manager page*/
    @GetMapping("/assign-manager")
    public String assignManagerToContractor(Model model, @ModelAttribute("managerId") Manager manager){
        List<ContractorProjection> contractors = adminSearch.findAllContractorsAndManagersAssociated();
        List<Manager> managers = managerSearch.findAllManagers();
        model.addAttribute("managers",managers);
        model.addAttribute("agencies", contractors);

      return "adminAssignManager";
    }

    @PostMapping("/assign-manager/update-manager")
    public String updateManager(Model model, @ModelAttribute("managerId") Manager manager, @RequestParam(value = "contractor_id") Long contractor){
        contractorSearch.updateContractorManager(contractor, manager.getId());
        return "redirect:";
    }
}
