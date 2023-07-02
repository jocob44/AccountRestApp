package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.reportes.ReporteCuentaDto;
import com.jislas.devsu.appcuentas.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteCuentaDto>> generarReporteCuentas(
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @RequestParam("clienteId") String clienteId) {

        List<ReporteCuentaDto> reporteCuentas = reporteService.generarReporteCuentas(clienteId, fechaDesde, fechaHasta);

        return ResponseEntity.ok(reporteCuentas);
    }
}