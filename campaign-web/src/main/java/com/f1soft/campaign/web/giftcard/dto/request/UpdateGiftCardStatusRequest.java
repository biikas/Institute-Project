package com.f1soft.campaign.web.giftcard.dto.request;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Getter
@Setter
public class UpdateGiftCardStatusRequest extends ModelBase {

    @NotNull
    private Long id;
    @NotNull
    private Character active;
    private String remarks;
}
