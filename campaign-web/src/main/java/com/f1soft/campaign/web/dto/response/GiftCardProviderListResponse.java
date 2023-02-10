package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardProviderListResponse extends ModelBase {

    private List<GiftCardProviderDetail> giftCardProviderDetails;

}
