package com.jislas.devsu.appcuentas.models.dao;

import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import com.jislas.devsu.appcuentas.models.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoDao extends JpaRepository<Movimiento, Long> {

    @Query("SELECT SUM(m.valor) FROM Movimiento m WHERE m.fecha = :fecha AND m.tipoMovimiento = :tipoMovimiento AND m.cuenta.id = :idCuenta")
    BigDecimal sumarValorOperacionesPorTipoCuentaYFecha(@Param("tipoMovimiento") TipoMovimiento tipoMovimiento, @Param("idCuenta") Long idCuenta, @Param("fecha") LocalDate fecha);

    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin);

}