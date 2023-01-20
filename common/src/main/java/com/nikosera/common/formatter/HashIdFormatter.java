package com.nikosera.common.formatter;

import com.nikosera.common.service.IdHasherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.util.Locale;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@AllArgsConstructor
public class HashIdFormatter implements Formatter<Long> {

    private final IdHasherService idHasherService;

    @Override
    public Long parse(String hash, Locale locale) {
        // Checks if the String hash is a type of number
        if (StringUtils.isNumeric(hash)) {
            return -1L;
        }
        final Long decoded = idHasherService.decode(hash);
        return decoded;
    }

    @Override
    public String print(Long aLong, Locale locale) throws ParseException {
        return idHasherService.encode(aLong);
    }
}
