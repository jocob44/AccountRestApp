package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.reportes.ReporteCuentaDto;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {
    List<ReporteCuentaDto> generarReporteCuentas(String clienteId, LocalDate fechaDesde, LocalDate fechaHasta);
}
