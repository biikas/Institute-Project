package com.nikosera.common.formatter;

import com.nikosera.common.annotation.Unhash;
import com.nikosera.common.exception.InvalidRequestException;
import com.nikosera.common.service.IdHasherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
public class UnhashFormatterFactory implements AnnotationFormatterFactory<Unhash> {

    private IdHasherService idHasherService;

    public UnhashFormatterFactory(IdHasherService idHasherService) {
        this.idHasherService = idHasherService;
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Arrays.asList(Long.class));
    }

    @Override
    public Printer<?> getPrinter(Unhash unHash, Class<?> aClass) throws InvalidRequestException {
        return new HashIdFormatter(idHasherService);
    }

    @Override
    public Parser<?> getParser(Unhash unHash, Class<?> aClass) {
        return new HashIdFormatter(idHasherService);
    }
}
