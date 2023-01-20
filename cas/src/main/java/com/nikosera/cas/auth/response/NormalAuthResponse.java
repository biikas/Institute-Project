package com.nikosera.cas.auth.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nikosera.common.converter.CharacterStatusToBooleanConverter;
import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class NormalAuthResponse extends ModelBase {
    @JsonIgnore
    private String token;
    @JsonSerialize(converter = CharacterStatusToBooleanConverter.class)
    private Character isMfaEnabled;
    private Date accessTime;
    private Date accessExpiryTime;
}
