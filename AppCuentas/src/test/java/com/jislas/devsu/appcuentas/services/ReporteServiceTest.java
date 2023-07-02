package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import com.jislas.devsu.appcuentas.models.dao.MovimientoDao;
import com.jislas.devsu.appcuentas.models.dto.reportes.ReporteCuentaDto;
import com.jislas.devsu.appcuentas.models.entity.Cliente;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ReporteServiceTest {
    @Mock
    private ClienteDao clienteDao;

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private MovimientoDao movimientoDao;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generarReporteCuentas_ValidInput_ReturnsReporteCuentaDtoList() {
        // Arrange
        String clienteId = "1";
        LocalDate fechaDesde = LocalDate.of(2022, 1, 1);
        LocalDate fechaHasta = LocalDate.of(2022, 1, 31);

        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteId);
        cliente.setNombre("John Doe");

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(1L);
        cuenta1.setNumeroCuenta(12345678L);
        cuenta1.setSaldoInicial(BigDecimal.valueOf(1000));
        cuenta1.setCliente(cliente);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(2L);
        cuenta2.setNumeroCuenta(98765432L);
        cuenta2.setSaldoInicial(BigDecimal.valueOf(2000));
        cuenta2.setCliente(cliente);

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);

        List<Movimiento> movimientos = new ArrayList<>();

        when(clienteDao.getClienteByClienteId(clienteId)).thenReturn(cliente); // Configurar el comportamiento del mock
        when(cuentaDao.findByCliente(cliente)).thenReturn(cuentas);
        when(movimientoDao.findByCuentaAndFechaBetween(any(Cuenta.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(movimientos);

        // Act
        List<ReporteCuentaDto> reporteCuentas = reporteService.generarReporteCuentas(clienteId, fechaDesde, fechaHasta);

        // Assert
        assertEquals(2, reporteCuentas.size());


        ReporteCuentaDto cuentaDto1 = reporteCuentas.get(0);
        assertEquals(1L, cuentaDto1.getId());
        assertEquals(12345678L, cuentaDto1.getNumeroCuenta());
        assertEquals(BigDecimal.valueOf(1000), cuentaDto1.getSaldo());
        assertEquals("John Doe", cuentaDto1.getNombreCliente());

        // Verificar la información de la segunda cuenta
        ReporteCuentaDto cuentaDto2 = reporteCuentas.get(1);
        assertEquals(2L, cuentaDto2.getId());
        assertEquals(98765432L, cuentaDto2.getNumeroCuenta());
        assertEquals(BigDecimal.valueOf(2000), cuentaDto2.getSaldo());
        assertEquals("John Doe", cuentaDto2.getNombreCliente());


    }


}
