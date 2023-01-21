package com.nikosera.clientweb.remoablecontroller;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> companyList() {
        GenericResponse response = companyService.companyList();
        return ResponseEntity.ok(response);
    }
}
