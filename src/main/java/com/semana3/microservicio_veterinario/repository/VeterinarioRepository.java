package com.semana3.microservicio_veterinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semana3.microservicio_veterinario.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    
}
