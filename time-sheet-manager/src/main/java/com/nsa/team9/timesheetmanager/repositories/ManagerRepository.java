package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Manager;

import java.util.Optional;
import java.util.List;
public interface ManagerRepository {

    public Optional<Manager> findManagerByEmail(String email);
        
    /*finds all managers*/
    public List<Manager> findAllManagers();
}
