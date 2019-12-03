package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepositoryJpa  extends JpaRepository<Manager, Long>, ManagerRepository {

    /*finds all managers*/
    @Query(value = "SELECT m FROM Manager m")
    List<Manager> findAllManagers();

}
