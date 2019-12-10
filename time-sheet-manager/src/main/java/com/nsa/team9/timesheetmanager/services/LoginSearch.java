package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Login;

import java.util.Optional;

public interface LoginSearch {

    public Optional<Login> findById(Long id);

    public Optional<Login> getLoginByEmail(String email);

    /*creates a login account*/
    public void createLogin(Login aLogin);

    /*finds all be email*/
    public Optional<Login>findEmailExists(String email);

    /*updates password for the user signed in*/
    public void updateUserPassword(Long loginId, String newPassword);


}
