package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSheetSearch {

    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName);

    public void createTimeSheet(TimeSheet aTimeSheet);
    public void updateTimesheetStatus(String status, Long timesheetId);

    public Optional<TimeSheet> CheckIfTimeSheetExists(LocalDate start_date);
}
