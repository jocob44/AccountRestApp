package com.jislas.devsu.appcuentas.validators;

import com.jislas.devsu.appcuentas.models.dao.CuentaDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidCuentaIdValidator implements ConstraintValidator<ValidCuentaId, Long> {

    private final CuentaDao cuentaDao;

    @Autowired
    public ValidCuentaIdValidator(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public void initialize(ValidCuentaId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        return id != null && cuentaDao.existsById(id);
    }
}