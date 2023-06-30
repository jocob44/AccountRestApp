package com.jislas.devsu.appcuentas.models.dao;
import com.jislas.devsu.appcuentas.models.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoDao extends JpaRepository<Movimiento, Long> {
}