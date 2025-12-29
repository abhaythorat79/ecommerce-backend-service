package com.ecommerce.service;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest request);

}
