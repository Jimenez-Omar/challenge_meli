package com.mercadolibre.challenge.loan.application.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoanBetweenDatesResponse {
    private Long loan_id;
    private int amount;
    private int term;
    private double rate;
    private Long user_id;
    private String target;

    private String date;
}
