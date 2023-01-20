package com.nikosera.notification.dto;

import com.nikosera.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage extends ModelBase {

    private String template;
    private String subject;
    private Map<String, String> paramMap;
    private String address;

}
