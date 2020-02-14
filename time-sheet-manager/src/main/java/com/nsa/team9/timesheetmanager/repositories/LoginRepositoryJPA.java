package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface LoginRepositoryJPA extends JpaRepository<Login, Long>, LoginRepository {

    @Query(value = "select * from logins where email = :email", nativeQuery = true)
    public Optional<Login> getLoginByEmail(@Param("email") String email);

    @Query(value = "select l.email from Login l where l.email = :email ")
    public Optional<Login> findEmailExists(@Param("email") String email);

    /*Query to update user password*/
    @Modifying
    @Transactional
    @Query(value = "update Login l set l.password = :newPassword where l.id = :loginId")
    public void updateUserPassword(@Param("loginId") Long loginId, @Param("newPassword") String newPassword);
}
