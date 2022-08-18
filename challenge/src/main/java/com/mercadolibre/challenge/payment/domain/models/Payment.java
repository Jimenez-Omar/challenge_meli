package com.mercadolibre.challenge.payment.domain.models;

import com.mercadolibre.challenge.loan.domain.models.Loan;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Loan loan;

    private LocalDateTime date;

    @PrePersist
    void prePersistFecha(){
        this.date = LocalDateTime.now();
    }
}
