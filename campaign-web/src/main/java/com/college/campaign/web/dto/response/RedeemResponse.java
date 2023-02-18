package com.college.campaign.web.dto.response;

import com.college.campaign.web.dto.RefundDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedeemResponse {

    private List<RefundDTO> response;
    private int successCount;
    private int failureCount;
}
