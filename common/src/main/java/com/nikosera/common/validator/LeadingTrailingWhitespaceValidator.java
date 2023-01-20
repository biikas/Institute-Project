package com.nikosera.common.validator;

import com.nikosera.common.annotation.HasNoneLeadingTrailingWhitespace;
import com.nikosera.common.constant.ValidationConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class LeadingTrailingWhitespaceValidator implements ConstraintValidator<HasNoneLeadingTrailingWhitespace, String> {

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        //leave null-checking to @NotNull on individual parameters
        if(string == null){
            return true;
        }
        Pattern pattern = Pattern.compile(ValidationConstant.LEADING_TRAILING_WHITESPACE);
        Matcher matcher = pattern.matcher(string);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return false;
        } else {
            return true;
        }
    }
}
