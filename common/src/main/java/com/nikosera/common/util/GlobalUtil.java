package com.nikosera.common.util;

import com.nikosera.common.service.IdHasherService;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class GlobalUtil {

    public static IdHasherService hasher(){
        return StaticContextAccessor.getBean(IdHasherService.class);
    }

}
