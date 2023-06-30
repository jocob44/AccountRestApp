package com.jislas.devsu.appcuentas.models.dto.Cuenta;

import com.jislas.devsu.appcuentas.models.entity.EstadoCuenta;
import com.jislas.devsu.appcuentas.models.entity.TipoCuenta;
import com.jislas.devsu.appcuentas.validators.ValidClientId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCuentaDto {

    @NotBlank(message = "El Numero de cuenta es obligatorio")
    private String numeroCuenta;


    @NotNull
    private double saldoInicial;
    private EstadoCuenta estado;

    @ValidClientId(message = "El Client Id debe ser válido")
    private Long clienteId;
    private TipoCuenta tipo;
}