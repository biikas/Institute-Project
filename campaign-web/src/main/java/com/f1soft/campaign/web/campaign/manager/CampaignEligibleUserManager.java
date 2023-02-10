package com.f1soft.campaign.web.campaign.manager;

import com.f1soft.campaign.entities.model.CampaignEligibleUser;
import com.f1soft.campaign.repository.CampaignEligibleUserRepository;
import com.f1soft.campaign.web.campaign.mapper.CampaignEligibleUserMapper;
import com.f1soft.campaign.web.dto.CampaignEligibleUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class CampaignEligibleUserManager {

    @Autowired
    private CampaignEligibleUserRepository campaignEligibleUserRepository;

    public void insertData(List<CampaignEligibleUserDTO> campaignEligibleUserDTOS) {
        for(CampaignEligibleUserDTO campaignEligibleUserDTO : campaignEligibleUserDTOS) {
            CampaignEligibleUser campaignEligibleUser = CampaignEligibleUserMapper.mapToCampaignEligibleUser(campaignEligibleUserDTO);
            try {
                campaignEligibleUserRepository.save(campaignEligibleUser);
            }
            catch(Exception e) {
                log.error("Resource already exist");
            }
        }
    }
}
