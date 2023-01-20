package com.nikosera.common.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class CharacterStatusToBooleanConverter extends StdConverter<Character, Boolean> {

    @Override
    public Boolean convert(Character status) {
        if (status.compareTo('Y') == 0) {
            return true;
        } else if (status.compareTo('N') == 0) {
            return false;
        }
        throw new IllegalArgumentException("Not supported");
    }
}
