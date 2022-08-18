package com.mercadolibre.challenge.loan.domain.services;

import com.mercadolibre.challenge.loan.domain.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "loan",collectionResourceRel = "loan")
public interface ILoanRepository extends JpaRepository<Loan,Long> {
}
