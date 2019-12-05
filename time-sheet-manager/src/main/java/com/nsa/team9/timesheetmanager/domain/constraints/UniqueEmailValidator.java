package com.nsa.team9.timesheetmanager.domain.constraints;
;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private LoginSearchImpl loginSearch;

    @Autowired
    public UniqueEmailValidator(LoginSearchImpl loginRepo){
        loginSearch = loginRepo;}

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && loginSearch.findEmailExists(value).isEmpty();
    }
}
