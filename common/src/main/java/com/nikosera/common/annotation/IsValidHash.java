package com.nikosera.common.annotation;

import com.nikosera.common.validator.IsHashValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsHashValidator.class)
@Documented
public @interface IsValidHash {

    String message() default "Field value should be from list of ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
