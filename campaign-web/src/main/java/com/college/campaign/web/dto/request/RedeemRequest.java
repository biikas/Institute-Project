package com.college.campaign.web.dto.request;

import com.college.campaign.common.dto.ModelBase;
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
public class RedeemRequest extends ModelBase {

    private List<Long> offerTransactions;
    private String remarks;

}
