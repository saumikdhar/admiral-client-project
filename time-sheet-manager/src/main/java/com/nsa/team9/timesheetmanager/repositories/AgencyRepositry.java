package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyRepositry {

    public Agency save(Agency aAgency);

    public Optional<Agency> findById(Long agencyId);

}
