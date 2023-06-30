package com.jislas.devsu.appcuentas.models.dto;

import com.jislas.devsu.appcuentas.models.entity.TipoMovimiento;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MovimientoDto {
    private LocalDate fecha;
    private TipoMovimiento tipoMovimiento;
    private double valor;
    private double saldo;
}