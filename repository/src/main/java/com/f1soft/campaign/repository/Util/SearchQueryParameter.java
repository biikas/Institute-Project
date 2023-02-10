package com.f1soft.campaign.repository.Util;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Santosh Pun
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SearchQueryParameter {

    private List<FieldQueryParameter> search;

    private String fromDate;
    private String toDate;

    @NotNull
    private Integer page;
    @NotNull
    private Integer size;


}
