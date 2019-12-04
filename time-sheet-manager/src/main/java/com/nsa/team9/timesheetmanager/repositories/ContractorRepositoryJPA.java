package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContractorRepositoryJPA extends JpaRepository<Contractor, Long>, ContractorRepository{

    @Query(value = "select * from contractors c join logins l on c.login_id = l.login_id where l.email = :email",
            nativeQuery = true)
    public Optional<Contractor> findContractorByEmail(@Param("email") String email);
}
