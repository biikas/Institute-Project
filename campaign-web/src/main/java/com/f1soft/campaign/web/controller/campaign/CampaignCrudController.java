package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.campaign.dto.request.campaign.CreateCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.ModifyCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.UpdateCampaignStatusRequest;
import com.f1soft.campaign.web.campaign.service.CampaignCrudService;
import com.f1soft.campaign.web.service.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Prajwol Hada
 */
@Slf4j
@RestController
@RequestMapping("campaign")
public class CampaignCrudController {

    @Autowired
    private CampaignCrudService campaignCrudService;
    @Autowired
    private CampaignService campaignService;

    @SkipAPILogging
    @GetMapping(value = "{campaignId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignDetail(@PathVariable("campaignId") Long campaignId) {
        ServerResponse serverResponse = campaignService.campaignDetail(campaignId);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCampaignList(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = campaignService.searchCampaign(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCampaign(@NotNull @Valid @RequestBody CreateCampaignRequest createCampaignRequest) {
        ServerResponse serverResponse = campaignCrudService.createCampaign(createCampaignRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyCampaign(@NotNull @Valid @RequestBody ModifyCampaignRequest modifyCampaignRequest) {
        ServerResponse serverResponse = campaignCrudService.modifyCampaign(modifyCampaignRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyCampaignStatus(@NotNull @Valid @RequestBody UpdateCampaignStatusRequest updateCampaignStatusRequest) {
        ServerResponse serverResponse = campaignCrudService.updateCampaignStatus(updateCampaignStatusRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "{id}/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long campaignId) {
        ServerResponse serverResponse = campaignCrudService.delete(campaignId);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        ServerResponse serverResponse = campaignService.getAllCampaign();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignRedeemDetail() {
        ServerResponse serverResponse = campaignService.campaignRedeemDetail();
        return ResponseBuilder.response(serverResponse);
    }
}
