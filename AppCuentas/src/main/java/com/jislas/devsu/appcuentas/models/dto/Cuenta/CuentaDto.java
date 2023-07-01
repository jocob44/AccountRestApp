package com.jislas.devsu.appcuentas.models.dto.Cuenta;

import com.jislas.devsu.appcuentas.models.entity.EstadoCuenta;
import com.jislas.devsu.appcuentas.models.entity.TipoCuenta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaDto {
    private Long id;
    private Long numeroCuenta;
    private BigDecimal saldoInicial;
    private EstadoCuenta estado;
    private TipoCuenta tipo;
}