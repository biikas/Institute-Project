package com.nikosera.user.request;

import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class ChangeImageRequest extends ModelBase {

    @NotBlank
    private String imagePath;
}
