package com.f1soft.campaign.common.manager.inquiry;

import com.f1soft.campaign.common.cbs.dto.*;
import com.f1soft.campaign.common.cbs.factory.JdbcTemplateFactory;
import com.f1soft.campaign.common.cbs.query.executor.*;
import com.f1soft.campaign.common.util.QueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
@Qualifier("local")
public class LocalCustomCbsManager implements CustomEnquiryManager {


    @Override
    public List<CustomRedeemDTO> customRedeemDetail(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        String query = QueryHelper.convertedSql(cbsQueryParameter.getSql(), queryParameter);
        JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getCbsJdbcTemplate(cbsQueryParameter);
        CustomRedeemDAO customRedeemDAO = new CustomRedeemDAOImpl(jdbcTemplate);
        return customRedeemDAO.customRedeemDetail(query);
    }
}
