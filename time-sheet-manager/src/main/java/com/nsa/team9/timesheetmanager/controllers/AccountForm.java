package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.constraints.ExtendedEmailValidator;
import com.nsa.team9.timesheetmanager.domain.constraints.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {

    @ExtendedEmailValidator
    @UniqueEmail(message = "This Email is already taken")
    private String emailAddress;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 12,message = "Password must be between 6 and 12 characters long")
    private String password;

    private Integer accessLevel;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    private Long managerId;

    private Long agencyId;

    private Long contractorId;
}
