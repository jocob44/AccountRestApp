package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.Cuenta.CreateCuentaDto;
import com.jislas.devsu.appcuentas.models.dto.Cuenta.CuentaDto;
import com.jislas.devsu.appcuentas.services.AccountService;
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
    public ResponseEntity<?> createCuenta(@Valid @RequestBody CreateCuentaDto cuentaDto, BindingResult result) {

        if (result.hasErrors()) {
            // Obtener los errores de validación
            List<String> errores = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            // Devolver una respuesta de error con los mensajes de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

        }
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
