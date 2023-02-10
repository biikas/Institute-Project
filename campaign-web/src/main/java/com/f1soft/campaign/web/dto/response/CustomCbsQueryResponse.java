package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.web.dto.CustomCbsQueryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CustomCbsQueryResponse extends ModelBase {

    private List<CustomCbsQueryDTO> customCbsQueries;
}
