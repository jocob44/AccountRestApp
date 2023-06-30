package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.MovimientoDto;
import com.jislas.devsu.appcuentas.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDto>> getAllMovimientos() {
        List<MovimientoDto> movimientos = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> getMovimientoById(@PathVariable Long id) {
        MovimientoDto movimiento = movimientoService.getMovimientoById(id);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<MovimientoDto> createMovimiento(@RequestBody MovimientoDto movimientoDto) {
        MovimientoDto createdMovimiento = movimientoService.createMovimiento(movimientoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDto movimientoDto) {
        MovimientoDto updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDto);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
