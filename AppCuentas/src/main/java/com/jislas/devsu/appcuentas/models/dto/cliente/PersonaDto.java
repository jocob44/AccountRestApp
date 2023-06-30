package com.jislas.devsu.appcuentas.models.dto.cliente;

import com.jislas.devsu.appcuentas.models.entity.Genero;
import lombok.Data;

@Data
public class PersonaDto {

    private Long id;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}