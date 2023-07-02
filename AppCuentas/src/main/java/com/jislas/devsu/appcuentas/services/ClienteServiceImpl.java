package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import com.jislas.devsu.appcuentas.models.dto.cliente.ClienteDto;
import com.jislas.devsu.appcuentas.models.dto.cliente.CreateClienteDto;
import com.jislas.devsu.appcuentas.models.entity.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
    private final ClienteDao clienteDao;

    @Autowired
    public ClienteServiceImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteDao.findAll();
        return clientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClienteDto getClienteById(Long id) {
        Cliente cliente = clienteDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return convertToDto(cliente);
    }

    public ClienteDto createCliente(CreateClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        Cliente savedCliente = clienteDao.save(cliente);
        return convertToDto(savedCliente);
    }

    public ClienteDto updateCliente(Long id, ClienteDto clienteDto) {
        Cliente existingCliente = clienteDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        BeanUtils.copyProperties(clienteDto, existingCliente, "id");
        Cliente updatedCliente = clienteDao.save(existingCliente);
        return convertToDto(updatedCliente);
    }

    public void deleteCliente(Long id) {
        if (!clienteDao.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteDao.deleteById(id);
    }

    @Override
    public ClienteDto partialUpdateCliente(Long id, ClienteDto clienteDto) {
        Cliente cliente = clienteDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (clienteDto.getNombre() != null) {
            cliente.setNombre(clienteDto.getNombre());
        }
        if (clienteDto.getGenero() != null) {
            cliente.setGenero(clienteDto.getGenero());
        }
        if (clienteDto.getEdad() != null) {
            cliente.setEdad(clienteDto.getEdad());
        }
        if (clienteDto.getIdentificacion() != null) {
            cliente.setIdentificacion(clienteDto.getIdentificacion());
        }
        if (clienteDto.getDireccion() != null) {
            cliente.setDireccion(clienteDto.getDireccion());
        }
        if (clienteDto.getTelefono() != null) {
            cliente.setTelefono(clienteDto.getTelefono());
        }

        Cliente updatedCliente = clienteDao.save(cliente);
        return convertToDto(updatedCliente);
    }

    private ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    private Cliente convertToEntity(CreateClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        return cliente;
    }
}