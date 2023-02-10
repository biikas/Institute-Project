package com.f1soft.campaign.web.campaign.service.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.CampaignStatusEnum;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.web.campaign.dto.request.campaign.CreateCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.ModifyCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.UpdateCampaignStatusRequest;
import com.f1soft.campaign.web.campaign.manager.CampaignManager;
import com.f1soft.campaign.web.campaign.service.CampaignCrudService;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
public class CampaignCrudServiceImpl implements CampaignCrudService {

    @Autowired
    private CampaignManager campaignManager;

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public ServerResponse createCampaign(CreateCampaignRequest createCampaignRequest) {
        if (createCampaignRequest.getPromoCode() != null) {
            campaignManager.checkIfPromoCodeExist(createCampaignRequest.getPromoCode());
        }

        return campaignManager.createCampaign(createCampaignRequest, LoginProvider.getApplicationUser());
    }

    @Override
    public ServerResponse modifyCampaign(ModifyCampaignRequest modifyCampaignRequest) {

        Optional<Campaign> campaignOptional = campaignRepository.findById(modifyCampaignRequest.getId());

        if (!campaignOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        if (modifyCampaignRequest.getPromoCode() != null || !modifyCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
            if (!campaignOptional.get().getPromoCode().equalsIgnoreCase(modifyCampaignRequest.getPromoCode())) {
                campaignManager.checkIfPromoCodeExist(modifyCampaignRequest.getPromoCode());
            }
        }
        return campaignManager.modifyCampaign(modifyCampaignRequest, campaignOptional.get(), LoginProvider.getApplicationUser());
    }

    @Override
    public ServerResponse updateCampaignStatus(UpdateCampaignStatusRequest updateCampaignStatusRequest) {

        Optional<Campaign> campaignOptional = campaignRepository.findById(updateCampaignStatusRequest.getId());

        if (!campaignOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        if (campaignOptional.get().getStatus().equalsIgnoreCase(CampaignStatusEnum.EXPIRED.name())) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.CAMPAIGN_EXPIRED));
        }
        if (campaignOptional.get().getStatus().equalsIgnoreCase(CampaignStatusEnum.COMPLETED.name())) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.CAMPAIGN_COMPLETED));
        }
        return campaignManager.updateCampaignStatus(campaignOptional.get(), LoginProvider.getApplicationUser(), updateCampaignStatusRequest);
    }

    @Override
    public ServerResponse delete(Long campaignId) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        if (!campaignOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        return campaignManager.deleteCampaign(campaignOptional.get());
    }
}
