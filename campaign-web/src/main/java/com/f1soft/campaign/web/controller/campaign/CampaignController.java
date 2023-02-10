package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.service.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @SkipAPILogging
    @GetMapping(value = "offer/mode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignOffers() {
        ServerResponse serverResponse = campaignService.getAllOfferMode();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "service/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignServices() {
        ServerResponse serverResponse = campaignService.getAllServices();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "profile/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> profiles() {
        ServerResponse serverResponse = campaignService.getAllCustomerProfiles();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "mode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignMode() {
        ServerResponse serverResponse = campaignService.getCampaignModes();
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "profile/{campaignId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignCreatorProfile(@PathVariable("campaignId") Long campaignId) {
        ServerResponse serverResponse = campaignService.campaignCreatorProfile(campaignId);
        return ResponseBuilder.response(serverResponse);
    }
}
