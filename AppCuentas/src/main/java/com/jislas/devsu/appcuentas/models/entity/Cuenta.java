package com.jislas.devsu.appcuentas.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long numeroCuenta;

    @NotNull
    private BigDecimal saldoInicial;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;

    @ManyToOne
    @JoinColumn(name = "last_movement_id")
    private Movimiento lastMovement;


}