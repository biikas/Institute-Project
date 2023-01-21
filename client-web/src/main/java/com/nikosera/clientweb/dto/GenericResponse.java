package com.nikosera.clientweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private boolean success;
    private String message;
    @JsonIgnore
    private String unicodeMessage;
    private String code;
    private String processingCode;

    private Object data;
}
