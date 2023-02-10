package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferTransactionListResponse extends ModelBase {
    
    private List<OfferTransactionResponse> transactionList;
}
