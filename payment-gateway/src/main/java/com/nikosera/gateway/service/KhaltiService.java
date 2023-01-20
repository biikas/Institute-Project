package com.nikosera.gateway.service;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.gateway.dto.PaymentRequestDto;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/6/22
 */
public interface KhaltiService {

    public GenericResponse initiatePayment(PaymentRequestDto requestDto);
}
