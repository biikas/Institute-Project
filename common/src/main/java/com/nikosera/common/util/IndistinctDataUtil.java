package com.nikosera.common.util;

import com.nikosera.common.exception.IndistinctDataException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class IndistinctDataUtil {

    public static <T> void handleIndistinctData(List<T> list,
                                                Function<? super T, ?> keyExtractor,
                                                String indistinctMessage) {
        Long count = list.stream()
                .map(keyExtractor)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .values()
                .stream()
                .filter(x -> x > 1)
                .count();

        if (count > 0) {
            throw new IndistinctDataException(indistinctMessage);
        }
    }
}
