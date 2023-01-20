package com.nikosera.common.validator;

import com.nikosera.common.annotation.IsValidHash;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class IsHashValidator implements ConstraintValidator<IsValidHash, Long> {

    public boolean isValid(Long id, ConstraintValidatorContext constraintContext) {
        //leave null-checking to @NotNull on individual parameters
        if(id == null){
            return true;
        }
        // Formatter sends -1 if invalid
        if(id > 0){
            return true;
        }

        return false;
    }
}