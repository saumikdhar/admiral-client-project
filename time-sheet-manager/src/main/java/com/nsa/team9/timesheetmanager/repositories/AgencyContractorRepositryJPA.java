package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Agency;
import com.nsa.team9.timesheetmanager.domain.AgencyContractor;
import com.nsa.team9.timesheetmanager.domain.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AgencyContractorRepositryJPA extends JpaRepository<AgencyContractor, Long>, AgencyContractorRepositry {

    /*Find any if agencycontractor has a link between agency and contractor already*/
    @Query(value = "SELECT * FROM agency_contractors WHERE agency_id = :agencyId AND contractor_id = :contractorId" ,nativeQuery = true)
    public Optional<AgencyContractor> findAgencyContractorExists(@Param("agencyId") Long agencyId,@Param("contractorId") Long contractorId);
}
