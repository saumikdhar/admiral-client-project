package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;

import java.util.List;

public interface AgencyRepositry {

    public Agency save(Agency aAgency);

    public List<Agency> findById(Long agencyId);

}
