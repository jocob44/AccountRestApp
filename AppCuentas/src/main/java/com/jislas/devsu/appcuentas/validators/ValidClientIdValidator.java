package com.jislas.devsu.appcuentas.validators;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidClientIdValidator implements ConstraintValidator<ValidClientId, Long> {

    private final ClienteDao clienteDao;

    @Autowired
    public ValidClientIdValidator(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public void initialize(ValidClientId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        return clienteDao.existsById(id);
    }
}