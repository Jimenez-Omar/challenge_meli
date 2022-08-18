package com.mercadolibre.challenge.user.domain.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer total_prestamos;
    private Integer volumen_prestamos;
}
