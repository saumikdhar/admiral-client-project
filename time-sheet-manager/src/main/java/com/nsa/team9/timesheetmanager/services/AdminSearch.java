package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.util.List;

public interface AdminSearch {

    /*finds all time sheets*/
    public List<TimeSheet> getAllTimeSheets();

    /*finds time sheets by agency name*/
    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm);
}
