package com.mercadolibre.challenge.loan.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoanResponse {
    private Long loan_id;
    private double installment;
}
