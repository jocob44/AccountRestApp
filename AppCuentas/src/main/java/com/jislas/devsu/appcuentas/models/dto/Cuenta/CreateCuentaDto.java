package com.jislas.devsu.appcuentas.models.dto.Cuenta;

import com.jislas.devsu.appcuentas.models.entity.EstadoCuenta;
import com.jislas.devsu.appcuentas.models.entity.TipoCuenta;
import com.jislas.devsu.appcuentas.validators.IsUniqueNumeroCuenta;
import com.jislas.devsu.appcuentas.validators.ValidClientId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCuentaDto {

    @NotNull
    @IsUniqueNumeroCuenta
    private Long numeroCuenta;

    @NotNull
    private BigDecimal saldoInicial;
    private EstadoCuenta estado;

    @ValidClientId(message = "El Client Id debe ser válido")
    private String clienteId;
    private TipoCuenta tipo;
}