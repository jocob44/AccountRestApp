package com.jislas.devsu.appcuentas.models.dto.cliente;

import com.jislas.devsu.appcuentas.models.entity.EstadoCliente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateClienteDto extends CreatePersonaDto {

    @NotBlank
    private String clienteId;
    private String contrasena;
    private EstadoCliente estado;
}
