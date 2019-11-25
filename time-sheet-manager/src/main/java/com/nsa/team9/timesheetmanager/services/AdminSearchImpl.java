package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.TimeSheet;
import com.nsa.team9.timesheetmanager.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSearchImpl implements AdminSearch {

    private AdminRepository adminRepository;

    @Autowired
    public AdminSearchImpl(AdminRepository aRepo){adminRepository =aRepo;}

    public List<TimeSheet> getAllTimeSheets() {
        return adminRepository.getAllTimeSheets();
    }

    public List<TimeSheet> findTimeSheetsByAgencyName(String searchTerm){
        return adminRepository.findTimeSheetsByAgencyName(searchTerm);
    }

    @Override
    public List<TimeSheet> findTimeSheetsByContractorName(String searchTerm) {
        return adminRepository.findTimeSheetsByContractorName(searchTerm);
    }
}
