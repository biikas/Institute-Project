package com.nikosera.common.validator;

import com.nikosera.common.annotation.CollectionSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class CollectionSizeValidator implements ConstraintValidator<CollectionSize, Collection> {

    private Long minimum;

    @Override
    public void initialize(CollectionSize constraintAnnotation) {
        this.minimum = constraintAnnotation.minimum();
    }

    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext constraintValidatorContext) {
        //leave null-checking to @NotNull on individual parameters
        if (collection == null) {
            return true;
        }

        if (collection.size() >= minimum) {
            return true;
        }
        return false;
    }
}