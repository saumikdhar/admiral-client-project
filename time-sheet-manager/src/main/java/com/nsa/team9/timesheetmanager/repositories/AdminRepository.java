package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.util.List;

public interface AdminRepository {

    /*gets all time sheets*/
    public List<TimeSheet> getAllTimeSheets();
}
