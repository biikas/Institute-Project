package com.f1soft.campaign.web.dto.response;


import com.f1soft.campaign.web.dto.CustomCBSConnectionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomCBSConnectionResponse {

    private List<CustomCBSConnectionDTO> cbsConnections;
}
