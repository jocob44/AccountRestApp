package com.jislas.devsu.appcuentas.models.dto;

import com.jislas.devsu.appcuentas.models.entity.EstadoCuenta;
import lombok.Data;

@Data
public class CuentaDto {
    private String numeroCuenta;
    private double saldoInicial;
    private EstadoCuenta estado;
}