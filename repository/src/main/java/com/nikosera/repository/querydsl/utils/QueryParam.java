package com.nikosera.repository.querydsl.utils;

import com.nikosera.repository.querydsl.OperatorEnum;
import com.querydsl.core.types.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class QueryParam {

    private Object value;
    private Path path;
    private OperatorEnum operator;
    private OperatorEnum condition;

}
