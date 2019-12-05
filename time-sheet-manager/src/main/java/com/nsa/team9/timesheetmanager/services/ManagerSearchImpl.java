package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Manager;
import com.nsa.team9.timesheetmanager.repositories.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ManagerSearchImpl implements ManagerSearch{

    private ManagerRepository managerRepository;

    public ManagerSearchImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Optional<Manager> findManagerByEmail(String email) {
        return managerRepository.findManagerByEmail(email);
    }

    @Override
    public List<Manager> findAllManagers() {
        return managerRepository.findAllManagers();
    
    }

    @Transactional
    @Override
    public void createManager(Manager aManager) {
        managerRepository.save(aManager);
    }
}
