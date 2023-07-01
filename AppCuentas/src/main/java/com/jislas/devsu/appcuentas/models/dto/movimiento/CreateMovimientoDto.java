package com.jislas.devsu.appcuentas.models.dto.movimiento;

import com.jislas.devsu.appcuentas.validators.ValidNumeroCuenta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateMovimientoDto {

    @NotNull
    private BigDecimal valor;

    @ValidNumeroCuenta(message = "La propiedad numeroCuenta debe ser válido")
    private Long numeroCuenta;

    private LocalDate fecha;
}