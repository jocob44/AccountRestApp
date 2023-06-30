package com.jislas.devsu.appcuentas.models.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @NotNull
    @Min(18)
    @Max(100)
    private int edad;
    @NotEmpty
    private String identificacion;
    private String direccion;
    private String telefono;
}