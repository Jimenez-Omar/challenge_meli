package com.mercadolibre.challenge.payment.domain.services;

import com.mercadolibre.challenge.loan.domain.models.Loan;
import com.mercadolibre.challenge.loan.domain.services.ILoanRepository;
import com.mercadolibre.challenge.payment.application.request.PaymentRequest;
import com.mercadolibre.challenge.payment.application.response.PaymentResponse;
import com.mercadolibre.challenge.payment.domain.models.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService implements IPaymentService{

    private final IPaymentRepository paymentRepository;
    private final ILoanRepository loanRepository;

    public PaymentService(IPaymentRepository paymentRepository, ILoanRepository loanRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public PaymentResponse createPayment(Long loanId, PaymentRequest request){

        Optional<Loan> loan = loanRepository. findById(loanId);
        Loan loanToPay = loan.get();

        if(request.getAmount() > loanToPay.getBalance()){

        }

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setLoan(loanToPay);

        paymentRepository.saveAndFlush(payment);

        loanToPay.setBalance(loanToPay.getBalance() - request.getAmount());
        loanRepository.saveAndFlush(loanToPay);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setLoadId(loanId);
        response.setDebt(loanToPay.getBalance());

        return response;

    }
}
