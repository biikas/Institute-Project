package com.f1soft.campaign.common.mapper;


import com.f1soft.campaign.common.cbs.dto.CbsQueryParameter;
import com.f1soft.campaign.common.cbs.dto.DataSourceParameter;
import com.f1soft.campaign.entities.model.CBSConnection;
import com.f1soft.campaign.entities.model.CBSQuery;

/**
 * @author Prajwol Hada
 */
public class CbsQueryParameterMapper {

    public static CbsQueryParameter convertToCbsQueryParameter(CBSQuery cbsQuery) {
        CbsQueryParameter cbsQueryParameter = new CbsQueryParameter();
        cbsQueryParameter.setCode(cbsQuery.getQueryCode());
        cbsQueryParameter.setSql(cbsQuery.getSqlQuery());
        cbsQueryParameter.setDataSourceParameter(convertToDataSourceParameter(cbsQuery.getCbsConnection()));
        return cbsQueryParameter;
    }

    public static DataSourceParameter convertToDataSourceParameter(CBSConnection cbsConnection) {
        DataSourceParameter dataSourceParameter = new DataSourceParameter();
        dataSourceParameter.setUrl(cbsConnection.getCbsConnectionURL());
        dataSourceParameter.setDriver(cbsConnection.getCbsDriverName());
        dataSourceParameter.setUser(cbsConnection.getCbsUsername());
        dataSourceParameter.setPassword(cbsConnection.getCbsPassword());
        return dataSourceParameter;
    }
}
