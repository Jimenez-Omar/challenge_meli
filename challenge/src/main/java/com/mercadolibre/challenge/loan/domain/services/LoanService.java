package com.mercadolibre.challenge.loan.domain.services;

import com.mercadolibre.challenge.category.domain.models.Category;
import com.mercadolibre.challenge.category.domain.services.CategoryService;
import com.mercadolibre.challenge.loan.application.request.LoanBetweenDatesRequest;
import com.mercadolibre.challenge.loan.application.request.LoanRequest;
import com.mercadolibre.challenge.loan.application.response.LoanBetweenDatesResponse;
import com.mercadolibre.challenge.loan.application.response.LoanDebtResponse;
import com.mercadolibre.challenge.loan.application.response.LoanListByUserResponse;
import com.mercadolibre.challenge.loan.application.response.LoanResponse;
import com.mercadolibre.challenge.loan.domain.models.Loan;
import com.mercadolibre.challenge.user.domain.models.User;
import com.mercadolibre.challenge.user.domain.services.UserService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class LoanService implements ILoanService {

    private final ILoanRepository loanRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    private Category response;


    public LoanService(ILoanRepository loanRepository,UserService userService, CategoryService categoryService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public LoanResponse createLoan(LoanRequest request) {

        Optional<User> user = userService.updateUser(request.getUser_id());
        LoanResponse response = new LoanResponse();

        if (user.isPresent()){
            user.flatMap(usr->{

                Loan newLoan = new Loan();
                newLoan.setAmount(request.getAmount());
                newLoan.setTerm(request.getTerm());
                newLoan.setBalance(request.getAmount());
                newLoan.setUser(usr);
                newLoan.setCategory(this.getCategoryByLoan(usr));

                loanRepository.saveAndFlush(newLoan);

                response.setLoan_id(newLoan.getId());
                response.setInstallment(this.calculateInstallment(newLoan));

                return  Optional.of(response);
            });
        }
        return response;
    }

    @Override
    public LoanDebtResponse getDebtByLoan(Long loanId) {

        Optional<Loan> loan =  loanRepository.findById(loanId);

        Loan loanDebt = loan.get();

        LoanDebtResponse response = new LoanDebtResponse();

        response.setBalance(loanDebt.getBalance());

        return response;
    }

    @Override
    public List<LoanListByUserResponse> getListLoansByUser(Long userId) {

        Optional<User> userFound = userService.getUser(userId);
        User user = userFound.get();

        List<Loan> loans = loanRepository.findAll();
        List<LoanListByUserResponse> result = new ArrayList<>();


        for (Loan loan:loans) {
            if(loan.getUser().equals(user)){
                LoanListByUserResponse list = new LoanListByUserResponse();
                list.setLoan_id(loan.getId());
                list.setAmount(loan.getAmount());
                list.setTerm(loan.getTerm());
                list.setBalance(loan.getBalance());
                list.setDate(loan.getDate());
                list.setTarget(loan.getCategory().getName());

                result.add(list);

            }
        }
        return result;
    }

    @Override
    public LoanDebtResponse getTotalDebtByTarget(Long userId, String target) {

        Optional<User> userLoan = userService.getUser(userId);
        User user = userLoan.get();

        double result = 0.0;

        List<Loan> loans = loanRepository.findAll();

        for (Loan loan:loans) {
            if(loan.getUser().equals(user)&&loan.getCategory().getName().equals(target)){
                result += loan.getBalance();
            }
        }

        LoanDebtResponse response = new LoanDebtResponse();
        response.setBalance(result);

        return response;

    }

    @Override
    public List<LoanBetweenDatesResponse> getListLoanBetweenDates(Long userId, LoanBetweenDatesRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Optional<User> userFound = userService.getUser(userId);
        User user = userFound.get();

        List<Loan> loans = loanRepository.findAll();
        List<LoanBetweenDatesResponse> resultByUser = new ArrayList<>();
        List<LoanBetweenDatesResponse> resultByDates = new ArrayList<>();

        for (Loan loan:loans) {
            if(loan.getUser().equals(user)){

                LoanBetweenDatesResponse list = new LoanBetweenDatesResponse();
                list.setLoan_id(loan.getId());
                list.setAmount(loan.getAmount());
                list.setTerm(loan.getTerm());
                list.setRate(loan.getCategory().getRate());
                list.setUser_id(loan.getUser().getId());
                list.setDate(loan.getDate());
                list.setTarget(loan.getCategory().getName());
                resultByUser.add(list);

            }
        }

        for (LoanBetweenDatesResponse response:resultByUser) {

            LocalDateTime dateTime = LocalDateTime.parse(response.getDate(),formatter);

            if (request.getFrom().isBefore(dateTime)&&request.getTo().isAfter(dateTime)){

                resultByDates.add(response);
            }

        }

        return resultByDates;
    }

    public Category getCategoryByLoan(User user){

        List<Category> categories = categoryService.getAllCategories();

        for (Category category:categories) {
            if(user.getVolumen_prestamos()>0&&user.getVolumen_prestamos()<100000){
                if (user.getTotal_prestamos()>0&&user.getTotal_prestamos()<2){
                    response =  category;
                }
            }else{
                if (user.getVolumen_prestamos()>=100000&&user.getVolumen_prestamos()<500000){
                    if (user.getTotal_prestamos()>=2&&user.getTotal_prestamos()<5){
                        response =  category;
                    }
                }else if(user.getVolumen_prestamos()>=500000&&user.getTotal_prestamos()>=5){
                    response =  category;
                }
            }
        }
        return response;
    }

    public double calculateInstallment(Loan loan){

        Category category = loan.getCategory();

        double rate = categoryService.getRateByCategoryId(category.getId());

        double r = rate/12;

        double potencia = loan.getTerm()-1;
        double divide = Math.pow((1 + r),potencia);

        return  (r + r/divide)* loan.getAmount();
    }
}
