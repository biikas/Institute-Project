package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.service.BookingDataService;
import com.f1soft.campaign.web.service.TransactionDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("campaign")
public class CampaignTransactionController {

    @Autowired
    private TransactionDataService transactionDataService;

    @Autowired
    private BookingDataService bookingDataService;

    @SkipAPILogging
    @GetMapping(value = "transaction/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionList() {
        ServerResponse serverResponse = transactionDataService.getAllTransactionList();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transactionDetail(@PathVariable("transactionId") Long transactionId) {
        ServerResponse serverResponse = transactionDataService.transactionDetail(transactionId);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "booking/log", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bookingLog() {
        ServerResponse serverResponse = bookingDataService.getAllBookingLog();
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "transaction/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionList(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = transactionDataService.searchTransaction(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "transaction/manual/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getManualTransactions(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = transactionDataService.searchManualTransaction(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "transaction/recent/{campaignId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transactionDetailByCampaignId(@PathVariable("campaignId") Long campaignId) {
        ServerResponse serverResponse = transactionDataService.transactionDetailByCampaignId(campaignId);
        return ResponseBuilder.response(serverResponse);
    }
}
