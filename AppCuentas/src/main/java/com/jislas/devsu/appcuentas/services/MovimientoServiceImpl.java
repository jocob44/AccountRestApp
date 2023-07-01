package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.handlers.OperacionInvalidaException;
import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import com.jislas.devsu.appcuentas.models.dao.MovimientoDao;
import com.jislas.devsu.appcuentas.models.dto.movimiento.CreateMovimientoDto;
import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import com.jislas.devsu.appcuentas.models.entity.TipoMovimiento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    private final CuentaDao cuentaDao;
    private final MovimientoDao movimientoDao;
    @Value("${daily_limit}")
    private BigDecimal DAILY_DEBIT_LIMIT;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public MovimientoServiceImpl(MovimientoDao movimientoDao, CuentaDao cuentaDao) {
        this.movimientoDao = movimientoDao;
        this.cuentaDao = cuentaDao;
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
    public MovimientoDto createMovimiento(CreateMovimientoDto movimientoDto) {

        Cuenta cuenta = this.cuentaDao.findByNumeroCuenta(movimientoDto.getNumeroCuenta());
        validarMovimiento(movimientoDto, cuenta);

        Movimiento movimiento = new MovimientoBuilder()
                .withMonto(movimientoDto.getValor())
                .withCuenta(cuenta)
                .withFecha(movimientoDto.getFecha())
                .build();
        Movimiento savedMovimiento = movimientoDao.save(movimiento);
        cuenta.setLastMovement(savedMovimiento);
        cuentaDao.save(cuenta);
        return convertToDto(savedMovimiento);
    }

    public void validarMovimiento(CreateMovimientoDto movimiento, Cuenta cuenta) {

        if (movimiento.getValor().compareTo(BigDecimal.ZERO) == 0) {
            throw new OperacionInvalidaException("El valor deber ser distinto de cero");
        }
        if (movimiento.getValor().compareTo(BigDecimal.ZERO) < 0) {

            if (movimiento.getValor().multiply(BigDecimal.valueOf(-1)).compareTo(DAILY_DEBIT_LIMIT) > 0) {
                throw new OperacionInvalidaException("Cupo Diario Excedido");
            }

            BigDecimal valorAcumulado = this.movimientoDao.sumarValorOperacionesPorTipoCuentaYFecha(TipoMovimiento.DEBITO, movimiento.getNumeroCuenta(), movimiento.getFecha());
            if (valorAcumulado == null) {
                valorAcumulado = BigDecimal.ZERO;
            }

            if (valorAcumulado.add(movimiento.getValor()).multiply(BigDecimal.valueOf(-1)).compareTo(DAILY_DEBIT_LIMIT) > 0) {
                throw new OperacionInvalidaException("Cupo Diario Excedido");
            }

            BigDecimal saldo = (cuenta.getLastMovement() != null ? cuenta.getLastMovement().getSaldo() : cuenta.getSaldoInicial());

            if ((saldo.add(movimiento.getValor()).compareTo(BigDecimal.ZERO)) < 0) {
                throw new OperacionInvalidaException("Saldo no Disponible");
            }
        }
    }

    @Override
    public MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto) {
        Movimiento existingMovimiento = movimientoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.invalid.movimiento.id.inexistente", new Object[]{id}, LocaleContextHolder.getLocale())
                ));
        BeanUtils.copyProperties(movimientoDto, existingMovimiento, "id");
        Movimiento updatedMovimiento = movimientoDao.save(existingMovimiento);
        return convertToDto(updatedMovimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        if (!movimientoDao.existsById(id)) {
            throw new ResourceNotFoundException(messageSource.getMessage("error.invalid.movimiento.id.inexistente", new Object[]{id}, LocaleContextHolder.getLocale())
            );
        }
        movimientoDao.deleteById(id);
    }


    @Override
    public MovimientoDto partialUpdateMovimiento(Long id, MovimientoDto movimientoDto) {
        Movimiento movimientoExistente = movimientoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.invalid.movimiento.id.inexistente", new Object[]{id}, LocaleContextHolder.getLocale())
                ));

        // Aplicar las actualizaciones parciales al movimiento existente
        if (movimientoDto.getTipoMovimiento() != null) {
            movimientoExistente.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        }

        if (movimientoDto.getFecha() != null) {
            throw new OperacionInvalidaException(messageSource.getMessage("error.invalid.movimiento.fecha", null, LocaleContextHolder.getLocale()));
        }

        if (movimientoDto.getSaldo() != null) {
            throw new OperacionInvalidaException(messageSource.getMessage("error.invalid.movimiento.saldo", null, LocaleContextHolder.getLocale()));
        }

        if (movimientoDto.getId() != null) {
            throw new OperacionInvalidaException(messageSource.getMessage("error.invalid.movimiento.id", null, LocaleContextHolder.getLocale()));
        }

        if (movimientoDto.getValor() != null) {
            throw new OperacionInvalidaException(messageSource.getMessage("error.invalid.movimiento.valor", null, LocaleContextHolder.getLocale()));
        }
        Movimiento movimientoActualizado = movimientoDao.save(movimientoExistente);
        MovimientoDto movimientoDtoActualizado = new MovimientoDto();
        BeanUtils.copyProperties(movimientoActualizado, movimientoDtoActualizado);
        return movimientoDtoActualizado;
    }

    private MovimientoDto convertToDto(Movimiento movimiento) {
        MovimientoDto movimientoDto = new MovimientoDto();
        BeanUtils.copyProperties(movimiento, movimientoDto);
        return movimientoDto;
    }

}
