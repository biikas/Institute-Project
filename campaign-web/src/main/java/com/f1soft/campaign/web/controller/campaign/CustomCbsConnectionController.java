package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.request.CustomCBSConnectionRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import com.f1soft.campaign.web.service.CustomCbsConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Shreetika Panta
 */

@Slf4j
@RestController
@RequestMapping(value = "custom-cbs-connection")
public class CustomCbsConnectionController {

    @Autowired
    private CustomCbsConnectionService customCbsConnectionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getActive() {
        ServerResponse serverResponse = customCbsConnectionService.getAllCustomCBSQueryConnection();
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ServerResponse serverResponse = customCbsConnectionService.getById(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@NotNull @Valid @RequestBody CustomCBSConnectionRequest customCBSConnectionRequest) {
        ServerResponse serverResponse = customCbsConnectionService.create(customCBSConnectionRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modify(@PathVariable("id") Long id, @NotNull @Valid @RequestBody CustomCBSConnectionRequest customCBSConnectionRequest) {
        ServerResponse serverResponse = customCbsConnectionService.modify(id, customCBSConnectionRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCBSConnectionList(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = customCbsConnectionService.getAllCustomCBSConnectionList(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "execute/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> execute(@PathVariable("id") Long id) {
        ServerResponse serverResponse = customCbsConnectionService.execute(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyStatus(@PathVariable("id") Long id, @NotNull @Valid @RequestBody StatusRequest statusRequest) {
        ServerResponse serverResponse = customCbsConnectionService.modifyStatus(id, statusRequest);
        return ResponseBuilder.response(serverResponse);
    }

}
