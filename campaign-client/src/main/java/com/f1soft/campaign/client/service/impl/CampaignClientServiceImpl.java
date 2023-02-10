package com.f1soft.campaign.client.service.impl;

import com.f1soft.campaign.client.dto.CampaignOfferDTO;
import com.f1soft.campaign.client.dto.GiftCardDTO;
import com.f1soft.campaign.client.dto.request.GiftCardFetchRequest;
import com.f1soft.campaign.client.dto.request.OfferFetchRequest;
import com.f1soft.campaign.client.dto.response.CampaignResponse;
import com.f1soft.campaign.client.dto.response.GiftCardResponse;
import com.f1soft.campaign.client.manager.UserGiftCardManager;
import com.f1soft.campaign.client.manager.UserOfferManager;
import com.f1soft.campaign.client.mapper.CampaignMapper;
import com.f1soft.campaign.client.mapper.GiftCardUserMapper;
import com.f1soft.campaign.client.service.CampaignClientService;
import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.common.constant.ResponseCodeConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.GiftCard;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.repository.GiftCardDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajwol hada
 */
@Slf4j
@Service
public class CampaignClientServiceImpl implements CampaignClientService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private UserOfferManager userOfferManager;
    @Autowired
    private GiftCardDetailRepository giftCardDetailRepository;
    @Autowired
    private UserGiftCardManager userGiftCardManager;

    @Override
    public ServerResponse campaignList() {
        ServerResponse serverResponse = new ServerResponse();

        String imagePath = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_IMAGE_PATH);

        List<Campaign> campaignList = campaignRepository.getAllActiveCampaignList();

        List<CampaignOfferDTO> campaignResponseList = campaignList.stream()
                .map(campaign -> CampaignMapper.convertToCampaignResponse(campaign, imagePath))
                .collect(Collectors.toList());

        serverResponse.setSuccess(true);
        serverResponse.setCode(ResponseCodeConstant.SUCCESS);
        serverResponse.setObj(new CampaignResponse(campaignResponseList));
        serverResponse.setMessage("Campaign List obtained successfully");
        return serverResponse;
    }

    @Override
    public ServerResponse campaignListByUser(OfferFetchRequest offerFetchRequest) {
        ServerResponse serverResponse = new ServerResponse();

        String imagePath = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_IMAGE_PATH);

        List<Campaign> campaignList = campaignRepository.getAllActiveCampaignList();

        List<CampaignOfferDTO> campaignResponseList = campaignList.stream()
                .filter(f -> userOfferManager.checkOfferEligibility(f, offerFetchRequest))
                .map(campaign -> CampaignMapper.convertToCampaignResponse(campaign, imagePath))
                .collect(Collectors.toList());

        serverResponse.setSuccess(true);
        serverResponse.setCode(ResponseCodeConstant.SUCCESS);
        serverResponse.setObj(new CampaignResponse(campaignResponseList));
        serverResponse.setMessage("Campaign List obtained successfully");
        return serverResponse;
    }

    @Override
    public ServerResponse userGiftCards(GiftCardFetchRequest giftCardFetchRequest) {

        ServerResponse serverResponse = new ServerResponse();

        List<GiftCard> giftCardList = giftCardDetailRepository.findAllActive();

        List<GiftCardDTO> giftCardResponseList = giftCardList.stream()
                .filter(f -> userGiftCardManager.checkGiftCardEligibility(f, giftCardFetchRequest))
                .map(giftCard -> GiftCardUserMapper.mapToGiftCardDTO(giftCard))
                .collect(Collectors.toList());

        serverResponse.setSuccess(true);
        serverResponse.setCode(ResponseCodeConstant.SUCCESS);
        serverResponse.setObj(new GiftCardResponse(giftCardResponseList));
        serverResponse.setMessage("Gift Card obtained successfully");
        return serverResponse;
    }


}
