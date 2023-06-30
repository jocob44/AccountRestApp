package com.jislas.devsu.appcuentas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoCliente {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String descripcion;
}