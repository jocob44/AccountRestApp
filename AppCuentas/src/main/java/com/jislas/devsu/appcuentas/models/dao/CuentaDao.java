package com.jislas.devsu.appcuentas.models.dao;

import com.jislas.devsu.appcuentas.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaDao extends JpaRepository<Cuenta, Long> {
}