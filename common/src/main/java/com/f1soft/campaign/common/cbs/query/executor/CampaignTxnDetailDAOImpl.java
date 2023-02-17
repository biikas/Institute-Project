package com.f1soft.campaign.common.cbs.query.executor;


import com.f1soft.campaign.common.cbs.dto.TransactionDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Shreetika Panta
 */
public class CampaignTxnDetailDAOImpl implements CampaignTxnDetailDAO {

    private final JdbcTemplate jdbcTemplate;

    public CampaignTxnDetailDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TransactionDTO> campaignTxnDetail(String query) {
        return jdbcTemplate.query(query, CUSTOMER_PROFILE_MAPPER);
    }

    @Override
    public List<TransactionDTO> campaignTxnDetailWithoutProduct(String query) {
        return jdbcTemplate.query(query, CUSTOMER_PROFILE_MAPPER);
    }

    private static final RowMapper<TransactionDTO> CUSTOMER_PROFILE_MAPPER = new RowMapper<TransactionDTO>() {
        @Override
        public TransactionDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            final TransactionDTO customerDetailDTO = new TransactionDTO();
            customerDetailDTO.setId(resultSet.getLong("id"));
            customerDetailDTO.setAccountNumber(resultSet.getString("accountNumber"));
            customerDetailDTO.setUsername(resultSet.getString("username"));
            customerDetailDTO.setTransactionCode(resultSet.getString("serviceCode"));
            customerDetailDTO.setProfileId(resultSet.getLong("profileId"));
            customerDetailDTO.setTransactionDate(resultSet.getDate("transactionDate"));
            customerDetailDTO.setAmount(resultSet.getDouble("amount"));
            customerDetailDTO.setChannel(resultSet.getString("channel"));
            customerDetailDTO.setCustomerName(resultSet.getString("customerName"));
            return customerDetailDTO;
        }
    };

}
