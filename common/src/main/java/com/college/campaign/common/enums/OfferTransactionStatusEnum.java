package com.college.campaign.common.enums;

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
