package com.f1soft.campaign.common.cbs.dto;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Rashim Dhaubanjar
 */

@Getter
@Setter
public class CbsQueryParameter extends ModelBase {

    private String code;
    private String sql;
    private DataSourceParameter dataSourceParameter;
}
