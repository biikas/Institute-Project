package com.nikosera.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class ImageConfig extends ModelBase {
    private String path;
    private Integer expiresIn;
}
