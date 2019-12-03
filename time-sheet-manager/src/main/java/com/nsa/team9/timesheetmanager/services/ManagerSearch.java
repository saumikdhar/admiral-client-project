package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Manager;

import java.util.Optional;

public interface ManagerSearch {

    public Optional<Manager> findManagerByEmail(String email);
}
