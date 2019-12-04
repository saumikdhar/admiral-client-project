package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Contractor;

import java.util.Optional;

public interface ContractorRepository {

    public Optional<Contractor> findContractorByEmail(String email);
}
