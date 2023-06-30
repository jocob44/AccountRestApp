package com.jislas.devsu.appcuentas.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Genero {
    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTRO("Otro");

    private final String descripcion;
}