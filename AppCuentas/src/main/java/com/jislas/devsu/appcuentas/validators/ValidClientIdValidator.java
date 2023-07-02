package com.jislas.devsu.appcuentas.validators;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidClientIdValidator implements ConstraintValidator<ValidClientId, String> {

    private final ClienteDao clienteDao;

    @Autowired
    public ValidClientIdValidator(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public void initialize(ValidClientId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {

        return id != null && clienteDao.existsByClienteId(id);
    }
}