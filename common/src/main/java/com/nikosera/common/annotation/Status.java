package com.nikosera.common.annotation;


import com.nikosera.common.validator.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusValidator.class)
@Documented
public @interface Status {

    String message() default "Invalid Status Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
