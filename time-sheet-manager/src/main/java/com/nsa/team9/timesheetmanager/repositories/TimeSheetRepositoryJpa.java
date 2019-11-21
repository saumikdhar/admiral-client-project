package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepositoryJpa extends JpaRepository<TimeSheet, Long>, TimeSheetRespository {
    /*Queries go in here*/
}
