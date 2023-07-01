package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.movimiento.CreateMovimientoDto;
import com.jislas.devsu.appcuentas.models.dto.movimiento.MovimientoDto;
import com.jislas.devsu.appcuentas.services.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createMovimiento(@Valid @RequestBody CreateMovimientoDto movimientoDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errores = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

        }
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

    @PatchMapping("/{id}")
    public ResponseEntity<MovimientoDto> partialUpdateMovimiento(@PathVariable Long id, @RequestBody MovimientoDto movimientoDto) {
        MovimientoDto patchedMovimiento = movimientoService.partialUpdateMovimiento(id, movimientoDto);
        return ResponseEntity.ok(patchedMovimiento);
    }
}
