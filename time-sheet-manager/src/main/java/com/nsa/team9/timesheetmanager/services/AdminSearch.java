package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.AgencyProjection;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdminSearch {

    /*finds all time sheets*/
    public List<TimeSheet> getAllTimeSheets();

    /*finds time sheets by agency name*/
    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm);

    /*finds time sheets by contractor name*/
    public List<TimeSheet> findTimeSheetsByContractorName(String searchTerm);

    /*finds time sheets by date*/
    public List<TimeSheet> findTimeSheetsByDate(LocalDate dateFrom, LocalDate dateTo);

    /*finds all contractors without a manager assigned to them*/
    public List<AgencyProjection> findContractorsNotAssignedManager();

    /*finds all contractors with a manager*/
    public List<Agency> findContractorsAssignedWithManager();

    /*finds all managers*/
    public List<Manager> findAllManagers();
}
