package com.nikosera.user.request;

import com.nikosera.common.annotation.HasNoneLeadingTrailingWhitespace;
import com.nikosera.common.constant.ValidationConstant;
import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Getter
@Setter
public class UpdateUserProfileRequest extends ModelBase {

    @NotBlank
    @Pattern(regexp = ValidationConstant.ALPHANUMERIC_SPACE)
    @HasNoneLeadingTrailingWhitespace
    @Size(max = 50)
    private String name;

    @NotNull
    @HasNoneLeadingTrailingWhitespace
    @Pattern(regexp = ValidationConstant.CONTACT_NO_PATTERN)
    private String contactNo;
}
