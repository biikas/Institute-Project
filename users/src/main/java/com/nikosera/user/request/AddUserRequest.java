package com.nikosera.user.request;

import com.nikosera.common.annotation.HasNoneLeadingTrailingWhitespace;
import com.nikosera.common.constant.ValidationConstant;
import com.nikosera.common.converter.HashToLongConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Getter
@Setter
public class AddUserRequest extends ModelBase {
    @NotBlank
    @Pattern(regexp = ValidationConstant.ALPHANUMERIC_SPACE)
    @Size(max = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = ValidationConstant.ALPHANUMERIC_UNDERSCORE_DASH)
    @Size(max = 20)
    private String username;

    @Pattern(regexp = ValidationConstant.PASSWORD)
    @Size(max = 20)
    private String password;

    @NotBlank
    @Pattern(regexp = ValidationConstant.ONLY_AUTO_OR_MANUAL)
    @HasNoneLeadingTrailingWhitespace
    private String passwordMode;

    @NotBlank
    @Email
    @HasNoneLeadingTrailingWhitespace
    private String email;

    @NotNull
    @HasNoneLeadingTrailingWhitespace
    @Pattern(regexp = ValidationConstant.CONTACT_NO_PATTERN)
    private String contactNo;

    @NotNull
    @JsonDeserialize(converter = HashToLongConverter.class)
    private Long userGroupId;
}
