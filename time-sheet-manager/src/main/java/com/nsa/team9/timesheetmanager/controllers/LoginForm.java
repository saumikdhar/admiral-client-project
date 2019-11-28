package com.nsa.team9.timesheetmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}
