package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.movimiento.CreateMovimientoDto;
import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;

import java.util.List;

public interface MovimientoService {
    List<MovimientoDto> getAllMovimientos();

    MovimientoDto getMovimientoById(Long id);

    MovimientoDto createMovimiento(CreateMovimientoDto movimientoDto);

    MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto);

    void deleteMovimiento(Long id);

    MovimientoDto partialUpdateMovimiento(Long id, MovimientoDto movimientoDto);
}