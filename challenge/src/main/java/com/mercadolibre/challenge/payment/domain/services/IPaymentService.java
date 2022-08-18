package com.mercadolibre.challenge.payment.domain.services;

import com.mercadolibre.challenge.payment.application.request.PaymentRequest;
import com.mercadolibre.challenge.payment.application.response.PaymentResponse;
import com.mercadolibre.challenge.payment.domain.models.Payment;
import org.springframework.data.repository.Repository;

public interface IPaymentService{

    PaymentResponse createPayment(Long loanId,PaymentRequest request);
}
