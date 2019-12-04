package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Manager;

import java.util.List;

public interface ManagerRepository {

    /*finds all managers*/
    List<Manager> findAllManagers();

    /*inserts/save into data base*/
    public Manager save(Manager aManager);
}
