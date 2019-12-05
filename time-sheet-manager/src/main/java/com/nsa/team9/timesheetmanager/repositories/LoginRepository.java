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
}
