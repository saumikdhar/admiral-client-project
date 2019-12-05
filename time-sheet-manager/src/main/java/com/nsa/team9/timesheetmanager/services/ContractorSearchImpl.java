package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Contractor;
import com.nsa.team9.timesheetmanager.repositories.ContractorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;

@Slf4j
@Service
public class ContractorSearchImpl implements ContractorSearch {

    private ContractorRepository contractorRepository;

    public ContractorSearchImpl(ContractorRepository aRepo){
        contractorRepository = aRepo;
    }

    @Transactional
    @Override
    public void createContractor(Contractor contractor) {
        contractorRepository.save(contractor);
    }

    @Override
    public Optional<Contractor> findContractorById(Long contractorId) {
        return contractorRepository.findContractorById(contractorId);
    }

    @Override
    public void updateContractorManager(Long contractorId, Long managerId) {
        contractorRepository.updateContractorManager(contractorId,managerId);
    }
    @Override
    public Optional<Contractor> findContractorByEmail(String email) {
        return contractorRepository.findContractorByEmail(email);
    }
}
