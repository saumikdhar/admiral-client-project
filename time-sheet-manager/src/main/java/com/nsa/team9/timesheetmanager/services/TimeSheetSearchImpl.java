package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.TimeSheetRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TimeSheetSearchImpl implements TimeSheetSearch {

    private TimeSheetRespository timeSheetRepository;

    public TimeSheetSearchImpl(TimeSheetRespository aRepo) {
        timeSheetRepository = aRepo;
    }

    @Override
    @Transactional
    public void createTimeSheet(TimeSheet aTimeSheet){
        timeSheetRepository.save(aTimeSheet);
    }

    @Override
    public List<TimeSheet> getTimeSheetsByManager(String lastName, String firstName) {
        return timeSheetRepository.getTimeSheetsByManager(lastName, firstName);
    }

    @Override
    public void updateTimesheetStatus(String status, Long timesheetId) {
        timeSheetRepository.updateTimesheetStatus(status, timesheetId);
    }

    @Override
    public Optional<TimeSheet> CheckIfTimeSheetExists(LocalDate startDate, Long contractorId){
        return timeSheetRepository.CheckIfTimeSheetExists(startDate,contractorId);
    }

    @Override
    public List<TimeSheet> getTimeSheetsByContractor(Long contractorId) {
        return timeSheetRepository.getTimeSheetsByContractor(contractorId);
    }

    @Override
    public List<TimeSheet> getAllTimeSheetsByManager(String lastName, String firstName) {
        return timeSheetRepository.getAllTimeSheetsByManager(lastName, firstName);
    }

    @Override
    public List<TimeSheet> getAllTimeSheetsByManagerAndDate(String lastName, String firstName, LocalDate dateFrom, LocalDate dateTo) {
        return timeSheetRepository.getAllTimeSheetsByManagerAndDate(lastName, firstName, dateFrom, dateTo);

    }
}
