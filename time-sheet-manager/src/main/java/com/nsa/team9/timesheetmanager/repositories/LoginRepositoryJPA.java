package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepositoryJPA extends JpaRepository<Login, Long>, LoginRepository {

    @Query(value = "select * from Logins where email = :email", nativeQuery = true)
    public Optional<Login> getLoginByEmail(@Param("email") String email);
}
