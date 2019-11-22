package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepositryJpa extends JpaRepository<Agency, Long>, AgencyRepositry{
}
