package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.handlers.OperacionInvalidaException;
import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import com.jislas.devsu.appcuentas.models.dto.Cuenta.CreateCuentaDto;
import com.jislas.devsu.appcuentas.models.dto.Cuenta.CuentaDto;
import com.jislas.devsu.appcuentas.models.entity.Cliente;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {
    private final CuentaDao cuentaDao;

    private final ClienteDao clienteDao;
    private final MessageSource messageSource;

    @Autowired
    public CuentaServiceImpl(CuentaDao cuentaDao, ClienteDao clienteDao, MessageSource messageSource) {
        this.cuentaDao = cuentaDao;
        this.clienteDao = clienteDao;
        this.messageSource = messageSource;
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
    public CuentaDto createCuenta(CreateCuentaDto cuentaDto) {
        Cuenta cuenta = convertToEntity(cuentaDto);
        Cuenta savedCuenta = cuentaDao.save(cuenta);
        return convertToDto(savedCuenta);
    }

    @Override
    public CuentaDto updateCuenta(Long id, CuentaDto cuentaDto) {
        Cuenta cuentaExistente = cuentaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));


        if (!cuentaDto.getSaldoInicial().equals(cuentaExistente.getSaldoInicial()) && cuentaExistente.getLastMovement() != null) {
            throw new OperacionInvalidaException("No es posible modificar el saldo inicial de una cuenta que ya tien movimientos");
        }
        if (!cuentaDto.getNumeroCuenta().equals(cuentaExistente.getNumeroCuenta()) && this.cuentaDao.existsByNumeroCuenta(cuentaDto.getNumeroCuenta())) {
            throw new OperacionInvalidaException("No es posible modificar el numero de cuenta, ya que es usada en otra cuenta. ");
        }

        if (cuentaDto.getEstado() != null) {
            cuentaExistente.setEstado(cuentaDto.getEstado());
        }

        if (cuentaDto.getTipo() != null) {
            cuentaExistente.setTipo(cuentaDto.getTipo());
        }

        Cuenta updatedCuenta = cuentaDao.save(cuentaExistente);
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

    private Cuenta convertToEntity(CreateCuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);
        Cliente client = clienteDao.getClienteByClienteId(cuentaDto.getClienteId());
        cuenta.setCliente(client);
        return cuenta;
    }

    @Override
    public CuentaDto partialUpdateCuenta(Long id, CuentaDto cuentaDto) {
        Cuenta cuentaExistente = cuentaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La cuenta con ID " + id + " no existe."));

        // Aplicar las actualizaciones parciales a la cuenta existente

        if (cuentaDto.getSaldoInicial() != null && cuentaExistente.getLastMovement() != null) {
            throw new OperacionInvalidaException("No es posible modificar el saldo inicial de una cuenta que ya tien movimientos");
        }
        if (cuentaDto.getNumeroCuenta() != null) {
            cuentaExistente.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        }

        if (cuentaDto.getEstado() != null) {
            cuentaExistente.setEstado(cuentaDto.getEstado());
        }

        if (cuentaDto.getTipo() != null) {
            cuentaExistente.setTipo(cuentaDto.getTipo());
        }

        Cuenta cuentaActualizada = cuentaDao.save(cuentaExistente);

        CuentaDto cuentaDtoActualizada = new CuentaDto();
        BeanUtils.copyProperties(cuentaActualizada, cuentaDtoActualizada);
        return cuentaDtoActualizada;
    }


}
