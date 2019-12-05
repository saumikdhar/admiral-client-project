package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Contractor;

import java.util.List;
import java.util.Optional;

public interface ContractorRepository {

    /*Save contractor to DB*/
    public Contractor save(Contractor contractor);

    /*find contractor by id*/
    Optional<Contractor> findContractorById(Long contractorId);

    /*update contractor manager*/
    void updateContractorManager(Long contractorId, Long managerId);

    public Optional<Contractor> findContractorByEmail(String email);
}
