package com.college.campaign.web.giftcard.dto.request;

import com.college.campaign.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author <krishna.pandey@college.com>
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
