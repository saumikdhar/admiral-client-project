package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    @NotNull
    private Date start_date;


    private Boolean monday_worked;


    private Boolean tuesday_worked;


    private Boolean wednesday_worked;


    private Boolean thursday_worked;


    private Boolean friday_worked;

    @NotNull
    @Size(min = 0, max = 20, message = "Invalid overtime hours")
    private int overtime;

}
