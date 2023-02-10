package com.f1soft.campaign.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bikash Shah
 */
@Getter
@Setter
public class EventTypeResponse {
    private Long id;
    private String code;
    private String name;
    private String type;
}
