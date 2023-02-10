package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.crud.service.ServiceCrudService;
import com.f1soft.campaign.web.crud.service.ServiceRequest;
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
@RequestMapping("service")
public class ServiceCrudController {

    @Autowired
    private ServiceCrudService serviceCrudService;

    @SkipAPILogging
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        ServerResponse serverResponse = serviceCrudService.getService();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ServerResponse serverResponse = serviceCrudService.getServiceById(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modify(@PathVariable("id") Long id, @NotNull @Valid @RequestBody ServiceRequest serviceRequest) {
        ServerResponse serverResponse = serviceCrudService.modifyService(id, serviceRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@NotNull @Valid @RequestBody ServiceRequest serviceRequest) {
        ServerResponse serverResponse = serviceCrudService.createService(serviceRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> status(@PathVariable("id") Long id, @NotNull @Valid @RequestBody StatusRequest statusRequest) {
        ServerResponse serverResponse = serviceCrudService.modifyStatus(id, statusRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
