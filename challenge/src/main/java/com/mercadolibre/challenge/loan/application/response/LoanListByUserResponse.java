package com.mercadolibre.challenge.loan.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoanListByUserResponse {

    private Long loan_id;
    private Integer amount;
    private Integer term;
    private double balance;
    private String target;
    private String date;

}
