package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.repositories.AgencyContractorRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AgencyContractorSearchImpl implements AgencyContractorSearch {

    private AgencyContractorRepositry agencyContractorRepositry;


    public AgencyContractorSearchImpl(AgencyContractorRepositry aRepo){
        agencyContractorRepositry = aRepo;
    }

    @Override
    public Optional<AgencyContractor> findById(Long agency_contractor_Id) {
        return agencyContractorRepositry.findById(agency_contractor_Id);
    }

}
