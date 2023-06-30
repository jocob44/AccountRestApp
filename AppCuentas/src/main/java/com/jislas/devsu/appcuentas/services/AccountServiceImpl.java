package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import com.jislas.devsu.appcuentas.models.dto.CuentaDto;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final CuentaDao cuentaDao;

    @Autowired
    public AccountServiceImpl(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public List<CuentaDto> getAllCuentas() {
        List<Cuenta> cuentas = cuentaDao.findAll();
        return cuentas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto getCuentaById(Long id) {
        Cuenta cuenta = cuentaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));
        return convertToDto(cuenta);
    }

    @Override
    public CuentaDto createCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = convertToEntity(cuentaDto);
        Cuenta savedCuenta = cuentaDao.save(cuenta);
        return convertToDto(savedCuenta);
    }

    @Override
    public CuentaDto updateCuenta(Long id, CuentaDto cuentaDto) {
        Cuenta existingCuenta = cuentaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));
        BeanUtils.copyProperties(cuentaDto, existingCuenta, "id");
        Cuenta updatedCuenta = cuentaDao.save(existingCuenta);
        return convertToDto(updatedCuenta);
    }

    @Override
    public void deleteCuenta(Long id) {
        if (!cuentaDao.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta no encontrada con ID: " + id);
        }
        cuentaDao.deleteById(id);
    }

    private CuentaDto convertToDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    private Cuenta convertToEntity(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);
        return cuenta;
    }
}
