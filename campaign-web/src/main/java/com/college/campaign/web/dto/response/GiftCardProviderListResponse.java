package com.college.campaign.web.dto.response;

import com.college.campaign.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <krishna.pandey@college.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardProviderListResponse extends ModelBase {

    private List<GiftCardProviderDetail> giftCardProviderDetails;

}
