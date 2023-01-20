package com.nikosera.common.annotation;

import com.nikosera.common.validator.LeadingTrailingWhitespaceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LeadingTrailingWhitespaceValidator.class)
@Documented
public @interface HasNoneLeadingTrailingWhitespace {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
