package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;

import java.util.List;

public interface AgencySearch {

public void createAgency(Agency aAgency);

public List<Agency> findById(Long id);
}
