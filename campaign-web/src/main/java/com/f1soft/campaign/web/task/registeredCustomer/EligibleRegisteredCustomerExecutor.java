package com.f1soft.campaign.web.task.registeredCustomer;

import com.f1soft.campaign.client.mapper.TransactionMapper;
import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.manager.QueryManager;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.*;
import com.f1soft.campaign.web.mapper.EligibleCustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Shreetika Panta
 */

@Slf4j
@Component
public class EligibleRegisteredCustomerExecutor {

    @Autowired
    private QueryManager queryManager;
    @Autowired
    private RegistrationLogTrackerRepository registrationLogTrackerRepository;
    @Autowired
    private RegistrationCampaignRepository registrationCampaignRepository;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private DataPackageCampaignUserRepository dataPackageCampaignUserRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private CustomLogTrackerRepository customLogTrackerRepository;

    @Transactional
    public CampaignJob processFetchCustomer(Campaign campaign) {
        Long trackId = customLogTrackerRepository.getTrackerId(campaign.getId());

        if (trackId == null) {
            trackId = 0L;
        }

        List<CustomerDetailDTO> customerDetailDTOS = queryManager.getRegisteredCustomerDetail(campaign, trackId);

        log.debug("Fetched Customer size : {}", customerDetailDTOS.size());

        if (customerDetailDTOS.size() > 0) {
            saveRegistrationCampaignUser(customerDetailDTOS, campaign);
            saveRegistrationLogTracker(customerDetailDTOS, campaign);
            saveOfferTransaction(customerDetailDTOS, campaign);
        }
        return new CampaignJob(true, "Data fetched successfully.");
    }

    private void saveOfferTransaction(List<CustomerDetailDTO> customerDetailDTOS, Campaign campaign) {
        customerDetailDTOS.stream()
                .map(customerDetailDTO -> {
                    Channel channel = channelRepository.getChannelByCode(customerDetailDTO.getChannel()).get();
                    saveOfferTxnForCampaignOffer(customerDetailDTO, campaign, channel);
                    return customerDetailDTO;
                }).collect(Collectors.toList());
    }

    private void saveOfferTxnForCampaignOffer(CustomerDetailDTO customerDetailDTO, Campaign campaign, Channel channel) {
        campaign.getCampaignOffer().stream()
                .map(campaignOffer -> {
                    OfferTransaction offerTransaction = EligibleCustomerMapper.saveOfferTxn(customerDetailDTO, campaign, campaignOffer, channel);
                    offerTransactionRepository.save(offerTransaction);
                    DataPackageCampaignUser dataPackageCampaignUser = EligibleCustomerMapper.convertToDataPackageCampaignUser(offerTransaction, customerDetailDTO, campaign, campaignOffer);
                    if (dataPackageCampaignUser != null) {
                        dataPackageCampaignUserRepository.save(dataPackageCampaignUser);
                    }
                    return campaignOffer;
                }).collect(Collectors.toList());
    }

    private void saveRegistrationLogTracker(List<CustomerDetailDTO> customerDetailDTOS, Campaign campaign) {
        CustomerDetailDTO customerDetailDTO = customerDetailDTOS.get(0);
        CustomLogTracker customLogTracker = new CustomLogTracker();
        customLogTracker.setTrackId(customerDetailDTO.getId());
        customLogTracker.setCampaignId(campaign.getId());

        customLogTrackerRepository.save(customLogTracker);
    }

    private void saveRegistrationCampaignUser(List<CustomerDetailDTO> customerDetailDTOS, Campaign campaign) {
        customerDetailDTOS.stream()
                .map(customerDetailDTO -> {
                    RegistrationCampaignUser registrationCampaignUser = EligibleCustomerMapper.saveCampaignUser(customerDetailDTO, campaign);
                    registrationCampaignRepository.save(registrationCampaignUser);
                    return customerDetailDTO;
                }).collect(Collectors.toList());
    }
}
