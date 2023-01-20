package com.nikosera.common.annotation;


import com.nikosera.common.validator.CharacterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharacterValidator.class)
@Documented
public @interface ValidCharacter {

    char[] supports() default {};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
