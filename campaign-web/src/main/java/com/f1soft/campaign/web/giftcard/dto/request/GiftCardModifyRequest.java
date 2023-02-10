package com.f1soft.campaign.web.giftcard.dto.request;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Getter
@Setter
public class GiftCardModifyRequest extends ModelBase {

    @NotNull
    private Long id;
    @NotNull
    private Long giftCardProviderId;
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    @NotNull
    private Character active;
    @NotEmpty
    private String expiryDate;
    @NotNull
    private String amount;
}
