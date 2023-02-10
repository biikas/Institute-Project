package com.f1soft.campaign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Getter
@Setter
public class QueryCriteria extends ModelBase {

    private String whereClause;
    private String joinClause;
}