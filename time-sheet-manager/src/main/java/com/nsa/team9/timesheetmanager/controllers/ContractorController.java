package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.services.AgencyContractorSearchImpl;
import com.nsa.team9.timesheetmanager.services.AgencySearchImpl;
import com.nsa.team9.timesheetmanager.services.TimeSheetSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Service
@SessionAttributes({"agencies"})
@Controller
public class ContractorController {

    private TimeSheetSearch TimeSheetCreator ;
    private AgencyContractorSearchImpl agencyContractorCreator;
    private AgencySearchImpl agencySearch;


    public ContractorController(TimeSheetSearch aCreator,
                                AgencyContractorSearchImpl aRepo,
                                AgencySearchImpl aAgencyRepo){
        TimeSheetCreator = aCreator;
        agencyContractorCreator = aRepo;
        agencySearch = aAgencyRepo;
    }


    static final Logger LOG = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping("/TimeSheetForm")
    public String ReturnTimeSheet(Model model){

        List<Agency> agencies = agencySearch.findAllAgency();
        model.addAttribute("agencies",agencies);
        model.addAttribute("TimeSheet", new TimeSheetForm());
        model.addAttribute("agencycontractor", new AgencyContractorForm());
        return "contractor_timesheet";
    };

    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.password}")
    private String password;

    @PostMapping("TimeSheetDetails")
    public String TimeSheetDetails(Model model,
                                   @ModelAttribute("agencycontractor") AgencyContractorForm agencyContractorForm,
                                   @ModelAttribute("TimeSheet") @Valid TimeSheetForm TimeSheet,
                                   BindingResult bindingResult) throws IOException, MessagingException {


        Login l = new Login(2L,"quis.arcu.vel@augueporttitor.org","Quisque","1");
        Manager m = new Manager(2L,"Felix","Shaffer",l);
        Contractor c = new Contractor(7L,"gabriel","agius",l,m);
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

        return "timesheet_confirmation";

        //Code here to get the post request running when page is returned
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
