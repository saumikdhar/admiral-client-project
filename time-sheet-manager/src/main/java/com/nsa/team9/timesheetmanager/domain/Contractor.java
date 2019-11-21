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
    public Long id;

    @Column(name = "contractor_firstName")
    public String firstName;

    @Column(name = "contractor_lastName")
    public String lastName;


//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "login_id")
//    private Login login;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
