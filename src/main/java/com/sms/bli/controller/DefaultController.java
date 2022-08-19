package com.sms.bli.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bikash Shah
 */

@Slf4j
@RestController
@RequestMapping("/")
public class DefaultController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String versions() {
        return "Remit Server is Running";
    }
}