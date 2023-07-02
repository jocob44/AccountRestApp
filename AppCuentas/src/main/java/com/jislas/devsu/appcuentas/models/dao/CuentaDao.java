package com.jislas.devsu.appcuentas.models.dao;

import com.jislas.devsu.appcuentas.models.entity.Cliente;
import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaDao extends JpaRepository<Cuenta, Long> {

    boolean existsByNumeroCuenta(Long numeroCuenta);

    Cuenta findByNumeroCuenta(Long numeroCuenta);

    List<Cuenta> findByCliente(Cliente cliente);
}