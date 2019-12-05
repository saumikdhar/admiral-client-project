package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {

    @Email(message = "Invalid email please ensure it follows example@gmail.com format")
    private String emailAddress;

    @Size(min = 6, max = 12,message = "password must be between 6 and 12 characters long")
    private String password;

    private Integer accessLevel;

    private String firstName;

    private String lastName;

    private Long managerId;

    private Long agencyId;

    private Long contractorId;
}
