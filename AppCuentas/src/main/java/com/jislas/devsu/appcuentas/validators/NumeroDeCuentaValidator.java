package com.jislas.devsu.appcuentas.validators;

import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumeroDeCuentaValidator implements ConstraintValidator<ValidNumeroCuenta, Long> {

    private final CuentaDao cuentaDao;

    @Autowired
    public NumeroDeCuentaValidator(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public void initialize(ValidNumeroCuenta constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        return id != null && cuentaDao.existsByNumeroCuenta(id);
    }
}