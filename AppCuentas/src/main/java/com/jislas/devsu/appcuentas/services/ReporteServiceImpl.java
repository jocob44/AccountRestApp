package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import com.jislas.devsu.appcuentas.models.dao.MovimientoDao;
import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;
import com.jislas.devsu.appcuentas.models.dto.reportes.ReporteCuentaDto;
import com.jislas.devsu.appcuentas.models.entity.Cliente;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {


    private final MovimientoDao movimientoDao;

    private final CuentaDao cuentaDao;

    private final ClienteDao clienteDao;

    @Autowired
    public ReporteServiceImpl(MovimientoDao movimientoDao, CuentaDao cuentaDao, ClienteDao clienteDao) {
        this.movimientoDao = movimientoDao;
        this.cuentaDao = cuentaDao;
        this.clienteDao = clienteDao;
    }


    @Override
    public List<ReporteCuentaDto> generarReporteCuentas(String clienteId, LocalDate fechaDesde, LocalDate fechaHasta) {

        Cliente cliente = clienteDao.getClienteByClienteId(clienteId);
        List<Cuenta> cuentas = cuentaDao.findByCliente(cliente);
        List<ReporteCuentaDto> reporteCuentas = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            BigDecimal saldo = cuenta.getLastMovement() != null ? cuenta.getLastMovement().getSaldo() : cuenta.getSaldoInicial();
            List<MovimientoDto> movimientos = obtenerMovimientos(cuenta, fechaDesde, fechaHasta);
            String nombreCliente = cuenta.getCliente().getNombre();

            ReporteCuentaDto cuentaReporteDto = new ReporteCuentaDto(cuenta.getId(), cuenta.getNumeroCuenta(), saldo, movimientos, nombreCliente);
            reporteCuentas.add(cuentaReporteDto);
        }

        return reporteCuentas;
    }


    private List<MovimientoDto> obtenerMovimientos(Cuenta cuenta, LocalDate fechaDesde, LocalDate fechaHasta) {
        List<Movimiento> movimientos = movimientoDao.findByCuentaAndFechaBetween(cuenta, fechaDesde, fechaHasta);
        List<MovimientoDto> movimientoDtos = new ArrayList<>();

        for (Movimiento movimiento : movimientos) {
            MovimientoDto movimientoDto = convertToDto(movimiento);
            movimientoDtos.add(movimientoDto);
        }

        return movimientoDtos;
    }

    private MovimientoDto convertToDto(Movimiento movimiento) {
        MovimientoDto movimientoDto = new MovimientoDto();
        BeanUtils.copyProperties(movimiento, movimientoDto);
        return movimientoDto;
    }

}
