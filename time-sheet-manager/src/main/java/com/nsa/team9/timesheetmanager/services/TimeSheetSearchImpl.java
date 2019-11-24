package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSheetSearchImpl implements TimeSheetSearch {

    private TimeSheetRespository timeSheetRepository;

    public TimeSheetSearchImpl(TimeSheetRespository aRepo) {
        timeSheetRepository = aRepo;
    }

    @Override
    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName) {
        return timeSheetRepository.getTimeSheetsByManager(lastName, firstName);
    }
}
