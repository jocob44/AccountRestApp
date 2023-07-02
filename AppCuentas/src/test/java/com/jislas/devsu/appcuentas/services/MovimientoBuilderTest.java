package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import com.jislas.devsu.appcuentas.models.entity.TipoMovimiento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovimientoBuilderTest {

    @Test
    public void testBuild() {
        //Arrange
        BigDecimal monto = new BigDecimal("100");
        LocalDate fecha = LocalDate.now();
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldoInicial(new BigDecimal("500"));

        MovimientoBuilder movimientoBuilder = new MovimientoBuilder();

        //Act
        Movimiento movimiento = movimientoBuilder
                .withMonto(monto)
                .withFecha(fecha)
                .withCuenta(cuenta)
                .build();
        //Expect
        assertEquals(monto, movimiento.getValor());
        assertEquals(fecha, movimiento.getFecha());
        assertEquals(cuenta, movimiento.getCuenta());
        assertEquals(new BigDecimal("600"), movimiento.getSaldo());
        assertEquals(TipoMovimiento.CREDITO, movimiento.getTipoMovimiento());
    }
}
