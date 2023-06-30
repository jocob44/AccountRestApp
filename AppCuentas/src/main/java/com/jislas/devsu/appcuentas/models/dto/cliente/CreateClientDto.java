package com.jislas.devsu.appcuentas.models.dto.cliente;

import com.jislas.devsu.appcuentas.models.entity.EstadoCliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateClientDto extends CreatePersonaDto {

    private String clientId;
    private String contrasena;
    private EstadoCliente estado;
}
