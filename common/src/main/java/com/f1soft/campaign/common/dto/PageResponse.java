package com.f1soft.campaign.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> extends ModelBase {
    private List<T> object;
    private Long totalCount;

    public <U> PageResponse(List<U> tList, Long totalCount) {
        this.object = (List<T>) tList;
        this.totalCount = totalCount;
    }
}
