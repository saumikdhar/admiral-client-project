package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoginRepositoryJPA extends JpaRepository<Login, Long>, LoginRepository {

    @Query(value = "select * from Logins where email = :email", nativeQuery = true)
    public Optional<Login> getLoginByEmail(@Param("email") String email);

    @Query(value = "select l.email from Login l where l.email = :email ")
    public Optional<Login> findEmailExists(@Param("email") String email);

    @Query(value = "select l.password from Login l where l.id = :loginId")
    public String findPasswordMatches(@Param("loginId") Long loginId);
}
