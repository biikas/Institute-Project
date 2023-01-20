package com.nikosera.common.validator;

import com.nikosera.common.annotation.ValidCharacter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class CharacterValidator implements ConstraintValidator<ValidCharacter, Character> {
    private char[] supported;

    @Override
    public void initialize(ValidCharacter constraintAnnotation) {
        supported = constraintAnnotation.supports();
    }

    @Override
    public boolean isValid(Character character, ConstraintValidatorContext constraintValidatorContext) {
        if(character == null){
            return true;
        }
        for (char c : supported) {
            if (character.charValue() == c) {
                return true;
            }
        }
        return false;
    }
}