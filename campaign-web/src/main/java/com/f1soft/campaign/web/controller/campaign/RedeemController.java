package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.dto.request.ManualRedeemRequest;
import com.f1soft.campaign.web.dto.request.RedeemRequest;
import com.f1soft.campaign.web.service.campaign.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("redeem")
public class RedeemController {

    @Autowired
    private RefundService refundService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refund(@NotNull @Valid @RequestBody RedeemRequest redeemRequest) {
        ServerResponse serverResponse = refundService.redeem(redeemRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "accept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> acceptManualRedeem(@NotNull @Valid @RequestBody ManualRedeemRequest redeemRequest) {
        ServerResponse serverResponse = refundService.acceptManualRedeem(redeemRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "reject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reject(@NotNull @Valid @RequestBody ManualRedeemRequest redeemRequest) {
        ServerResponse serverResponse = refundService.failRedeem(redeemRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> redeemAllManual(){
        ServerResponse serverResponse = refundService.redeemAll();
        return ResponseBuilder.response(serverResponse);
    }

}
