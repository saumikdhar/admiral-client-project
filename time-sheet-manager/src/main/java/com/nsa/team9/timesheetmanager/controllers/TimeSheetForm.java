package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    @NotNull
    @Past(message = "Start date cannot be present or in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;


    private Boolean monday_worked;


    private Boolean tuesday_worked;


    private Boolean wednesday_worked;


    private Boolean thursday_worked;


    private Boolean friday_worked;

<<<<<<< HEAD
    @NotNull(message = "Invalid overtime hours")
    //@Size(min = 0, max = 2, message = "Invalid overtime hours")
    @Min(value = 0)
    @Max(20)
=======
    @Min(value = 0, message = "The minimum amount of overtime cannot be less than 0")
    @Max(value = 20,message = "The max amount of overtime is 20 hours")
>>>>>>> e7c631d4ce181c2a4b5f8715fe580e62b20b187c
    private Double overtime;

}
