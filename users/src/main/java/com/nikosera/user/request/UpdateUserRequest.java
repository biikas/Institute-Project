package com.nikosera.user.request;

import com.nikosera.common.annotation.HasNoneLeadingTrailingWhitespace;
import com.nikosera.common.annotation.Status;
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
public class UpdateUserRequest extends ModelBase {

    @NotNull
    @Status
    private Character active;

    @NotBlank
    @Pattern(regexp = ValidationConstant.ALPHANUMERIC_SPACE)
    @HasNoneLeadingTrailingWhitespace
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email
    private String emailAddress;

    @NotNull
    @HasNoneLeadingTrailingWhitespace
    @Pattern(regexp = ValidationConstant.CONTACT_NO_PATTERN)
    private String contactNo;

    @NotNull
    @JsonDeserialize(converter = HashToLongConverter.class)
    private Long userGroupId;

}
