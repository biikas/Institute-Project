package com.nikosera.repository.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private SearchOperation searchOperation;
    private boolean isOrOperation;
    private List<Object> arguments;
}