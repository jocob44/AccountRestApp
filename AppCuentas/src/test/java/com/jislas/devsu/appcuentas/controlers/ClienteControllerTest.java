package com.jislas.devsu.appcuentas.controlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jislas.devsu.appcuentas.models.dto.cliente.CreateClienteDto;
import com.jislas.devsu.appcuentas.models.entity.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class ClienteControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        CreateClienteDto createClienteDto = new CreateClienteDto();
        createClienteDto.setClienteId("1");
        createClienteDto.setContrasena("12345");
        createClienteDto.setNombre("John Doe");
        createClienteDto.setGenero(Genero.MASCULINO);
        createClienteDto.setEdad(30);
        createClienteDto.setIdentificacion("12345");
        createClienteDto.setDireccion("123 Main St");
        createClienteDto.setTelefono("123-456-7890");


        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClienteDto)))
                .andReturn();
    }

    @Test
    public void testCreateCliente() throws Exception {
        CreateClienteDto createClienteDto = new CreateClienteDto();
        createClienteDto.setClienteId("2");
        createClienteDto.setContrasena("12345");
        createClienteDto.setNombre("John Doe");
        createClienteDto.setGenero(Genero.MASCULINO);
        createClienteDto.setEdad(30);
        createClienteDto.setIdentificacion("12345");
        createClienteDto.setDireccion("123 Main St");
        createClienteDto.setTelefono("123-456-7890");


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClienteDto)))
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());

    }

    @Test
    public void testCreateClienteError() throws Exception {
        CreateClienteDto createClienteDto = new CreateClienteDto();
        createClienteDto.setClienteId(null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClienteDto)))
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());

    }

    @Test
    void getAllClientes() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void getClienteById() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void createCliente() {
        //Todo
    }

    @Test
    void updateCliente() {
        //Todo
    }

    @Test
    void deleteCliente() {
        //Todo
    }

    @Test
    void partialUpdateCliente() {
        //Todo
    }
}