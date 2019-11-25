package com.nsa.team9.timesheetmanager.services;


import com.nsa.team9.timesheetmanager.domain.AgencyContractor;

import java.util.Optional;

public interface AgencyContractorSearch {
    public Optional<AgencyContractor> findById(Long id);
}
