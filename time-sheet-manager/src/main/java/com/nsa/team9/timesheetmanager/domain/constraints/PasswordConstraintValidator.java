package com.nsa.team9.timesheetmanager.domain.constraints;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import com.google.common.base.Joiner;

import static org.apache.tomcat.util.buf.StringUtils.join;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30), //checks the length is between 8 and 30
                new UppercaseCharacterRule(1),// checks there is atleast 1 upper case letter
                new DigitCharacterRule(1),// checks there is atleast 1 number
                new SpecialCharacterRule(1),//checks there is atleast 1 special character
                new NumericalSequenceRule(3,false),// checks the numerical sequence e.g. if it was true 111 would be invalid
                new AlphabeticalSequenceRule(3,false),// checks the alphabetical sequence e.g. if it was true aaa would be invalid
                new QwertySequenceRule(3,false), // checks the qwerty sequence
                new WhitespaceRule()));//checks if there is any whitespace

        RuleResult result = validator.validate(new PasswordData(password));// create variable to check the password input by user
        if (result.isValid()) {
            return true;//return as a valid password, it meets requirements
        }
        context.disableDefaultConstraintViolation();//stops default errror messages
        context.buildConstraintViolationWithTemplate( // show the custom error messages
                Joiner.on(",").join(validator.getMessages(result))) //get the error messages and join them
                .addConstraintViolation();//make it a constraint validator
        return false;//return as invalid, it doesn't meet the requirements
    }
}
