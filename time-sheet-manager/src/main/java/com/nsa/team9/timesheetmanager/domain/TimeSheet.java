package com.nsa.team9.timesheetmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timesheets")
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_id")
    private Long timesheet_id;

    @Column(name = "monday_worked")
    private boolean monday_worked;

    @Column(name = "tuesday_worked")
    private boolean tuesday_worked;

    @Column(name = "wednesday_worked")
    private boolean wednesday_worked;

    @Column(name = "thursday_worked")
    private boolean thursday_worked;

    @Column(name = "friday_worked")
    private boolean friday_worked;

    @Column(name = "saturday_worked")
    private boolean saturday_worked;

    @Column(name = "sunday_worked")
    private boolean sunday_worked;

    @Column(name = "overtime")
    private Double overtime;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "contractor_id")
    private Contractor contractorId;

}