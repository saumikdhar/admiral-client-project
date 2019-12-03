package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TimeSheetRepositoryJpa extends JpaRepository<TimeSheet, Long>, TimeSheetRespository {
    /*Queries go in here*/

//  Gets all time sheets where status is pending and for the relevant manager
    @Query(value = "select * from timesheets t join contractors c on t.contractor_id = " +
        "c.contractor_id join managers m on c.manager_id = m.manager_id where " +
        "m.manager_first_name = :firstName and m.manager_last_name = :lastName and status like '%pending%'", nativeQuery = true)
    public List<TimeSheet> getTimeSheetsByManager(@Param("lastName") String lastName,@Param("firstName") String firstName);

    @Modifying
    @Transactional
    @Query(value = "update timesheets set status = :status where timesheet_id = :timesheetId", nativeQuery = true)
    void updateTimesheetStatus(@Param("status") String status, @Param("timesheetId") Long timesheetId);
}
