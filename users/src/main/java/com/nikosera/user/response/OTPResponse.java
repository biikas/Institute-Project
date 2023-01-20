package com.nikosera.user.response;

import com.nikosera.common.converter.CharacterStatusToBooleanConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Getter
@Setter
public class OTPResponse extends ModelBase {
    @JsonSerialize(converter = CharacterStatusToBooleanConverter.class)
    private Character isMfaEnabled;
    private String qr;
}
