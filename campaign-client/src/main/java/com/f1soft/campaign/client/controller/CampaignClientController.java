package com.f1soft.campaign.client.controller;

import com.f1soft.campaign.client.dto.request.GiftCardFetchRequest;
import com.f1soft.campaign.client.dto.request.OfferFetchRequest;
import com.f1soft.campaign.client.service.CampaignClientService;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Prajwol hada
 */
@Slf4j
@RestController
@RequestMapping("offer")
public class CampaignClientController {

    @Autowired
    private CampaignClientService campaignService;

    @SkipAPILogging
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> campaignList() {

        ServerResponse serverResponse = campaignService.campaignList();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "user/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bookOffer(@NotNull @Valid @RequestBody OfferFetchRequest offerFetchRequest) {

        ServerResponse serverResponse = campaignService.campaignListByUser(offerFetchRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "user/giftcard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userGiftCard(@NotNull @Valid @RequestBody GiftCardFetchRequest giftCardFetchRequest) {
        ServerResponse serverResponse = campaignService.userGiftCards(giftCardFetchRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
