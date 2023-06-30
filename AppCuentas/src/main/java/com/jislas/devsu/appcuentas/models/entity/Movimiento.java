package com.jislas.devsu.appcuentas.models.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;
    private double valor;
    private double saldo;
}