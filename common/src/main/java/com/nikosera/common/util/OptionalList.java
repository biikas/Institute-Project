package com.nikosera.common.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class OptionalList<T> {

    private List<T> list;

    public OptionalList(List<T> list) {
        this.list = list;
    }

    public static <T> OptionalList<T> of(List<T> list) {
        return new OptionalList<>(list);
    }

    public <X extends Throwable> List<T> orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public static <T> OptionalList<T> from(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        List<T> list = new ArrayList<T>();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return new OptionalList<>(list);
    }
}
