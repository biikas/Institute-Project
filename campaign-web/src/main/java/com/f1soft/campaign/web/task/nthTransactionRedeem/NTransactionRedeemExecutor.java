package com.f1soft.campaign.web.task.nthTransactionRedeem;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.cbs.dto.TransactionDTO;
import com.f1soft.campaign.common.manager.QueryManager;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.ChannelRepository;
import com.f1soft.campaign.repository.DataPackageCampaignUserRepository;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.repository.TransactionCampaignUserRepository;
import com.f1soft.campaign.web.mapper.NTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Shreetika Panta
 */

@Component
public class NTransactionRedeemExecutor {

    @Autowired
    private QueryManager queryManager;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private TransactionCampaignUserRepository transactionCampaignUserRepository;
    @Autowired
    private DataPackageCampaignUserRepository dataPackageCampaignUserRepository;
    @Autowired
    private ChannelRepository channelRepository;

    public CampaignJob processCampaignWithProduct(Campaign campaign, CampaignEligibleService campaignEligibleService) {
        List<TransactionDTO> transactionDTOList = queryManager.getProcessCampaignWithProduct(campaign, campaignEligibleService);
        if (transactionDTOList.size() > 0) {
            for (TransactionDTO transactionDTO : transactionDTOList) {
                Optional<TransactionCampaignUser> optionalTransactionCampaignUser = transactionCampaignUserRepository.findByUsernameAndCampaignId(transactionDTO.getUsername(), campaign.getId());
                if (!optionalTransactionCampaignUser.isPresent()) {
                    Channel channel = channelRepository.getChannelByCode(transactionDTO.getChannel()).get();

                    OfferTransaction offerTransaction = NTransactionMapper.convertToSaveOfferTransaction(campaign, channel, transactionDTO);
                    offerTransactionRepository.save(offerTransaction);

                    saveDataPackageCampaignUser(offerTransaction, campaign, transactionDTO);

                    TransactionCampaignUser transactionCampaignUser = NTransactionMapper.convertToSaveTransactionCampaignUser(campaign, transactionDTO);
                    transactionCampaignUserRepository.save(transactionCampaignUser);

                }
            }
        }
        return new CampaignJob(true, "Data fetched successfully.");
    }

    public CampaignJob processCampaignWithoutProduct(Campaign campaign) {
        List<TransactionDTO> transactionDTOList = queryManager.getProcessCampaignWithoutProduct(campaign);
        if (transactionDTOList.size() > 0) {
            for (TransactionDTO transactionDTO : transactionDTOList) {
                Optional<TransactionCampaignUser> optionalTransactionCampaignUser = transactionCampaignUserRepository.findByUsernameAndCampaignId(transactionDTO.getUsername(), campaign.getId());
                if (!optionalTransactionCampaignUser.isPresent()) {
                    Channel channel = channelRepository.getChannelByCode(transactionDTO.getChannel()).get();

                    OfferTransaction offerTransaction = NTransactionMapper.convertToSaveOfferTransaction(campaign, channel, transactionDTO);
                    offerTransactionRepository.save(offerTransaction);

                    saveDataPackageCampaignUser(offerTransaction, campaign, transactionDTO);

                    TransactionCampaignUser transactionCampaignUser = NTransactionMapper.convertToSaveTransactionCampaignUser(campaign, transactionDTO);
                    transactionCampaignUserRepository.save(transactionCampaignUser);

                }
            }
        }
        return new CampaignJob(true, "Data fetched successfully.");
    }

    private void saveDataPackageCampaignUser(OfferTransaction offerTransaction, Campaign campaign, TransactionDTO transactionDTO) {
        DataPackageCampaignUser dataPackageCampaignUser = NTransactionMapper.convertToDataPackageCampaignUser(offerTransaction, campaign, transactionDTO);
        if (dataPackageCampaignUser != null) {
            dataPackageCampaignUserRepository.save(dataPackageCampaignUser);
        }
    }
}
