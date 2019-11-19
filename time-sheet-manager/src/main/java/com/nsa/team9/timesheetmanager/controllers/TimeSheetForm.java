package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    @NotNull
    @Past(message = "Start date cannot be present or in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;


    private Boolean monday_worked;


    private Boolean tuesday_worked;


    private Boolean wednesday_worked;


    private Boolean thursday_worked;


    private Boolean friday_worked;

    @NotNull(message = "Invalid overtime hours")
    //@Size(min = 0, max = 2, message = "Invalid overtime hours")
    @Min(0)
    @Max(20)
    private Integer overtime;

}
