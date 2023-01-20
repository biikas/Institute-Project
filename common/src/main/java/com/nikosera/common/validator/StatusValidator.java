package com.nikosera.common.validator;

import com.nikosera.common.annotation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class StatusValidator implements ConstraintValidator<Status, Character> {

    @Override
    public boolean isValid(Character status, ConstraintValidatorContext constraintContext) {
        //leave null-checking to @NotNull on individual parameters
        if(status == null){
            return true;
        }

        if (status.equals('Y') || status.equals('N'))
            return true;
        else {
            return false;
        }
    }
}