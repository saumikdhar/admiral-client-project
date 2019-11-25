package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TimeSheetRepositoryJpa extends JpaRepository<TimeSheet, Long>, TimeSheetRespository {
    /*Queries go in here*/

//  Gets all time sheets where status is pending and for the relevant manager
    @Query(value = "select * from timesheets join " +
        "agency_contractors ac on timesheets.agency_contractor_id = " +
        "ac.agency_contractor_id join contractors c2 on ac.contractor_id = " +
        "c2.contractor_id join managers m on c2.manager_id = m.manager_id where " +
        "m.manager_first_name = :firstName and m.manager_last_name = :lastName and status = 'pending'", nativeQuery = true)
    public List<TimeSheet> getTimeSheetsByManager(@Param("lastName") String lastName,@Param("firstName") String firstName);
}
