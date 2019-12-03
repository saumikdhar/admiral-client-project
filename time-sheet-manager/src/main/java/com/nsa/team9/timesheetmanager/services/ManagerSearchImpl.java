package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Manager;
import com.nsa.team9.timesheetmanager.repositories.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ManagerSearchImpl implements ManagerSearch {

    private ManagerRepository managerRepository;

    public ManagerSearchImpl(ManagerRepository aRepo) {
        managerRepository = aRepo;
    }


    @Override
    public List<Manager> findAllManagers() {
        return managerRepository.findAllManagers();
    }
}
