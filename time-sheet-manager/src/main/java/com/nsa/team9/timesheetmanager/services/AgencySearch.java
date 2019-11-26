package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencySearch {

public void createAgency(Agency aAgency);

public Optional<Agency> findById(Long id);

public List<Agency> findAllAgencyNames();

public List<Agency> findAllAgency();
}
