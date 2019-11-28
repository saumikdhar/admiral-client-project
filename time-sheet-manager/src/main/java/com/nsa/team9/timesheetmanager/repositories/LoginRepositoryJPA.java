package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginRepositoryJPA extends JpaRepository<Login, Long>, LoginRepository {

    @Query(value = "select * from Logins", nativeQuery = true)
    public List<Login> getAllLogins();
}
