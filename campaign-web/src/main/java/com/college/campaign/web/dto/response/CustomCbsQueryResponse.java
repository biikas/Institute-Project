package com.college.campaign.web.dto.response;

import com.college.campaign.common.dto.ModelBase;
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
