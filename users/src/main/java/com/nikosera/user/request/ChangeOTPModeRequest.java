package com.nikosera.user.request;

import com.nikosera.common.converter.BooleanStatusToCharacterConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class ChangeOTPModeRequest extends ModelBase {

    @NotBlank
    private String password;

    @NotNull
    @JsonDeserialize(converter = BooleanStatusToCharacterConverter.class)
    private Character enabled;

    private String token;
}
