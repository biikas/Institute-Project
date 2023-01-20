package com.nikosera.user.request;

import com.nikosera.common.annotation.CollectionSize;
import com.nikosera.common.annotation.HasNoneLeadingTrailingWhitespace;
import com.nikosera.common.constant.ValidationConstant;
import com.nikosera.common.converter.HashListToLongListConverter;
import com.nikosera.common.converter.HashToLongConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class AddUserGroupRequest extends ModelBase {

    @NotBlank
    @Pattern(regexp = ValidationConstant.ALPHANUMERIC_SPACE)
    @HasNoneLeadingTrailingWhitespace
    @Size(max = 50)
    private String name;

    @NotBlank
    @HasNoneLeadingTrailingWhitespace
    @Pattern(regexp = ValidationConstant.XSS_SCRIPT)
    @Size(max = 255)
    private String description;

    @HasNoneLeadingTrailingWhitespace
    @Pattern(regexp = ValidationConstant.XSS_SCRIPT)
    @Size(max = 255)
    private String remarks;

    @JsonDeserialize(converter = HashToLongConverter.class)
    private Long parentId;

    @CollectionSize
    @NotNull
    @JsonDeserialize(converter = HashListToLongListConverter.class)
    private List<Long> permissions;
}
