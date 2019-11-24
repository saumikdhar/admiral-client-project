package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSearchImpl implements AdminSearch {

    private AdminRepository adminRepository;

    public AdminSearchImpl(AdminRepository aRepo){adminRepository =aRepo;}


    @Override
    public List<TimeSheet> getAllTimeSheets() {
        return adminRepository.getAllTimeSheets();
    }
}
