package com.f1soft.campaign.web.refund.dto;


import com.f1soft.campaign.web.dto.response.RedeemResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobResponse {

    private boolean success;
    private long id;
    private String message;
    private RedeemResponse cashBackResponse;

    public JobResponse() {
    }

    public JobResponse(boolean success) {
        this.success = success;
    }

    public JobResponse(boolean success, RedeemResponse cashBackResponse) {
        this.success = success;
        this.cashBackResponse = cashBackResponse;
    }

    public JobResponse(boolean success, RedeemResponse cashBackResponse, String message) {
        this.success = success;
        this.cashBackResponse = cashBackResponse;
        this.message = message;
    }

    public JobResponse(boolean success, long id, String message) {
        this.success = success;
        this.id = id;
        this.message = message;
    }

    public JobResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
