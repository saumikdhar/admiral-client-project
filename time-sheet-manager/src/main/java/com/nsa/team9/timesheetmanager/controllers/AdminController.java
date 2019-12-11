package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.controllers.util.DateContainer;
import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;
import com.nsa.team9.timesheetmanager.services.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes({"agencies","managers"})
@RequestMapping("/admin")
public class AdminController {

    private AdminSearchImpl adminSearch;
    private ContractorSearchImpl contractorSearch;
    private ManagerSearchImpl managerSearch;
    private LoginSearchImpl loginSearch;
    private AgencySearchImpl agencySearch;
    private PasswordEncoder encoder;


    static final Logger LOG = LoggerFactory.getLogger(AdminController.class);


    public AdminController(AdminSearchImpl aRepo, ContractorSearchImpl cRepo, ManagerSearchImpl mRepo
            , LoginSearchImpl lRepo, AgencySearchImpl agRepo, PasswordEncoder encoder) {
        adminSearch = aRepo;
        contractorSearch = cRepo;
        managerSearch = mRepo;
        loginSearch = lRepo;
        agencySearch = agRepo;
        this.encoder = encoder;
    }

    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.password}")
    private String password;


    /*Map to admin page*/
    @GetMapping("/timesheets")
    public String showtimesheets(Model model, DateContainer dateContainer) {
        List<TimeSheet> timesheets = adminSearch.getAllTimeSheets();
        model.addAttribute("timesheets", timesheets);
        return "adminshowtimesheets";
    }

    /*map to admin page with date range filter*/
    @RequestMapping("/timesheets/date")
    public String findTimeSheetsByDate(Model model, DateContainer dateContainer) {
        List<TimeSheet> timesheets = adminSearch.findTimeSheetsByDate(dateContainer.getDateFrom(), dateContainer.getDateTo());
        model.addAttribute("timesheets", timesheets);
        model.addAttribute("searchTerm", dateContainer);
        return "adminshowtimesheets";
    }


    /*map to from admin page to assign manager page*/
    @GetMapping("/assign-manager")
    public String assignManagerToContractor(Model model, @ModelAttribute("managerId") Manager manager) {
        List<ContractorProjection> contractors = adminSearch.findAllContractorsAndManagersAssociated();
        List<Manager> managers = managerSearch.findAllManagers();
        model.addAttribute("managers", managers);
        model.addAttribute("agencies", contractors);

        return "adminAssignManager";
    }

    @PostMapping("/assign-manager/update-manager")
    public String updateManager(Model model, @ModelAttribute("managerId") Manager manager, @RequestParam(value = "contractor_id") Long contractor) {
        contractorSearch.updateContractorManager(contractor, manager.getId());
        return "redirect:";
    }

    @GetMapping("/create-account")
    public String showCreateAccountPage(Model model) {
        List<Agency> agencies = agencySearch.findAllAgency();
        List<Manager> managers = managerSearch.findAllManagers();
        model.addAttribute("managers", managers);
        model.addAttribute("agencies", agencies);
        model.addAttribute("account", new AccountForm());
        return "adminCreateAccount";
    }

    @PostMapping("create-account/details")
    public String createAccount(Model model, @ModelAttribute("account") @Valid AccountForm account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("create-account has binding errors");
            model.addAttribute("account", account);
            return "adminCreateAccount";
        }

        Login L = new Login(
                null,
                account.getEmailAddress(),
                encoder.encode(account.getPassword()),
                account.getAccessLevel()
        );
        loginSearch.createLogin(L);
        LOG.debug("created login " + L);
        if (account.getAccessLevel() == 2) {
            Admin a = new Admin(null, L, account.getFirstName(), account.getLastName());
            adminSearch.createAdmin(a);
            LOG.debug("created admin account " + a);
        }

        if (account.getAccessLevel() == 1) {
            System.out.println("LOGIN IS " + L);
            Manager m = new Manager(null, account.getFirstName(), account.getLastName(), L);
            managerSearch.createManager(m);
            System.out.println("created manager account " + m);
        }


        if (account.getAccessLevel() == 0) {
            Manager m = new Manager(account.getManagerId(), "n/a", "n/a", null);
            Agency a = new Agency(account.getAgencyId(), "n/a");
            System.out.println("LOGIN IS " + L);
            Contractor c = new Contractor(
                    null,
                    account.getFirstName(),
                    account.getLastName(),
                    L,
                    m,
                    a
            );

            contractorSearch.createContractor(c);
            System.out.println("created Contractor");
        }
        return "adminCreateAccountConfirmation";
    }
}
