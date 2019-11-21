package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AgencySearchImpl implements AgencySearch {

    private AgencyRepositry agencyRepositry;

    public AgencySearchImpl(AgencyRepositry aRepo){agencyRepositry = aRepo;}

    @Override
    @Transactional
    public void createAgency(Agency aAgency) {
        agencyRepositry.save(aAgency);
//        Log.debug(aAgency.toString());


    }

    @Override
    public List<Agency> findById(Long agencyId) {
        return agencyRepositry.findById(agencyId);
    }
}
