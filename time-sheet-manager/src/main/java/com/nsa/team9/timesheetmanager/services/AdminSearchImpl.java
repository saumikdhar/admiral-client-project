package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.*;
import com.nsa.team9.timesheetmanager.projections.ContractorProjection;
import com.nsa.team9.timesheetmanager.repositories.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdminSearchImpl implements AdminSearch {

    private AdminRepository adminRepository;

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

    @Override
    public List<TimeSheet> findTimeSheetsByDate(LocalDate dateFrom,LocalDate dateTo ) {
        return adminRepository.findTimeSheetsByDate(dateFrom, dateTo);
    }

    @Override
    public List<ContractorProjection> findContractorsNotAssignedManager() {
        return adminRepository.findContractorsNotAssignedManager();
    }

    @Override
    public List<ContractorProjection> findAllContractorsAndManagersAssociated() {
        return adminRepository.findAllContractorsAndManagersAssociated();
    }

    @Transactional
    @Override
    public void createAdmin(Admin aAdmin) {
        adminRepository.save(aAdmin);
    }


}
