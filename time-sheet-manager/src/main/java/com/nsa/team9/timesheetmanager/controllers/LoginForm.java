package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.constraints.ExtendedEmailValidator;
import com.nsa.team9.timesheetmanager.domain.constraints.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    private String email;

    private String password;

}
