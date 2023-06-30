package com.jislas.devsu.appcuentas.models.dto.movimiento;

import com.jislas.devsu.appcuentas.validators.ValidCuentaId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMovimientoDto {

    @NotNull
    private double valor;

    @ValidCuentaId(message = "La propiedad cuentaId debe ser válido")
    private Long cuentaId;
}