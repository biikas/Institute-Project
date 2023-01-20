package com.nikosera.common.annotation;


import com.nikosera.common.validator.CollectionSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollectionSizeValidator.class)
@Documented
public @interface CollectionSize {

    long minimum() default 1L;

    String message() default "Invalid Status Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
