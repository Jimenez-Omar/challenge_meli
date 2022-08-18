package com.mercadolibre.challenge.payment.domain.services;


import com.mercadolibre.challenge.payment.domain.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "payment",collectionResourceRel = "payment")
public interface IPaymentRepository extends JpaRepository<Payment,Long> {
}
