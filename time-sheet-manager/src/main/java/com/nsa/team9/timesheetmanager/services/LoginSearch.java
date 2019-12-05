package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Login;

import java.util.List;
import java.util.Optional;

public interface LoginSearch {

    public Optional<Login> findById(Long id);

    public List<Login> findAllLoginEmails();

    public Optional<Login> getLoginByEmail(String email);

    /*creates a login account*/
    public void createLogin(Login aLogin);

}
