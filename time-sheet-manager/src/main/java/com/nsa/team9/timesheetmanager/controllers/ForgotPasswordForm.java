package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.constraints.ExtendedEmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordForm {

    @ExtendedEmailValidator
    private String emailAddress;

}
