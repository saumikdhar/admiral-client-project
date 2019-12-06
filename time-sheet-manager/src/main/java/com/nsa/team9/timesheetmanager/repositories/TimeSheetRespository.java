package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSheetRespository {

//    Find all Timesheets associated with a manager
    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName);

    public TimeSheet save(TimeSheet aTimeSheet);

//    update timesheet status
    public void updateTimesheetStatus(String status, Long timesheetId);

    // find all timesheets with a agency contractor id, start date and status
    public Optional<TimeSheet> CheckIfTimeSheetExists(LocalDate start_date, Long contractorId);
}
