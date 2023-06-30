package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.CuentaDto;

import java.util.List;

public interface AccountService {
    List<CuentaDto> getAllCuentas();
    CuentaDto getCuentaById(Long id);
    CuentaDto createCuenta(CuentaDto cuentaDto);
    CuentaDto updateCuenta(Long id, CuentaDto cuentaDto);
    void deleteCuenta(Long id);
}