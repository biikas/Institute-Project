package com.nikosera.repository.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class SpecificationFactory<T> {

    public Specification<T> like(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.LIKE, Collections.singletonList(arg)).build();
    }
}
