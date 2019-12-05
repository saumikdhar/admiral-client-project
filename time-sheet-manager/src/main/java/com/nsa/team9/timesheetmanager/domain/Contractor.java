package com.nsa.team9.timesheetmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contractors")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractor_id")
    public Long id;

    @Column(name = "contractor_first_name")
    public String firstName;

    @Column(name = "contractor_last_name")
    public String lastName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "login_id")
    private Login login;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "agency_id")
    private Agency agencyId;
}
