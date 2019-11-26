package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyContractorRepositryJPA extends JpaRepository<AgencyContractor, Long>, AgencyContractorRepositry {
}
