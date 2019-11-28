package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.util.List;

public interface TimeSheetRespository {

//    Find all Timesheets associated with a manager
    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName);

    public TimeSheet save(TimeSheet aTimeSheet);

//    update timesheet status
    public void updateTimesheetStatus(String status, Long timesheetId);

}
