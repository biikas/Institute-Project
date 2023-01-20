package com.nikosera.cas.auth.request;

import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
public class TOTPAuthRequest extends ModelBase {

    @NotNull
    @Pattern(regexp = "^[0-9]{6}$")
    private String token;
}
