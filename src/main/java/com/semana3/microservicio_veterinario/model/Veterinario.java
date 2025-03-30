package com.semana3.microservicio_veterinario.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Veterinario{
    private Long id;
    private LocalDate fechaRegistro;
    private String cliente;
    private String nombreMascota;
    private String veterinario;
    private String servicio;
    private String numeroFactura;
    private Integer total;
}