package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.util.List;

public interface TimeSheetSearch {

    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName);

<<<<<<< HEAD
    public void createTimeSheet(TimeSheet aTimeSheet);
=======
    public void updateTimesheetStatus(String status, Long timesheetId);
>>>>>>> managerSql-feature
}
