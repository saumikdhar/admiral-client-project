package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Login;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoginRepository {

    public Optional<Login> findById(Long loginId);
    public Optional<Login> getLoginByEmail(String email);

    /*inserts/save into data base*/
    public Login save(Login aLogin);

    /*finds all login by email*/
    public Optional<Login>findEmailExists(String email);

    /*finds the login and checks if the password matches*/
    public String findPasswordMatches(Long loginId);

}
