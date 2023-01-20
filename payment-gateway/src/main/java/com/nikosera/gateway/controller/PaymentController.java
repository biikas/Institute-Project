package com.nikosera.gateway.controller;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.gateway.dto.PaymentRequestDto;
import com.nikosera.gateway.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/24/22
 */

@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "initiate")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> initiatePayment(PaymentRequestDto requestDto) {
        GenericResponse response = transactionService.initiatePayment(requestDto);
        return ResponseEntity.ok(response);
    }
}
