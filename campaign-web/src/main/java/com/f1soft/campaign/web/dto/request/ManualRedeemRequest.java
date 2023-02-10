package com.f1soft.campaign.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class ManualRedeemRequest {

    private List<String> offerTransactions;
    private String remarks;

}
