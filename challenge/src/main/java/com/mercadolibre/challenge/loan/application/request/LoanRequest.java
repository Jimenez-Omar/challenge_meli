package com.mercadolibre.challenge.loan.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoanRequest {
    private int amount;
    private int term;
    private Long user_id;
}
