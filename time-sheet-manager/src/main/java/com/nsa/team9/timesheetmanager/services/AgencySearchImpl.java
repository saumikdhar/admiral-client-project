package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AgencySearchImpl implements AgencySearch {

    private AgencyRepositry agencyRepositry;

    @Autowired
    public AgencySearchImpl(AgencyRepositry aRepo){agencyRepositry = aRepo;}

    @Override
    @Transactional
    public void createAgency(Agency aAgency) {
        agencyRepositry.save(aAgency);
//        Log.debug(aAgency.toString());


    }

    @Override
    public Optional<Agency> findById(Long agencyId) {
        return agencyRepositry.findById(agencyId);
    }

    @Override
    public List<Agency> findAllAgencyNames() {
        return agencyRepositry.findAllAgencyNames();
    }

    @Override
    public List<Agency> findAllAgency() {
        return agencyRepositry.findAllAgency();
    }
}
