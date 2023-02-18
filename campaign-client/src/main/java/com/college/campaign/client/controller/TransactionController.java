package com.college.campaign.client.controller;

import com.college.campaign.client.dto.request.TransactionBookingRequest;
import com.college.campaign.client.dto.request.TransactionRequest;
import com.college.campaign.client.service.CampaignTransactionService;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.util.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Prajwol hada
 */
@Slf4j
@RestController
@RequestMapping("offer")
public class TransactionController {

    @Autowired
    private CampaignTransactionService campaignTransactionService;

    @PostMapping(value = "book/code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bookOffer(@NotNull @Valid @RequestBody TransactionBookingRequest transactionBookingRequest) {
        ServerResponse serverResponse = campaignTransactionService.bookOffer(transactionBookingRequest);

        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> payOffer(@NotNull @Valid @RequestBody TransactionRequest transactionRequest) {
        ServerResponse serverResponse = campaignTransactionService.offerPayment(transactionRequest);
        return ResponseBuilder.response(serverResponse);
    }
}
