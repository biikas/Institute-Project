package com.f1soft.campaign.repository.Util;

import com.querydsl.core.types.Path;
import lombok.Getter;
import lombok.Setter;

/*
 * @Author Rashim Dhaubanjar
 */
@Getter
@Setter
public class SearchField {
    private Path path;
    private String columnName;
    private String operator;
    private String condition;

    public SearchField(Path path, String operator, String condition) {
        this.path = path;
        this.operator = operator;
        this.condition = condition;
    }

    public SearchField(String columnName, String operator, String condition) {
        this.columnName = columnName;
        this.operator = operator;
        this.condition = condition;
    }
}
