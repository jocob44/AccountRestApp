package com.jislas.devsu.appcuentas.models.dto;

import com.jislas.devsu.appcuentas.models.entity.EstadoCliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends PersonaDto {
    private String clientId;
    private String contrasena;
    private EstadoCliente estado;
}