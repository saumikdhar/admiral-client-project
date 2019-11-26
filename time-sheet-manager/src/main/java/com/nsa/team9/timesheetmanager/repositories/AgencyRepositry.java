package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyRepositry {

    /*Save agency to DB*/
    public Agency save(Agency aAgency);

    /*find agency by agency id*/
    public Optional<Agency> findById(Long agencyId);

    /*find all agencies by agency name*/
    public List<Agency> findAllAgencyNames();

    /*find all agencies by agency*/
    public List<Agency> findAllAgency();

}
