package com.f1soft.campaign.client.dto.response;

import com.f1soft.campaign.client.dto.GiftCardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardResponse {

    private List<GiftCardDTO> giftCards;
}
