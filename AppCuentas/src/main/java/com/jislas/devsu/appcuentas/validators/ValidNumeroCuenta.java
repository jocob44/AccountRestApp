package com.jislas.devsu.appcuentas.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumeroDeCuentaValidator.class)
public @interface ValidNumeroCuenta {
    String message() default "El numero de Cuenta es inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}