package com.semana3.microservicio_veterinario.controller;

import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.service.VeterinarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService){
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public List<Veterinario> obtenerVeterinarios() {
        return veterinarioService.obtenerVeterinarios();
    }

    @GetMapping("/{id}")
    public Veterinario obtenerVeterinarioPorId(@PathVariable("id") Long id) {
        return veterinarioService.obtenerVeterinarioPorId(id);
    }

    @GetMapping("/servicios")
    public Set<String> obtenerServicios() {
        return veterinarioService.obtenerServicios();
    }

    @GetMapping("/servicios/{servicio}")
    public List<Veterinario> obtenerVeterinarioPorServicio(@PathVariable("servicio") String servicio) {
        return veterinarioService.obtenerVeterinarioPorServicio(servicio);
    }
    
}
