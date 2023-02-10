package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CustomCbsQueryDTO extends ModelBase {

    private Long id;
    private String query;
    private String queryCode;
    private String queryName;

    private String cbsDistribution;
    private String queryDescription;
    private Long cbsConnectionId;
    private String sqlQuery;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private char active;
    private String cbsConnectionURL;
}
