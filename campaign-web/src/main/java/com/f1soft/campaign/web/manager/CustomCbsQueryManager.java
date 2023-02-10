package com.f1soft.campaign.web.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.entities.model.CustomCBSQuery;
import com.f1soft.campaign.repository.CustomCbsQueryRepository;
import com.f1soft.campaign.web.dto.request.CustomCbsQueryRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

/**
 * @author Shreetika Panta
 */

@Slf4j
@Component
public class CustomCbsQueryManager {

    @Autowired
    private CustomCbsQueryRepository customCbsQueryRepository;

    public CustomCBSQuery findById(Long customCbsQueryId) {
        return customCbsQueryRepository.findCbsQueryById(customCbsQueryId)
                .orElseThrow(() -> new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.Data.NOT_FOUND)));
    }


    @SneakyThrows
    public ServerResponse databaseConnection(CustomCBSConnection cbsConnection, CustomCBSQuery cbsQuery){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(cbsConnection.getCbsDriverName());
             connection = DriverManager.getConnection(
                    cbsConnection.getCbsConnectionURL(),
                    cbsConnection.getCbsUsername(),
                    cbsConnection.getCbsPassword());
            statement = connection.prepareStatement(cbsQuery.getSqlQuery());
            ResultSet myRs = statement.executeQuery();
            log.info("SQL QUERY: " + cbsQuery.getSqlQuery());
            log.info("Results: " + myRs.toString());
            connection.close();
            return ResponseMsg.successResponse(MsgConstant.DATABASE_CONNECTION_ESTABLISHED);
        } catch (Exception e) {
            return ResponseMsg.failureResponse(MsgConstant.DATABASE_CONNECTION_FAILED);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public boolean checkIfAlreadyExist(CustomCbsQueryRequest cbsQueryRequest) {
        Optional<CustomCBSQuery> optCustomCbsQuery = customCbsQueryRepository.findByQueryCode(cbsQueryRequest.getQueryCode());
        if (optCustomCbsQuery.isPresent()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.QUERY_ALREADY_EXISTS));
        }
        return false;
    }

    public boolean save(CustomCBSQuery customCBSQuery) {
        customCbsQueryRepository.save(customCBSQuery);
        return true;
    }
}
