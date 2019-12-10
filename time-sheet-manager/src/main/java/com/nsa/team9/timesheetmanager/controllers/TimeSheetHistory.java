package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetHistory {


        @NotNull
        @PastOrPresent(message = "Start date cannot be in the future")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate start_date;

        private Boolean monday_worked;

        private Boolean tuesday_worked;

        private Boolean wednesday_worked;

        private Boolean thursday_worked;

        private Boolean friday_worked;

        private Boolean saturday_worked;

        private Boolean sunday_worked;

        @DecimalMin(value = "0.0", message = "The minimum amount of overtime cannot be less than 0")
        @DecimalMax(value = "20.0",message = "The max amount of overtime is 20 hours")
        private Double overtime;

    }

