package com.f1soft.campaign.common.dto;


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

    public JobResponse() {
    }

    public JobResponse(boolean success) {
        this.success = success;
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
