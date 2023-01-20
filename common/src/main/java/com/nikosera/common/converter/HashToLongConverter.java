package com.nikosera.common.converter;

import com.nikosera.common.service.IdHasherService;
import com.nikosera.common.util.StaticContextAccessor;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class HashToLongConverter extends StdConverter<String, Long> {

    private final IdHasherService hasher = StaticContextAccessor.getBean(IdHasherService.class);

    @SneakyThrows
    @Override
    public Long convert(String s) {

        if (!ObjectUtils.isEmpty(s)) {
            return hasher.decode(s);
        }
        return null;
    }
}
