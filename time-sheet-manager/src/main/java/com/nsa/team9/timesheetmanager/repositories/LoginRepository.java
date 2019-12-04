package com.nsa.team9.timesheetmanager.repositories;


import com.nsa.team9.timesheetmanager.domain.Login;

public interface LoginRepository {

    /*inserts/save into data base*/
    public Login save(Login aLogin);

}
