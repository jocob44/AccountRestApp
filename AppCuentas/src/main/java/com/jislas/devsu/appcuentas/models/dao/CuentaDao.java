package com.jislas.devsu.appcuentas.models.dao;

import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaDao extends JpaRepository<Cuenta, Long> {

    boolean existsByNumeroCuenta(Long numeroCuenta);

    Cuenta findByNumeroCuenta(Long numeroCuenta);
}