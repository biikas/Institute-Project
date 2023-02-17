package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Service
public class ProfileDAOImpl implements ProfileDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProfileDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomerProfileDTO> customerProfile(String query) {
        return jdbcTemplate.query(query, CUSTOMER_PROFILE_MAPPER);
    }

    private static final RowMapper<CustomerProfileDTO> CUSTOMER_PROFILE_MAPPER = new RowMapper<CustomerProfileDTO>() {
        @Override
        public CustomerProfileDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            final CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();
            customerProfileDTO.setId(resultSet.getLong("id"));
            customerProfileDTO.setName(resultSet.getString("name"));
            return customerProfileDTO;
        }
    };
}
