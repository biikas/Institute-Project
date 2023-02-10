package com.f1soft.campaign.web.notification.service.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;
import com.f1soft.campaign.web.notification.factory.NotificationFactory;
import com.f1soft.campaign.web.notification.service.FCMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class FCMServiceImpl implements FCMService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private NotificationFactory notificationFactory;

    @Override
    public ServerResponse sendFCMMessage(NotificationRequest notificationRequest) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(notificationRequest.getCampaignId());

        if (!campaignOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        return notificationFactory.getFCMNotification(campaignOptional.get(),notificationRequest.getTest()).sendNotification(notificationRequest, campaignOptional.get());
    }
}
