package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import org.springframework.stereotype.Service;

import java.util.Optional;



public interface AgencyContractorRepositry {

    public Optional<AgencyContractor> findById(Long agency_contractor_Id);
}
