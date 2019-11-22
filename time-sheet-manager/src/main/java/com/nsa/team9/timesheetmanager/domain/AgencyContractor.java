package com.nsa.team9.timesheetmanager.domain;

import javax.persistence.*;

public class AgencyContractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long agencyContractorId;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    public Agency agencyId;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    public Contractor contractorId;
}
