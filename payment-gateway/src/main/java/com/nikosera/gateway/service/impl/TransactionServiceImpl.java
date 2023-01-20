package com.nikosera.gateway.service.impl;

import com.nikosera.common.constant.StatusConstant;
import com.nikosera.common.constant.VendorConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.entity.TransactionRequest;
import com.nikosera.entity.TransactionResponse;
import com.nikosera.gateway.dto.KhaltiPaymentResponse;
import com.nikosera.gateway.dto.PaymentRequestDto;
import com.nikosera.gateway.service.KhaltiService;
import com.nikosera.gateway.service.TransactionService;
import com.nikosera.repository.repository.core.StatusRepository;
import com.nikosera.repository.repository.core.TransactionResponseRepository;
import com.nikosera.repository.repository.core.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.nikosera.repository.repository.core.TransactionRequestRepository;

import java.util.Date;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/12/22
 */

@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private KhaltiService khaltiService;

    @Autowired
    private TransactionRequestRepository requestRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TransactionResponseRepository responseRepository;

    @Override
    public GenericResponse initiatePayment(PaymentRequestDto requestDto) {
        log.info("Initiating Payment-----");
        GenericResponse genericResponse = new GenericResponse();
        TransactionRequest transactionRequest = saveLog(requestDto);
        try {
            genericResponse = khaltiService.initiatePayment(requestDto);
            updateRequestLog(genericResponse, transactionRequest);
            saveResponseLog(genericResponse, transactionRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.EXCEPTION));
            requestRepository.save(transactionRequest);
        }
        return genericResponse;
    }

    public TransactionRequest saveLog(PaymentRequestDto requestDto) {
        TransactionRequest transactionRequest = new TransactionRequest();

        transactionRequest.setRecordedDate(new Date());
        transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.INITIATED));

        transactionRequest.setVendor(vendorRepository.getActiveByCode(VendorConstant.KHALTI));

        transactionRequest.setAmount(requestDto.getAmount());
        transactionRequest.setOrderId(requestDto.getOrderId());
        transactionRequest.setOrderName(requestDto.getOrderName());
        transactionRequest.setInitiatorName(requestDto.getInitiatorName());
        transactionRequest.setInitiatorEmail(requestDto.getInitiatorEmail());
        transactionRequest.setInitiatorMobileNumber(requestDto.getInitiatorMobileNumber());
        return requestRepository.save(transactionRequest);
    }

    public TransactionResponse saveResponseLog(GenericResponse genericResponse, TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setRecordedDate(new Date());
        transactionResponse.setVendor(vendorRepository.getActiveByCode(VendorConstant.KHALTI));
        transactionResponse.setTransactionRequest(transactionRequest);
        transactionRequest.setStatus(transactionRequest.getStatus());
        if (genericResponse.getStatusCode().equalsIgnoreCase("0")) {
            transactionResponse.setStatus(statusRepository.getByCode(StatusConstant.SUCCESS));
        } else if (genericResponse.getStatusCode().equalsIgnoreCase("-1")) {
            transactionResponse.setStatus(statusRepository.getByCode(StatusConstant.TIMEOUT));
        } else if (genericResponse.getStatusCode().equalsIgnoreCase("2")) {
            transactionResponse.setStatus(statusRepository.getByCode(StatusConstant.EXCEPTION));
        } else {
            transactionResponse.setStatus(statusRepository.getByCode(StatusConstant.FAILURE));
        }
        return responseRepository.save(transactionResponse);
    }

    public void updateRequestLog(GenericResponse genericResponse, TransactionRequest transactionRequest) {
        if (genericResponse.getStatusCode().equalsIgnoreCase("0")) {
            transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.SUCCESS));
            requestRepository.save(transactionRequest);
        } else if (genericResponse.getStatusCode().equalsIgnoreCase("-1")) {
            transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.TIMEOUT));
            requestRepository.save(transactionRequest);
        } else if (genericResponse.getStatusCode().equalsIgnoreCase("2")) {
            transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.EXCEPTION));
            requestRepository.save(transactionRequest);
        } else {
            transactionRequest.setStatus(statusRepository.getByCode(StatusConstant.FAILURE));
            requestRepository.save(transactionRequest);
        }
    }
}
