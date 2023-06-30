package com.jislas.devsu.appcuentas.models.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}