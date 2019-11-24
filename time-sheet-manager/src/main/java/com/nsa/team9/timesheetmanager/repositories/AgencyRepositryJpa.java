package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgencyRepositryJpa extends JpaRepository<Agency, Long>, AgencyRepositry{

    @Query(value = "SELECT a.agencyName FROM Agency a ")
    public List<Agency> findAllAgencyNames();
}
