package com.nikosera.common.converter;

import com.nikosera.common.service.IdHasherService;
import com.nikosera.common.util.StaticContextAccessor;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.util.ObjectUtils;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class LongToHashConverter extends StdConverter<Long, String> {

    private final IdHasherService hasher = StaticContextAccessor.getBean(IdHasherService.class);

    @Override
    public String convert(Long id) {

        if (!ObjectUtils.isEmpty(id)) {
            return hasher.encode(id);
        }
        return "";
    }
}
