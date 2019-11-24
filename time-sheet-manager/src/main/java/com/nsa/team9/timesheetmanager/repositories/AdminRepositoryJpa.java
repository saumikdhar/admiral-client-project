package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.controllers.projections.TimeSheetFormAdminProjection;
import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepositoryJpa extends JpaRepository<TimeSheetFormAdminProjection, Long>, AdminRepository {

    /*Queries go here */

        @Query(value = "select c2.first_name, a from timesheets t join " +
            "agency_contractors ac on t.agency_contractor_id = " +
            "ac.agency_contractor_id join contractors c2 on ac.contractor_id = " +
            "c2.contractor_id join managers m on c2.manager_id = m.manager_id where " +
            "m.manager_firstName = :firstName and m.manager_lastName = :lastName and status = 'pending'", nativeQuery = true)
    public List<TimeSheetFormAdminProjection> getTimeSheetsByManager();

}
