package com.mercadolibre.challenge.loan.domain.services;

import com.mercadolibre.challenge.loan.application.request.LoanBetweenDatesRequest;
import com.mercadolibre.challenge.loan.application.request.LoanRequest;
import com.mercadolibre.challenge.loan.application.response.LoanBetweenDatesResponse;
import com.mercadolibre.challenge.loan.application.response.LoanDebtResponse;
import com.mercadolibre.challenge.loan.application.response.LoanListByUserResponse;
import com.mercadolibre.challenge.loan.application.response.LoanResponse;
import com.mercadolibre.challenge.loan.domain.models.Loan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ILoanService {
    LoanResponse createLoan(LoanRequest request);

    LoanDebtResponse getDebtByLoan(Long loanId);

    List<LoanListByUserResponse> getListLoansByUser(Long userId);

    LoanDebtResponse getTotalDebtByTarget(Long userId,String target);

    List<LoanBetweenDatesResponse> getListLoanBetweenDates(Long userId,LoanBetweenDatesRequest request);


}
