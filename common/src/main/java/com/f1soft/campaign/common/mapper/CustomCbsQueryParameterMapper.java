package com.f1soft.campaign.common.mapper;


import com.f1soft.campaign.common.cbs.dto.CbsQueryParameter;
import com.f1soft.campaign.common.cbs.dto.DataSourceParameter;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.entities.model.CustomCBSQuery;

/**
 * @author Prajwol Hada
 */
public class CustomCbsQueryParameterMapper {

    public static CbsQueryParameter convertToCustomCbsQueryParameter(CustomCBSQuery cbsQuery) {
        CbsQueryParameter cbsQueryParameter = new CbsQueryParameter();
        cbsQueryParameter.setCode(cbsQuery.getQueryCode());
        cbsQueryParameter.setSql(cbsQuery.getSqlQuery());
        cbsQueryParameter.setDataSourceParameter(convertToCustomDataSourceParameter(cbsQuery.getCustomCbsConnection()));
        return cbsQueryParameter;
    }

    public static DataSourceParameter convertToCustomDataSourceParameter(CustomCBSConnection cbsConnection) {
        DataSourceParameter dataSourceParameter = new DataSourceParameter();
        dataSourceParameter.setUrl(cbsConnection.getCbsConnectionURL());
        dataSourceParameter.setDriver(cbsConnection.getCbsDriverName());
        dataSourceParameter.setUser(cbsConnection.getCbsUsername());
        dataSourceParameter.setPassword(cbsConnection.getCbsPassword());
        return dataSourceParameter;
    }
}
