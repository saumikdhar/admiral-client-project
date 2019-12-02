package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Manager;

import java.util.List;

public interface ManagerSearch {

    /*finds all managers*/
    List<Manager> findAllManagers();

}
