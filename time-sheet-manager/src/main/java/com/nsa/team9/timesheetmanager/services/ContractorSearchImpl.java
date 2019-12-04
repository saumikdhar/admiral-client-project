package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Contractor;
import com.nsa.team9.timesheetmanager.repositories.ContractorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ContractorSearchImpl implements ContractorSearch {

    ContractorRepository contractorRepository;

    public ContractorSearchImpl(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Optional<Contractor> findContractorByEmail(String email) {
        return contractorRepository.findContractorByEmail(email);
    }
}
