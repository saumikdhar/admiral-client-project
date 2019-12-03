package com.nsa.team9.timesheetmanager.repositories;

import com.nsa.team9.timesheetmanager.domain.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContractorRepsoitoryJpa  extends JpaRepository<Contractor, Long>, ContractorRepository{

    /*queries go here*/


    /*QUERY TO UPDATE CONTRACTOR MANAGER*/
    @Modifying
    @Transactional
    @Query(value = "update contractors set manager_id = :managerId where contractor_id = :contractorId", nativeQuery = true)
    void updateContractorManager(@Param("contractorId") Long contractorId, @Param("managerId") Long managerId);
}
