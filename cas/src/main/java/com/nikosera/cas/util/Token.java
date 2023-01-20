package com.nikosera.cas.util;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Builder
@Getter
public class Token {
    private String token;
    private Date createdDate;
    private Date expiryDate;
}
