package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomRedeemDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Shreetika Panta
 */
public class CustomRedeemDAOImpl implements CustomRedeemDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomRedeemDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomRedeemDTO> customRedeemDetail(String query) {
        return jdbcTemplate.query(query, CUSTOM_REDEEM_MAPPER);
    }

    private static final RowMapper<CustomRedeemDTO> CUSTOM_REDEEM_MAPPER = new RowMapper<CustomRedeemDTO>() {
        @Override
        public CustomRedeemDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            final CustomRedeemDTO customRedeemDTO = new CustomRedeemDTO();
            customRedeemDTO.setId(resultSet.getLong("id"));
            customRedeemDTO.setChannel(resultSet.getString("channel"));
            customRedeemDTO.setAccountNumber(resultSet.getString("accountNumber"));
            customRedeemDTO.setRecordedDate(resultSet.getDate("recordedDate"));
            customRedeemDTO.setMobileNumber(resultSet.getString("mobileNumber"));
            return customRedeemDTO;
        }
    };

}
