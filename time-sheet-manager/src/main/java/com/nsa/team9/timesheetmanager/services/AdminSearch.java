package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;

import java.time.LocalDate;
import java.util.List;

public interface AdminSearch {

    /*finds all time sheets*/
    public List<TimeSheet> getAllTimeSheets();

    /*finds time sheets by agency name*/
    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm);

    /*finds time sheets by contractor name*/
    public List<TimeSheet> findTimeSheetsByContractorName(String searchTerm);

    /*finds time sheets by date*/
    public List<TimeSheet> findTimeSheetsByDate(LocalDate dateFrom, LocalDate dateTo);

    /*finds all contractors without a manager assigned to them*/
    public List<ContractorProjection> findContractorsNotAssignedManager();

    /*finds all contractors and managers assigned to them*/
    public List<ContractorProjection> findAllContractorsAndManagersAssociated();


    /*creates a admin account*/
    public void createAdmin(Admin aAdmin);

}
