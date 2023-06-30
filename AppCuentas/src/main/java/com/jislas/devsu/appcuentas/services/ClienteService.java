package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dto.cliente.ClienteDto;
import com.jislas.devsu.appcuentas.models.dto.cliente.CreateClientDto;

import java.util.List;

public interface ClienteService {
    List<ClienteDto> getAllClientes();

    ClienteDto getClienteById(Long id);

    ClienteDto createCliente(CreateClientDto ClienteDto);

    ClienteDto updateCliente(Long id, ClienteDto ClienteDto);
    void deleteCliente(Long id);
    ClienteDto partialUpdateCliente(Long id, ClienteDto clienteDto) ;



}