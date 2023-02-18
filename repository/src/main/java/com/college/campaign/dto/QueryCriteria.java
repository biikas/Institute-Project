package com.college.campaign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <krishna.pandey@college.com>
 */
@Getter
@Setter
public class QueryCriteria extends ModelBase {

    private String whereClause;
    private String joinClause;
}