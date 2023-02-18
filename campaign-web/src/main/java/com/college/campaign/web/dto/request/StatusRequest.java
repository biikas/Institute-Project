package com.college.campaign.web.dto.request;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest extends ModelBase {

    private char active;

}
