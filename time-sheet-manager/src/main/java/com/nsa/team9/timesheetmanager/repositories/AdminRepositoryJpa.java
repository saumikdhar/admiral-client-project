package com.nsa.team9.timesheetmanager.repositories;
import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepositoryJpa extends JpaRepository<TimeSheet, Long>, AdminRepository {

    /*Queries go here */

    /*finds all time sheets */
    @Query(value = "select * from timesheets join " +
            "agency_contractors ac on timesheets.agency_contractor_id = " +
            "ac.agency_contractor_id join contractors c2 on ac.contractor_id = " +
            "c2.contractor_id join managers m on c2.manager_id = m.manager_id", nativeQuery = true)
    public List<TimeSheet> getAllTimeSheets();

    /*finds all times sheets for an agency*/
    @Query(value = "select * from timesheets join " +
            "agency_contractors ac on timesheets.agency_contractor_id = " +
            "ac.agency_contractor_id join contractors c2 on ac.contractor_id =" +
            "c2.contractor_id join managers m on c2.manager_id = m.manager_id  " +
            "JOIN agencies a ON ac.agency_id = a.agency_id where a.agency_name like CONCAT('%',:searchTerm,'%')", nativeQuery = true)
    public List<TimeSheet> findTimeSheetsByAgencyName(@Param("searchTerm") String searchTerm);

}
