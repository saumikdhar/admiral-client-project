package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.repositories.AgencyContractorRepositry;
import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRepositoryJpa;
import com.nsa.team9.timesheetmanager.services.*;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencyContractorSearchImpl agencyContractorCreator;
    private AgencySearchImpl agencySearch;
    private ContractorSearchImpl contractorSearch;


    public ContractorController(TimeSheetSearch aCreator,
                                AgencyContractorSearchImpl aRepo,
                                AgencySearchImpl aAgencyRepo,
                                ContractorSearchImpl aContractorSearch){
        TimeSheetCreator = aCreator;
        agencyContractorCreator = aRepo;
        agencySearch = aAgencyRepo;
        contractorSearch = aContractorSearch;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){

        List<Agency> agencies = agencySearch.findAllAgency();
        model.addAttribute("agencies",agencies);
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    }

    @PostMapping("/TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult,
                                   HttpSession session,
                                   SessionStatus status,
                                   Authentication authentication) {
        //Get the logged in user
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        LOG.debug("The principal is " + principal);
        Contractor c = contractorSearch.findContractorByEmail(principal.getUser().getEmail()).get();

//        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque",1);
//        Manager m = new Manager(2L,"Felix","Shaffer",l);
        Agency a = agencyContractorForm.getAgency_id();


        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Timesheet has binding errors");
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }
        AgencyContractor agencyContractor = new AgencyContractor(null, a, c);

        TimeSheet t = new TimeSheet(
//                checkIfAgencyContractorLinkExists(a,c),
                agencyContractor,
                null,
                TimeSheet.getMonday_worked(),
                TimeSheet.getTuesday_worked(),
                TimeSheet.getWednesday_worked(),
                TimeSheet.getThursday_worked(),
                TimeSheet.getFriday_worked(),
                TimeSheet.getSaturday_worked(),
                TimeSheet.getSunday_worked(),
                TimeSheet.getOvertime(),
                TimeSheet.getStart_date(),
                "pending");

        System.out.println("saved timesheet " + t.toString());
        TimeSheetCreator.createTimeSheet(t);

        //invalidate session without removing login
        status.setComplete();
        session.removeAttribute("agencies");

        return "timesheet_confirmation";
    }

//    private AgencyContractor checkIfAgencyContractorLinkExists(Agency a, Contractor c){
//        Optional<AgencyContractor> agencyContractorExists = agencyContractorCreator.findAgencyContractorExists(a.getId(),c.getId());
//        AgencyContractor agencyContractor = new AgencyContractor(null, a, c);
//
//        System.out.println("data is " + agencyContractorExists.isPresent() + " "+ agencyContractorExists);
//        if ((agencyContractorExists).isPresent()) {
//            System.out.println("Link for contractor and agency already exists");
//        }else {
//            System.out.println("creating new link");
//            agencyContractorCreator.createAgency(agencyContractor);
//        }
//        return agencyContractor;
//    };

}
