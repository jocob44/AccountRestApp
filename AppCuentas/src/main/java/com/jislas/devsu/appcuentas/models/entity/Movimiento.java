package com.jislas.devsu.appcuentas.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @NotNull
    private double valor;
    @NotNull
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}