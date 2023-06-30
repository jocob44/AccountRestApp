package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.CuentaDto;
import com.jislas.devsu.appcuentas.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final AccountService accountService;

    @Autowired
    public CuentaController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDto>> getAllCuentas() {
        List<CuentaDto> cuentas = accountService.getAllCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable Long id) {
        CuentaDto cuenta = accountService.getCuentaById(id);
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<CuentaDto> createCuenta(@RequestBody CuentaDto cuentaDto) {
        CuentaDto createdCuenta = accountService.createCuenta(cuentaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> updateCuenta(@PathVariable Long id, @RequestBody CuentaDto cuentaDto) {
        CuentaDto updatedCuenta = accountService.updateCuenta(id, cuentaDto);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        accountService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
