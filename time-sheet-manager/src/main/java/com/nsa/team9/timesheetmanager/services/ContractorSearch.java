package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Contractor;

import java.util.List;
import java.util.Optional;

public interface ContractorSearch {

    /*creates a contractor*/
    public void createContractor(Contractor contractor);

    /*find contractor by id*/
    Optional<Contractor> findContractorById(Long contractorId);

    /*update contractor manager*/
    void updateContractorManager(Long contractorId, Long managerId);
    public Optional<Contractor> findContractorByEmail(String email);
}
