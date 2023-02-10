package com.f1soft.campaign.transaction.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsoField extends ModelBase {

    private String key;
    private String value;
}
