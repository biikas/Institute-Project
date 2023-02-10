package com.f1soft.campaign.repository.Util;


import com.querydsl.core.types.Path;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Santosh Pun
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SearchParameter {

    //column name
    private String columnName;
    //jpa cloumn path
    private Path path;
    //column value
    private Object value;
    /*
    and, or
     */
    private String condition;
    // like and order
    private String operator;

    public SearchParameter() {
        this.path = null;
        this.value = null;
        this.condition = null;
        this.operator = null;
    }
}
