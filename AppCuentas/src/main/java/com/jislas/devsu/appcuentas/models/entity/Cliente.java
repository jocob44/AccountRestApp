package com.jislas.devsu.appcuentas.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cliente extends Persona {
    private String clientId;
    private String contrasena;
    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;
}