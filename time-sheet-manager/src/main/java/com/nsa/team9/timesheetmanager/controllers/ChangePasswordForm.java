package com.nsa.team9.timesheetmanager.controllers;

import com.nsa.team9.timesheetmanager.domain.constraints.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {

    @PasswordValidator
    @NotBlank(message = "Current password should not be blank")
    private String currentPassword;

    @NotBlank(message = "New password mus not be blank")
    private String newPassword;

    @NotBlank(message = "Confirm password should not be blank")
    private String confirmPassword;

}
