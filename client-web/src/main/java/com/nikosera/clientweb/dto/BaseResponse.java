package com.nikosera.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */

@Getter
@Setter
public class BaseResponse  {

    protected boolean success;
    protected String resultCode;
    protected String resultDescription;
    protected String unicodeMessage;
}
