package com.jislas.devsu.appcuentas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoCuenta {
    ACTIVA("Activa"),
    INACTIVA("Inactiva");

    private final String descripcion;
}