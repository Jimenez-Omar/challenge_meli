package com.mercadolibre.challenge.payment.infraestructure;

import com.mercadolibre.challenge.loan.application.response.LoanDebtResponse;
import com.mercadolibre.challenge.payment.application.request.PaymentRequest;
import com.mercadolibre.challenge.payment.application.response.PaymentResponse;
import com.mercadolibre.challenge.payment.domain.services.PaymentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/payment")
public class PaymentHandler {

    private PaymentService paymentService;

    public PaymentHandler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Creacion de pago asociado a un id de prestamo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = PaymentResponse.class)})
    @PostMapping("/new/{loan_id}")
    private ResponseEntity<PaymentResponse> newPayment(@PathVariable long loan_id, @RequestBody PaymentRequest request){


        PaymentResponse newPayment = paymentService.createPayment(loan_id,request);

        try {
            return ResponseEntity.created(new URI("category/new/"+newPayment.getPaymentId())).body(newPayment);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
