package com.mercadolibre.challenge.loan.domain.models;

import com.mercadolibre.challenge.category.domain.models.Category;
import com.mercadolibre.challenge.user.domain.models.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    private Integer term;
    private double balance;

    private String date;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Category category;

    @PrePersist
    void prePersistFecha(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.now().format(formatter);
    }

}
