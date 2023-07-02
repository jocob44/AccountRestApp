package com.jislas.devsu.appcuentas.models.dto.reportes;

import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ReporteCuentaDto {
    private Long id;
    private Long numeroCuenta;
    private BigDecimal saldo;
    private List<MovimientoDto> movimientos;
    private String nombreCliente;

}
