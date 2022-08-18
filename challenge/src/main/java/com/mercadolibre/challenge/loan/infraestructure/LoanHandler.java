package com.mercadolibre.challenge.loan.infraestructure;

import com.mercadolibre.challenge.category.application.response.CategoryResponse;
import com.mercadolibre.challenge.loan.application.request.LoanBetweenDatesRequest;
import com.mercadolibre.challenge.loan.application.request.LoanRequest;
import com.mercadolibre.challenge.loan.application.response.LoanBetweenDatesResponse;
import com.mercadolibre.challenge.loan.application.response.LoanDebtResponse;
import com.mercadolibre.challenge.loan.application.response.LoanListByUserResponse;
import com.mercadolibre.challenge.loan.application.response.LoanResponse;
import com.mercadolibre.challenge.loan.domain.models.Loan;
import com.mercadolibre.challenge.loan.domain.services.LoanService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/loan")
public class LoanHandler {

    private LoanService loanService;

    public LoanHandler(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Creacion de un nuevo prestamo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LoanResponse.class)})
    @PostMapping("/new")
    public ResponseEntity<LoanResponse> createRequestLoan(@RequestBody LoanRequest request){

        LoanResponse newloan = loanService.createLoan(request);

        try {
            return ResponseEntity.created(new URI("loan/new/"+newloan.getLoan_id())).body(newloan);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @Operation(summary = "Obtener deuda por id de prestamo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LoanDebtResponse.class)})
    @PostMapping("/debt/{loan_id}")
    public ResponseEntity<LoanDebtResponse> getDebt(@PathVariable long loan_id){
        LoanDebtResponse response = loanService.getDebtByLoan(loan_id);

        try {
            return ResponseEntity.created(new URI("loan/debt/"+loan_id)).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Operation(summary = "Obtener lista de prestamos por id de usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LoanListByUserResponse.class)})
    @PostMapping("/user/{user_id}")
    public ResponseEntity<List<LoanListByUserResponse>> getLoansByUser(@PathVariable long user_id){

        List<LoanListByUserResponse> response = loanService.getListLoansByUser(user_id);

        try {
            return ResponseEntity.created(new URI("loan/user/"+user_id)).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Operation(summary = "Obtener lista de prestamos entre rango de fechas y por id de usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LoanBetweenDatesResponse.class)})
    @PostMapping("/user/between/{userId}")
    public ResponseEntity<List<LoanBetweenDatesResponse>> getLoansByDate(@PathVariable Long userId, @RequestBody LoanBetweenDatesRequest request){

        List<LoanBetweenDatesResponse> response = loanService.getListLoanBetweenDates(userId, request);

        try {
            return ResponseEntity.created(new URI("loan/user/"+userId)).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Obtener lista de prestamos por id de usuario y por target")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = LoanDebtResponse.class)})
    @PostMapping("/user/{user_id}/{target}")
    public ResponseEntity<LoanDebtResponse> getTotalDebtByTarget(@PathVariable long user_id,@PathVariable String target){

        LoanDebtResponse response = loanService.getTotalDebtByTarget(user_id,target);

        try {
            return ResponseEntity.created(new URI("loan/user/"+user_id)).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
