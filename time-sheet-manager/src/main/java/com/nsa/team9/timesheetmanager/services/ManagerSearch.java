package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Manager;


import java.util.List;
import java.util.Optional;


public interface ManagerSearch {

    /*finds all managers*/
    List<Manager> findAllManagers();

    public Optional<Manager> findManagerByEmail(String email);


    /*creates a login account*/
    public void createManager(Manager aManager);

}
