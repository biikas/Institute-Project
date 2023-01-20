package com.nikosera.gateway.service;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.gateway.dto.PaymentRequestDto;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/12/22
 */
public interface TransactionService {

    public GenericResponse initiatePayment(PaymentRequestDto requestDto);
}
