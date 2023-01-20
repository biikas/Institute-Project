package com.nikosera.common.util;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class OptionalIterator<T>{

    private List<T> list;

    public OptionalIterator(List<T> list) {
        this.list = list;
    }

    public static <T> OptionalIterator<T> of(List<T> list) {
        return new OptionalIterator<>(list);
    }

    public <X extends Throwable> List<T> orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        } else {
            throw exceptionSupplier.get();
        }
    }
}
