package com.f1soft.campaign.common.cbs.dto;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Rashim Dhaubanjar
 */
@Getter
@Setter
public class DataSourceParameter extends ModelBase {

    private String url;
    private String driver;
    private String user;
    private String password;
}
