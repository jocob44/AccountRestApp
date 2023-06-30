package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.MovimientoDto;

import java.util.List;

public interface MovimientoService {
    List<MovimientoDto> getAllMovimientos();
    MovimientoDto getMovimientoById(Long id);
    MovimientoDto createMovimiento(MovimientoDto movimientoDto);
    MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto);
    void deleteMovimiento(Long id);
}