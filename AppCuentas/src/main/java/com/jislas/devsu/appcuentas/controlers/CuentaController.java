package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.Cuenta.CreateCuentaDto;
import com.jislas.devsu.appcuentas.models.dto.Cuenta.CuentaDto;
import com.jislas.devsu.appcuentas.services.CuentaService;
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
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDto>> getAllCuentas() {
        List<CuentaDto> cuentas = cuentaService.getAllCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable Long id) {
        CuentaDto cuenta = cuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@Valid @RequestBody CreateCuentaDto cuentaDto, BindingResult result) {

        if (result.hasErrors()) {
             List<String> errores = result.getAllErrors().stream()
                     .map(DefaultMessageSourceResolvable::getDefaultMessage)
                     .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

        }
        CuentaDto createdCuenta = cuentaService.createCuenta(cuentaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> updateCuenta(@PathVariable Long id, @RequestBody CuentaDto cuentaDto) {
        CuentaDto updatedCuenta = cuentaService.updateCuenta(id, cuentaDto);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CuentaDto> partialUpdateCuenta(@PathVariable Long id, @RequestBody CuentaDto cuentaDto) {
        CuentaDto patchedCuenta = cuentaService.partialUpdateCuenta(id, cuentaDto);
        return ResponseEntity.ok(patchedCuenta);
    }
}
