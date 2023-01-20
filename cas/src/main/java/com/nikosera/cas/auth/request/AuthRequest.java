package com.nikosera.cas.auth.request;

import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthRequest extends ModelBase {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
