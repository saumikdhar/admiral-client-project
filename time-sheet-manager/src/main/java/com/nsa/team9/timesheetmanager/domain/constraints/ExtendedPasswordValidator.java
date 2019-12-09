package com.nsa.team9.timesheetmanager.domain.constraints;

import com.nsa.team9.timesheetmanager.config.security.MyUserPrincipal;
import com.nsa.team9.timesheetmanager.services.LoginSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExtendedPasswordValidator implements ConstraintValidator<PasswordValidator, String> {

    private LoginSearchImpl loginSearch;

    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    public ExtendedPasswordValidator(LoginSearchImpl loginRepo){
        loginSearch = loginRepo;
    }

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context, Authentication authentication) {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        boolean result = encoder.matches(value, loginSearch.findPasswordMatches(principal.getUser().getId()));
        System.out.println("does the password match ? " + result);
        return value != null && result;
    }
}
