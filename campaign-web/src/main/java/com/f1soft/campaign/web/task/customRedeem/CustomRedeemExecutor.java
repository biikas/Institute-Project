package com.f1soft.campaign.web.task.customRedeem;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.cbs.dto.CustomRedeemDTO;
import com.f1soft.campaign.common.manager.CustomQueryManager;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.*;
import com.f1soft.campaign.web.mapper.CustomRedeemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Shreetika Panta
 */

@Component
public class CustomRedeemExecutor {

    @Autowired
    private CustomQueryManager queryManager;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private CampaignOfferRepository campaignOfferRepository;
    @Autowired
    private CustomLogTrackerRepository customLogTrackerRepository;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private CustomCampaignEligibleUserRepository customCampaignEligibleUserRepository;

    @Transactional
    public CampaignJob processFetchData(Campaign campaign) {
        Long trackId = customLogTrackerRepository.getTrackerId(campaign.getId());

        if (trackId == null) {
            trackId = 0L;
        }
        List<CustomRedeemDTO> customRedeemDTOS = queryManager.getCustomRedeemDetail(campaign, trackId);

        if (customRedeemDTOS.size() > 0) {
            for (CustomRedeemDTO customRedeemDTO : customRedeemDTOS) {
                Optional<CustomCampaignEligibleUser> optCampaignEligibleUser = customCampaignEligibleUserRepository.findByUsernameAndCampaignId(customRedeemDTO.getMobileNumber(), campaign.getId());
                if (!optCampaignEligibleUser.isPresent()) {

                    CampaignOffer campaignOffer = campaignOfferRepository.findByCampaignId(campaign.getId()).get(0);
                    Channel channel = channelRepository.getChannelByCode(customRedeemDTO.getChannel()).get();

                    OfferTransaction offerTransaction = CustomRedeemMapper.convertToSaveOfferTransaction(campaign, channel, customRedeemDTO, campaignOffer);
                    offerTransactionRepository.save(offerTransaction);

                    CustomCampaignEligibleUser campaignEligibleUser = CustomRedeemMapper.convertToSaveCustomCampaignEligibleUser(campaign, customRedeemDTO);
                    customCampaignEligibleUserRepository.save(campaignEligibleUser);
                }
            }
            CustomLogTracker customLogTracker = CustomRedeemMapper.convertToCustomRedeemMapper(customRedeemDTOS.get(0), campaign);
            customLogTrackerRepository.save(customLogTracker);
        }
        return new CampaignJob(true, "Data fetched successfully.");
    }
}
