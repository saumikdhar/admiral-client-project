package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepository {

    /*gets all time sheets*/
    public List<TimeSheet> getAllTimeSheets();

    /*finds all time sheets by agency name*/
    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm);

    /*finds all time sheets by contractor name*/
    public List<TimeSheet> findTimeSheetsByContractorName(String searchTerm);

    /*finds all time sheets specified by date*/
    public List<TimeSheet> findTimeSheetsByDate(LocalDate dateFrom,LocalDate dateTo);
}
