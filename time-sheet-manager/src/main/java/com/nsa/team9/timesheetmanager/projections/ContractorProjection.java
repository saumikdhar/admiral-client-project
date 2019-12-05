package com.nsa.team9.timesheetmanager.projections;

import com.nsa.team9.timesheetmanager.domain.Agency;

public interface ContractorProjection {
    String getAgencyName();
    Long getContractorId();
    String getContractorName();
    Long getManagerIs();
    String getManagerName();
}
