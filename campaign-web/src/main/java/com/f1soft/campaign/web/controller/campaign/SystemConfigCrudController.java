package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.crud.systemconfig.SystemConfigCrudService;
import com.f1soft.campaign.web.crud.systemconfig.SystemConfigRequest;
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
public class SystemConfigCrudController {

    @Autowired
    private SystemConfigCrudService systemConfigCrudService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        ServerResponse serverResponse = systemConfigCrudService.getSystemConfig();
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        ServerResponse serverResponse = systemConfigCrudService.getSystemConfigId(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modify(@PathVariable("id") Integer id, @NotNull @Valid @RequestBody SystemConfigRequest systemConfigRequest) {
        ServerResponse serverResponse = systemConfigCrudService.modifySystemConfig(id, systemConfigRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@NotNull @Valid @RequestBody SystemConfigRequest systemConfigRequest) {
        ServerResponse serverResponse = systemConfigCrudService.createSystemConfig(systemConfigRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> status(@PathVariable("id") Integer id, @NotNull @Valid @RequestBody StatusRequest statusRequest) {
        ServerResponse serverResponse = systemConfigCrudService.modifyStatus(id, statusRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
