package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.domain.Contractor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;



public interface AgencyContractorRepositry {

    public Optional<AgencyContractor> findById(Long agency_contractor_Id);

    /*Save agencyContractor to DB*/
    public AgencyContractor save(AgencyContractor aAgencyContractor);

    /*Find any if agencycontractor has a link between agency and contractor already*/
    public Optional<AgencyContractor> findAgencyContractorExists(Long agencyId, Long contractorId);
}
