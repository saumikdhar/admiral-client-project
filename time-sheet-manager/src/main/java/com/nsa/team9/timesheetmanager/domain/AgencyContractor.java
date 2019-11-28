package com.nsa.team9.timesheetmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Agency_Contractors")
public class AgencyContractor {

    @Id
    @Column(name = "agency_contractor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long agencyContractorId;

    @ManyToOne()
    @JoinColumn(name = "agency_id")
    public Agency agencyId;

    @ManyToOne()
    @JoinColumn(name = "contractor_id")
    public Contractor contractorId;
}
