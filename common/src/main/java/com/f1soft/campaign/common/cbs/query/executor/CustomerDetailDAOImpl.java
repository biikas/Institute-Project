package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Shreetika Panta
 */
public class CustomerDetailDAOImpl implements CustomerDetailDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDetailDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomerDetailDTO> customerDetail(String query) {
        return jdbcTemplate.query(query, CUSTOMER_PROFILE_MAPPER);
    }

    private static final RowMapper<CustomerDetailDTO> CUSTOMER_PROFILE_MAPPER = new RowMapper<CustomerDetailDTO>() {
        @Override
        public CustomerDetailDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            final CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
            customerDetailDTO.setId(resultSet.getLong("id"));
            customerDetailDTO.setAccountNumber(resultSet.getString("accountNumber"));
            customerDetailDTO.setUsername(resultSet.getString("username"));
            customerDetailDTO.setRegistrationDate(resultSet.getDate("registrationDate"));
            customerDetailDTO.setProfileId(resultSet.getLong("profileId"));
            customerDetailDTO.setAccountType(resultSet.getString("accountType"));
            customerDetailDTO.setChannel(resultSet.getString("channel"));
            customerDetailDTO.setCustomerName(resultSet.getString("customerName"));
            return customerDetailDTO;
        }
    };

}
