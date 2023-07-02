package com.jislas.devsu.appcuentas.models.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cliente extends Persona {

    @NotEmpty
    private String clienteId;

    @NotEmpty
    private String contrasena;
    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;
}