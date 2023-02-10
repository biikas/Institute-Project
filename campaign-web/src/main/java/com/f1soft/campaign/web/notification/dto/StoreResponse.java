package com.f1soft.campaign.web.notification.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreResponse extends ModelBase {

    private String name;
    private String url;
    private String src;


    public StoreResponse(String name, String url, String src) {
        this.name = name;
        this.url = url;
        this.src = src;
    }
}
