package com.nikosera.common.converter;

import com.nikosera.common.service.IdHasherService;
import com.nikosera.common.util.StaticContextAccessor;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class HashListToLongListConverter extends StdConverter<List<String>, List<Long>> {

    private final IdHasherService hasher = StaticContextAccessor.getBean(IdHasherService.class);

    @SneakyThrows
    @Override
    public List<Long> convert(List<String> strings) {
        return strings.stream().map((s) -> {
            try {
                return hasher.decode(s);
            } catch (Exception e) {
                throw new RuntimeException("Type mismatched");
            }
        }).collect(Collectors.toList());
    }
}