package com.college.campaign.web.dto.response;

import com.college.campaign.common.cbs.dto.CustomerProfileDTO;
import com.college.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Prajwol Hada
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileResponse extends ModelBase {

    private List<CustomerProfileDTO> profiles;
}
