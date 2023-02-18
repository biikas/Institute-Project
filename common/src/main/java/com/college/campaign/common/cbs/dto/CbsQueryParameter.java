package com.college.campaign.common.cbs.dto;


import com.college.campaign.common.dto.ModelBase;
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
