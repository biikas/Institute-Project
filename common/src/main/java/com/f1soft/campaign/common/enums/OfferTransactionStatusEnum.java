package com.f1soft.campaign.common.enums;

import lombok.Getter;

/**
 * @author Prajwol Hada
 */
@Getter
public enum OfferTransactionStatusEnum {
    PENDING,
    SUCCESS,
    FAILED,
    TIMEOUT,
    AMBIGUOUS,
    REJECTED
}
