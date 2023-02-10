package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.crud.systemconfigmaster.SystemConfigMasterCrudService;
import com.f1soft.campaign.web.crud.systemconfigmaster.SystemConfigMasterRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Slf4j
@RestController
@RequestMapping("system-config")
public class SystemConfigMasterCrudController {

    @Autowired
    private SystemConfigMasterCrudService systemConfigMasterCrudService;

    @PostMapping(value = "master/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSystemConfigMaster(@NotNull @Valid @RequestBody SystemConfigMasterRequest systemConfigRequest) {
        ServerResponse serverResponse = systemConfigMasterCrudService.createSystemConfigMaster(systemConfigRequest);
        return ResponseBuilder.response(serverResponse);
    }


    @GetMapping(value = "master", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSystemConfigMaster() {
        ServerResponse serverResponse = systemConfigMasterCrudService.getSystemConfigMaster();
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "master/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSystemConfigMasterById(@PathVariable("id") Integer id) {
        ServerResponse serverResponse = systemConfigMasterCrudService.getSystemConfigMasterById(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "master/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifySystemConfigMaster(@PathVariable("id") Integer id, @NotNull @Valid @RequestBody SystemConfigMasterRequest systemConfigMasterRequest) {
        ServerResponse serverResponse = systemConfigMasterCrudService.modifySystemConfigMaster(id, systemConfigMasterRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "master/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> status(@PathVariable("id") Integer id, @NotNull @Valid @RequestBody StatusRequest statusRequest) {
        ServerResponse serverResponse = systemConfigMasterCrudService.modifyStatus(id, statusRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
