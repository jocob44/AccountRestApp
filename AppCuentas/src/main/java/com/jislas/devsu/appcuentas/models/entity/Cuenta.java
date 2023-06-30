package com.jislas.devsu.appcuentas.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCuenta;
    private double saldoInicial;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;
}