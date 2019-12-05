package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepositoryJPA extends JpaRepository<Manager, Long>, ManagerRepository {

    @Query(value = "select * from managers m join logins l on l.login_id = m.login_id where l.email = :email",
            nativeQuery = true)
    public Optional<Manager> findManagerByEmail(@Param("email") String email);

    /*finds all managers*/
    @Query(value = "SELECT m FROM Manager m")
    List<Manager> findAllManagers();


}
