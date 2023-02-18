package com.college.campaign.web.dto;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
public class ProfileDTO extends ModelBase {
    private Long id;
    private String name;
}
