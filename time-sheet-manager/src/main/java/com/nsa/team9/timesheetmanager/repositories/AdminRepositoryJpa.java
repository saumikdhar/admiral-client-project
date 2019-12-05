package com.nsa.team9.timesheetmanager.repositories;
import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepositoryJpa extends JpaRepository<TimeSheet, Long>, AdminRepository {

    /*Queries go here */

    /*finds all time sheets */
    @Query(value = "SELECT * FROM timesheets JOIN contractors c ON " +
            "timesheets.contractor_id = c.contractor_id " +
            "JOIN managers m ON c.manager_id = m.manager_id", nativeQuery = true)
    public List<TimeSheet> getAllTimeSheets();

    /*finds all time sheets for an agency*/
    @Query(value = "select * from timesheets join " +
            "agency_contractors ac on timesheets.agency_contractor_id = " +
            "ac.agency_contractor_id join contractors c2 on ac.contractor_id =" +
            "c2.contractor_id join managers m on c2.manager_id = m.manager_id  " +
            "JOIN agencies a ON ac.agency_id = a.agency_id where a.agency_name like CONCAT('%',:searchTerm,'%')", nativeQuery = true)
    public List<TimeSheet> findTimeSheetsByAgencyName(@Param("searchTerm") String searchTerm);

    /*finds all time sheets for a contractor*/
    @Query(value = "select * from timesheets join " +
            "agency_contractors ac on timesheets.agency_contractor_id = " +
            "ac.agency_contractor_id join contractors c2 on ac.contractor_id =" +
            "c2.contractor_id join managers m on c2.manager_id = m.manager_id  " +
            "JOIN contractors c ON ac.contractor_id = c.contractor_id where CONCAT(c.contractor_first_name,' ', c.contractor_last_name) like CONCAT('%',:searchTerm,'%')", nativeQuery = true)
    public List<TimeSheet> findTimeSheetsByContractorName(@Param("searchTerm") String searchTerm);

    /*query to find all time sheets in date range*/
    @Query(value = "select * from timesheets t where t.start_date between :dateFrom and :dateTo", nativeQuery = true)
    public List<TimeSheet> findTimeSheetsByDate(@Param("dateFrom")LocalDate dateFrom,@Param("dateTo")LocalDate dateTo );

    /*query finds all contractors without a manager assigned to them*/
    @Query(value = "SELECT agencies.agency_name as AgencyName, c.contractor_id as ContractorId, CONCAT(c.contractor_first_name, ' ', c.contractor_last_name) as ContractorName, concat(m.manager_first_name, ' ', m.manager_last_name) as ManagerName FROM agencies JOIN contractors c ON agencies.agency_id= c.agency_id JOIN managers m ON c.manager_id = m.manager_id WHERE concat(manager_first_name, ' ', manager_last_name) LIKE '%Not Assigned%'",nativeQuery = true)
    public List<ContractorProjection> findContractorsNotAssignedManager();
    
    /*query gets all relevant information for contractors and managers assigned to them*/
    @Query(value = "SELECT agencies.agency_name as AgencyName, c.contractor_id as ContractorId, CONCAT(c.contractor_first_name, ' ', c.contractor_last_name) as ContractorName," +
            " concat(m.manager_first_name, ' ', m.manager_last_name) as ManagerName FROM agencies " +
            "JOIN contractors c ON agencies.agency_id= c.agency_id " +
            "JOIN managers m ON c.manager_id = m.manager_id " +
            "group by ContractorName",nativeQuery = true)
    public List<ContractorProjection> findAllContractorsAndManagersAssociated();
}
