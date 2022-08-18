package com.mercadolibre.challenge.payment.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private Long loadId;
    private double debt;
}
