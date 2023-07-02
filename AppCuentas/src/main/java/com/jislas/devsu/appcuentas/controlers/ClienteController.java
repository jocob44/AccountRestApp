package com.jislas.devsu.appcuentas.controlers;

import com.jislas.devsu.appcuentas.models.dto.cliente.ClienteDto;
import com.jislas.devsu.appcuentas.models.dto.cliente.CreateClienteDto;
import com.jislas.devsu.appcuentas.services.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        ClienteDto cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@Valid @RequestBody CreateClienteDto clienteDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errores = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

        }
        ClienteDto createdCliente = clienteService.createCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto updatedCliente = clienteService.updateCliente(id, clienteDto);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDto> partialUpdateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto patchedCliente = clienteService.partialUpdateCliente(id, clienteDto);
        return ResponseEntity.ok(patchedCliente);
    }
}