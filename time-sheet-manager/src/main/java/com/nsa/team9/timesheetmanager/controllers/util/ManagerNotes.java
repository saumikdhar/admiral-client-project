package com.nsa.team9.timesheetmanager.controllers.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerNotes {

    @NotNull

    @Size(min=2, max=200, message = "Invalid Number of Characters")
    private String notes;
}
