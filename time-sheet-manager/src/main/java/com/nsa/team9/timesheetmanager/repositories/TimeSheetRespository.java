package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetRespository {

//    Find all pending Timesheets associated with a manager
    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName);

    public TimeSheet save(TimeSheet aTimeSheet);

//    update timesheet status
    public void updateTimesheetStatus(String status, Long timesheetId);

//    Get all Timesheets associated with a manager
    public List<TimeSheet> getAllTimeSheetsByManager(String lastName, String firstName);

//   Get all timesheets for manager depending on date
    public List<TimeSheet> getAllTimeSheetsByManagerAndDate( String lastName,  String firstName, LocalDate dateFrom, LocalDate dateTo );


}
