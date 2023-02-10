package com.f1soft.campaign.common.cbs.factory;

import com.f1soft.campaign.common.cbs.dto.CbsQueryParameter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Sabrin Luitel
 */
public class JdbcTemplateFactory {
    
    public static JdbcTemplate getCbsJdbcTemplate(CbsQueryParameter cbsQueryParameter) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(cbsQueryParameter.getDataSourceParameter().getDriver());
        dataSource.setUrl(cbsQueryParameter.getDataSourceParameter().getUrl());
        dataSource.setUsername(cbsQueryParameter.getDataSourceParameter().getUser());
        dataSource.setPassword(cbsQueryParameter.getDataSourceParameter().getPassword());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(30);
        return jdbcTemplate;
    }
}
