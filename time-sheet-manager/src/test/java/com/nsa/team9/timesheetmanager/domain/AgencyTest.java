package com.nsa.team9.timesheetmanager.domain;

import com.nsa.team9.timesheetmanager.repositories.AgencyRepositry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
@Transactional
@Sql({"/Schema.sql", "/data.sql"})
public class AgencyTest {
//
    @Autowired
    private AgencyRepositry agencyRepositry;
//
    @Test
    public void theOneWhereAgencyNameIsCassady(){
//        Agency agency = new Agency(99L, "Co");
//        agencyRepositry.save(agency);
        Optional<Agency> aAgency = agencyRepositry.findById(2L);
        assertEquals("Cassady Good", aAgency.get().getAgencyName());
    }
}