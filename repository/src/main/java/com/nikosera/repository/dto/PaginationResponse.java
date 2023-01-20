package com.nikosera.repository.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> implements Serializable {

    private T response;
    private long totalCount;
}
