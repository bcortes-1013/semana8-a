package com.semana3.microservicio_veterinario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="veterinario")

public class Veterinario{
    @Id
    @Min(value = 1, message = "El ID debe ser mayor a 0")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;

    @NotNull(message = "El nombre del especialista no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del especialista no puede ser mayor a 100 carácteres")
    private String especialista;

    @NotNull(message = "El nombre del cliente no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del cliente no puede ser mayor a 100 carácteres")
    private String cliente;

    @NotNull(message = "El nombre de la mascota no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre de la mascota no puede ser mayor a 100 carácteres")
    private String mascota;

    @NotNull(message = "El nombre del servicio no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del servicio no puede ser mayor 100 caracteres")
    private String servicio;

    @NotNull(message = "El total no puede estar vacío")
    @Min(value = 1, message = "El total debe ser mayor a 0")
    private Integer numeroFactura;

    @NotNull(message = "El total no puede estar vacío")
    @Min(value = 1, message = "El total debe ser mayor a 0")
    private Integer costo;
}