package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepositoryJpa extends JpaRepository<Login, Long>, LoginRepository  {

    /*queries go here */
}
