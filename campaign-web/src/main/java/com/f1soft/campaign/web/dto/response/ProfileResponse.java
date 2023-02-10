package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;
import com.f1soft.campaign.common.dto.ModelBase;
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
