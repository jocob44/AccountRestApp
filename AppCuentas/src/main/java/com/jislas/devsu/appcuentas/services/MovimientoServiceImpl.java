package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.MovimientoDao;
import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoDao movimientoDao;

    @Autowired
    public MovimientoServiceImpl(MovimientoDao movimientoDao) {
        this.movimientoDao = movimientoDao;
    }

    @Override
    public List<MovimientoDto> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoDao.findAll();
        return movimientos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDto getMovimientoById(Long id) {
        Movimiento movimiento = movimientoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));
        return convertToDto(movimiento);
    }

    @Override
    public MovimientoDto createMovimiento(MovimientoDto movimientoDto) {
        Movimiento movimiento = convertToEntity(movimientoDto);
        Movimiento savedMovimiento = movimientoDao.save(movimiento);
        return convertToDto(savedMovimiento);
    }

    @Override
    public MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto) {
        Movimiento existingMovimiento = movimientoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con ID: " + id));
        BeanUtils.copyProperties(movimientoDto, existingMovimiento, "id");
        Movimiento updatedMovimiento = movimientoDao.save(existingMovimiento);
        return convertToDto(updatedMovimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        if (!movimientoDao.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento no encontrado con ID: " + id);
        }
        movimientoDao.deleteById(id);
    }

    private MovimientoDto convertToDto(Movimiento movimiento) {
        MovimientoDto movimientoDto = new MovimientoDto();
        BeanUtils.copyProperties(movimiento, movimientoDto);
        return movimientoDto;
    }

    private Movimiento convertToEntity(MovimientoDto movimientoDto) {
        Movimiento movimiento = new Movimiento();
        BeanUtils.copyProperties(movimientoDto, movimiento);
        return movimiento;
    }
}
