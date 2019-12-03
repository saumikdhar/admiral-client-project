package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencySearchImpl agencySearch;


    public ContractorController(TimeSheetSearch aCreator,
                                AgencySearchImpl aAgencyRepo){
        TimeSheetCreator = aCreator;
        agencySearch = aAgencyRepo;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    };

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult) {


        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque","1");
        Manager m = new Manager(21L,"Felix","Shaffer",l);
        Agency a = agencyContractorForm.getAgency_id();
        Contractor c = new Contractor(7L,"gabriel","agius",l,m, a);

        if (bindingResult.hasErrors()) {
            LOG.error(bindingResult.toString());
            LOG.error("Timesheet has binding errors");
            model.addAttribute("TimeSheet", TimeSheet);
            return "contractor_timesheet";
        }
        TimeSheet t = new TimeSheet(
                c,
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
