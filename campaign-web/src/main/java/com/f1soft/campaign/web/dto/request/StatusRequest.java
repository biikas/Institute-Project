package com.f1soft.campaign.web.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest extends ModelBase {

    private char active;

}
