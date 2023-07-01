package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import com.jislas.devsu.appcuentas.models.entity.TipoMovimiento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoBuilder {
    private BigDecimal monto;

    private LocalDate fecha;
    private Cuenta cuenta;

    public MovimientoBuilder withMonto(BigDecimal monto) {
        this.monto = monto;
        return this;
    }

    public MovimientoBuilder withFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public MovimientoBuilder withCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        return this;
    }

    public Movimiento build() {
        Movimiento movimiento = new Movimiento();
        movimiento.setValor(monto);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(fecha);

        if (cuenta.getLastMovement() != null) {
            movimiento.setSaldo(monto.add(cuenta.getLastMovement().getSaldo()));
        } else {
            movimiento.setSaldo(monto.add(cuenta.getSaldoInicial()));
        }

        if (monto.compareTo(BigDecimal.ZERO) >= 0) {
            movimiento.setTipoMovimiento(TipoMovimiento.CREDITO);
        } else {
            movimiento.setTipoMovimiento(TipoMovimiento.DEBITO);
        }

        return movimiento;
    }
}