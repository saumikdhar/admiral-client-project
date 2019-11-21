package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.repositories.TimeSheetRespository;
import org.springframework.stereotype.Service;

@Service
public class TimeSheetSearchImpl implements TimeSheetSearch {

    private TimeSheetRespository timeSheetRepository;
}
