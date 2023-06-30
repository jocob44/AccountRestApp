package com.jislas.devsu.appcuentas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCuenta {
    CAJA_DE_AHORRO("Caja de Ahorro"),
    CUENTA_CORRIENTE("Cuenta Corriente");

    private final String nombre;
}