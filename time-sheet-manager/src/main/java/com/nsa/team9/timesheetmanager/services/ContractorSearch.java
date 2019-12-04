package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Contractor;

import java.util.Optional;

public interface ContractorSearch {

    public Optional<Contractor> findContractorByEmail(String email);
}
