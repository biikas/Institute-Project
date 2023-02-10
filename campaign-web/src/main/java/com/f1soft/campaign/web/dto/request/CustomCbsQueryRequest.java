package com.f1soft.campaign.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomCbsQueryRequest {

    private char active;
    private String cbsDistribution;
    private String queryCode;
    private String queryDescription;
    private String sqlQuery;
    private Long cbsConnectionId;

}
