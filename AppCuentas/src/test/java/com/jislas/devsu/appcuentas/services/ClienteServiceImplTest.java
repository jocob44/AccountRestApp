package com.jislas.devsu.appcuentas.services;

import com.jislas.devsu.appcuentas.models.dao.ClienteDao;
import com.jislas.devsu.appcuentas.models.dto.cliente.ClienteDto;
import com.jislas.devsu.appcuentas.models.entity.Cliente;
import com.jislas.devsu.appcuentas.models.entity.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClientes() {
        // Configurar datos de prueba
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(getCliente(1L, "John Doe", Genero.MASCULINO, 30, "12345", "123 Main St", "123-456-7890"));
        clientes.add(getCliente(2L, "Jane Smith", Genero.FEMENINO, 25, "67890", "456 Elm St", "987-654-3210"));

        // Configurar comportamiento del clienteDao mock
        when(clienteDao.findAll()).thenReturn(clientes);

        // Act
        List<ClienteDto> result = clienteService.getAllClientes();

        // Expect
        assertEquals(clientes.size(), result.size());

        // Verificar el resultado
        assertEquals(clientes.size(), result.size());
        assertEquals("John Doe", result.get(0).getNombre());
        assertEquals("Jane Smith", result.get(1).getNombre());

        // Verificar que se llamó al método correspondiente en el clienteDao
        verify(clienteDao, times(1)).findAll();
    }

    @Test
    public void testGetClienteById() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = getCliente(clienteId, "John Doe", Genero.MASCULINO, 30, "12345", "123 Main St", "123-456-7890");

        when(clienteDao.findById(clienteId)).thenReturn(Optional.of(cliente));

        // Run
        ClienteDto result = clienteService.getClienteById(clienteId);

        // Then
        assertNotNull(result);
        assertEquals(clienteId, result.getId());
        assertEquals("John Doe", result.getNombre());

        // Verificar que se llamó al método correspondiente en el clienteDao
        verify(clienteDao, times(1)).findById(clienteId);
    }

    private Cliente getCliente(Long clienteId, String name, Genero genero, int edad, String identificacion, String direccion, String telefono) {
        Cliente cliente = new Cliente();

        cliente.setId(clienteId);
        cliente.setNombre(name);
        cliente.setGenero(genero);
        cliente.setEdad(edad);
        cliente.setIdentificacion(identificacion);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        return cliente;

    }


}