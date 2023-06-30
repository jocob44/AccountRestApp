package com.jislas.devsu.appcuentas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMovimiento {
    DEBITO("Débito"),
    CREDITO("Crédito");

    private final String descripcion;
}