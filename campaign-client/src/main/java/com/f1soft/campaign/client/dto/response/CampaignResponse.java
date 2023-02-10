package com.f1soft.campaign.client.dto.response;

import com.f1soft.campaign.client.dto.CampaignOfferDTO;
import com.f1soft.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse extends ModelBase {

    List<CampaignOfferDTO> offers;
}
