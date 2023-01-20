package com.nikosera.user.request;

import com.nikosera.common.converter.BooleanStatusToCharacterConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class UserGroupSearchRequest extends ModelBase {

    private String name;
    @JsonDeserialize(converter = BooleanStatusToCharacterConverter.class)
    private Character active;
}
