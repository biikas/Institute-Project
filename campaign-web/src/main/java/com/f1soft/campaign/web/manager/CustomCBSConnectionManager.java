package com.f1soft.campaign.web.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.repository.CustomCbsConnectionRepository;
import com.f1soft.campaign.web.dto.request.CustomCBSConnectionRequest;
import com.f1soft.campaign.web.dto.request.TestCBSConnectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

@Component
public class CustomCBSConnectionManager {

    @Autowired
    private CustomCbsConnectionRepository customCbsConnectionRepository;

    public boolean checkIfAlreadyExist(CustomCBSConnectionRequest customCBSConnectionRequest) {
        Optional<CustomCBSConnection> optCustomCBSConnection = customCbsConnectionRepository.findByConnectionUrl(customCBSConnectionRequest.getCbsConnectionURL());
        if (optCustomCBSConnection.isPresent()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.QUERY_ALREADY_EXISTS));
        }
        return false;

    }

    public boolean save(CustomCBSConnection customCBSConnection) {
        customCbsConnectionRepository.save(customCBSConnection);
        return true;
    }

    public CustomCBSConnection findById(Long eid) {
        return customCbsConnectionRepository.findById(eid)
                .orElseThrow(() -> new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.Data.NOT_FOUND)));
    }

    public ServerResponse databaseConnection(TestCBSConnectionRequest testCBSConnectionRequest) {
        try {
            Class.forName(testCBSConnectionRequest.getCbsDriverName());
            Connection connection = DriverManager.getConnection(
                    testCBSConnectionRequest.getCbsConnectionURL(),
                    testCBSConnectionRequest.getCbsUsername(),
                    testCBSConnectionRequest.getCbsPassword());

            if (!connection.isClosed()) {
                connection.close();
                return ResponseMsg.successResponse(MsgConstant.DATABASE_CONNECTION_ESTABLISHED);
            } else {
                connection.close();
                return ResponseMsg.failureResponse(MsgConstant.DATABASE_CONNECTION_FAILED);
            }

        } catch (Exception e) {
            return ResponseMsg.failureResponse(MsgConstant.DATABASE_CONNECTION_FAILED);
        }
    }
}
