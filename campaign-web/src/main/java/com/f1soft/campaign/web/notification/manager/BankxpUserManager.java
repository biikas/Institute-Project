package com.f1soft.campaign.web.notification.manager;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.notification.connector.UserDetailConnector;
import com.f1soft.campaign.web.notification.dto.HttpResponse;
import com.f1soft.campaign.web.notification.dto.UserDeviceRequest;
import com.f1soft.campaign.web.notification.dto.UserDeviceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BankxpUserManager {

    public static final String USER_DEVICE = "v2/user/device";

    @Autowired
    UserDetailConnector userDetailConnector;


    public ServerResponse userDevice(String username) {
        log.debug("Requesting user device detail using username : {}", username);
        return userDetailConnector.request(
                USER_DEVICE,
                new UserDeviceRequest(username),
                new ParameterizedTypeReference<HttpResponse<UserDeviceResponse>>() {
                });
    }
}
