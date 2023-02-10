package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardModifyRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardProviderCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.UpdateGiftCardStatusRequest;
import com.f1soft.campaign.web.service.GiftCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Slf4j
@RestController
@RequestMapping("gift-card")
public class GiftCardCrudController {


    @Autowired
    private GiftCardService giftCardService;

    @SkipAPILogging
    @GetMapping(value = "{giftCardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giftCardDetail(@PathVariable("giftCardId") Long giftCardId) {
        ServerResponse serverResponse = giftCardService.giftCardDetail(giftCardId);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchGiftCard(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = giftCardService.searchGiftCard(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGiftCard(@NotNull @Valid @RequestBody GiftCardCreateRequest giftCardCreateRequest) {
        ServerResponse serverResponse = giftCardService.createGiftCard(giftCardCreateRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyGiftCard(@NotNull @Valid @RequestBody GiftCardModifyRequest giftCardModifyRequest) {
        ServerResponse serverResponse = giftCardService.modifyGiftCard(giftCardModifyRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGiftCardStatus(@NotNull @Valid @RequestBody UpdateGiftCardStatusRequest updateGiftCardStatusRequest) {
        ServerResponse serverResponse = giftCardService.updateGiftCardStatus(updateGiftCardStatusRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @PostMapping(value = "{id}/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long campaignId) {
        ServerResponse serverResponse = giftCardService.delete(campaignId);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "provider/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giftCardProviders() {
        ServerResponse serverResponse = giftCardService.getAllProviders();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giftCards() {
        ServerResponse serverResponse = giftCardService.getAllGiftCard();
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "provider/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGiftCardProvider(@NotNull @Valid @RequestBody GiftCardProviderCreateRequest giftCardCreateRequest) {
        ServerResponse serverResponse = giftCardService.createGiftCardProvider(giftCardCreateRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
