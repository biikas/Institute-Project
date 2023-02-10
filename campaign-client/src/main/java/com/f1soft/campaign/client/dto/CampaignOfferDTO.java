package com.f1soft.campaign.client.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.OfferMode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
public class CampaignOfferDTO extends ModelBase {

    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String promoCode;
    private String startDate;
    private String endDate;
    private String imagePath;
    private String policy;
    private String offerLink;
}
