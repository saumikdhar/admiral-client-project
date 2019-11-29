package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.domain.Contractor;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
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

}
