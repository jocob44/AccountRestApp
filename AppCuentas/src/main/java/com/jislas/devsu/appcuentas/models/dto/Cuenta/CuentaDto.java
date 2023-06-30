package com.jislas.devsu.appcuentas.models.dto.Cuenta;

import com.jislas.devsu.appcuentas.models.entity.EstadoCuenta;
import com.jislas.devsu.appcuentas.models.entity.TipoCuenta;
import lombok.Data;

@Data
public class CuentaDto {
    private Long id;
    private String numeroCuenta;
    private double saldoInicial;
    private EstadoCuenta estado;
    private TipoCuenta tipo;
}