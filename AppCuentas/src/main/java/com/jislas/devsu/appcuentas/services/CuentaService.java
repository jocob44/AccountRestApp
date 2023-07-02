package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.Cuenta.CreateCuentaDto;
import com.jislas.devsu.appcuentas.models.dto.Cuenta.CuentaDto;

import java.util.List;

public interface CuentaService {
    List<CuentaDto> getAllCuentas();

    CuentaDto getCuentaById(Long id);

    CuentaDto createCuenta(CreateCuentaDto cuentaDto);

    CuentaDto updateCuenta(Long id, CuentaDto cuentaDto);

    void deleteCuenta(Long id);

    CuentaDto partialUpdateCuenta(Long id, CuentaDto cuentaDto);

}