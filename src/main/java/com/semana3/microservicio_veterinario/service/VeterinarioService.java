package com.semana3.microservicio_veterinario.service;

import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.exception.NoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VeterinarioService {
    private final List<Veterinario> veterinarios = new ArrayList<>();

    public VeterinarioService(){
        veterinarios.add(new Veterinario(1L, LocalDate.of(2024, 1, 10), "Juan Pérez", "Firulais", "Andrea Gomez", "cirugia", "FAC-001", 620000));
        veterinarios.add(new Veterinario(2L, LocalDate.of(2025, 2, 12), "María López", "Luna", "Silvia Vargas", "odontologia", "FAC-002", 120000));
        veterinarios.add(new Veterinario(3L, LocalDate.of(2024, 3, 15), "Carlos Fernández", "Max", "Andrea Gomez", "cirugia", "FAC-003", 900000));
        veterinarios.add(new Veterinario(4L, LocalDate.of(2025, 4, 17), "Ana González", "Rocky", "Esteban Zamora", "dermatologia", "FAC-004", 70000));
        veterinarios.add(new Veterinario(5L, LocalDate.of(2024, 5, 20), "Roberto Díaz", "Nina", "Silvia Vargas", "odontologia", "FAC-005", 85000));
        veterinarios.add(new Veterinario(6L, LocalDate.of(2025, 6, 22), "Laura Medina", "Toby", "Pedro Pardo", "cardiologia", "FAC-006", 440000));
        veterinarios.add(new Veterinario(7L, LocalDate.of(2024, 7, 25), "Miguel Herrera", "Bella", "Esteban Zamora", "dermatologia", "FAC-007", 50000));
        veterinarios.add(new Veterinario(8L, LocalDate.of(2025, 8, 27), "Carolina Suárez", "Simba", "Andrea Gomez", "cirugia", "FAC-008", 750000));
    }

    public List<Veterinario> obtenerVeterinarios(){
        return veterinarios;
    }

    public Veterinario obtenerVeterinarioPorId(Long id){
        return veterinarios.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NoEncontradoException("No se encontró el ID Veterinaio " + id));
    }

    public Set<String> obtenerServicios(){
        Set<String> serviciosUnicos = new HashSet<>();
        
        for (Veterinario v : veterinarios) {
            serviciosUnicos.add(v.getServicio());
        }
        
        return serviciosUnicos;
    }

    public List<Veterinario> obtenerVeterinarioPorServicio(String servicio){
        List<Veterinario> resultado = veterinarios.stream()
            .filter(p -> p.getServicio().equalsIgnoreCase(servicio))
            .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new NoEncontradoException("No se encontró el servicio " + servicio);
        }

        return resultado;
    }
}
