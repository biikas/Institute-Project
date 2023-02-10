package com.f1soft.campaign.web.campaign.dto.request.campaign;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
public class UpdateCampaignStatusRequest extends ModelBase {

    @NotNull
    private Long id;
    @NotNull
    private String status;
    private String remarks;
}
