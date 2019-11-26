package com.nsa.team9.timesheetmanager.services;


import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.domain.Contractor;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AgencyContractorSearch {

    public Optional<AgencyContractor> findById(Long id);

    public void createAgency(AgencyContractor aAgencyContractor);

    /*Find any if agencycontractor has a link between agency and contractor already*/
    public Optional<AgencyContractor> findAgencyContractorExists(Long agencyId, Long contractorId);
}
