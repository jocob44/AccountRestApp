package com.jislas.devsu.appcuentas.models.dao;

import com.jislas.devsu.appcuentas.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long> {
    Cliente getClienteByClienteId(String clienteId);

    boolean existsByClienteId(String clienteId);
}