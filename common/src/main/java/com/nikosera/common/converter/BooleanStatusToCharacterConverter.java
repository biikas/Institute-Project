package com.nikosera.common.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class BooleanStatusToCharacterConverter extends StdConverter<Boolean, Character> {

    @Override
    public Character convert(Boolean status) {
        return status ? 'Y' : 'N';
    }
}
