package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepository {

    /*gets all time sheets*/
    public List<TimeSheet> getAllTimeSheets();

    /*finds all time sheets by agency name*/
    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm);

    /*finds all time sheets by contractor name*/
    public List<TimeSheet> findTimeSheetsByContractorName(String searchTerm);

    /*finds all time sheets specified by date*/
    public List<TimeSheet> findTimeSheetsByDate(LocalDate dateFrom,LocalDate dateTo);

    /*finds all contractors not assigned a manager*/
    public List<ContractorProjection> findContractorsNotAssignedManager();

    /*finds all contractors and managers assigned to them*/
    public List<ContractorProjection> findAllContractorsAndManagersAssociated();

    /*inserts/save into data base*/
    public Admin save(Admin aAdmin);

}
