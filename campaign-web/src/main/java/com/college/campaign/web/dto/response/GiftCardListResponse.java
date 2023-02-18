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
@NoArgsConstructor
@AllArgsConstructor
public class GiftCardListResponse extends ModelBase {

    private List<GiftCardResponse> giftCardResponseList;
}
