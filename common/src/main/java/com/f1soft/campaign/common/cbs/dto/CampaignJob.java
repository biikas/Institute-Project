package com.f1soft.campaign.common.cbs.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rashim Dhaubanjar
 */

@Getter
@Setter
@ToString
public class CampaignJob {

    private boolean success;
    private String message;

    public CampaignJob() {
    }

    public CampaignJob(boolean success) {
        this.success = success;
    }

    public CampaignJob(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
