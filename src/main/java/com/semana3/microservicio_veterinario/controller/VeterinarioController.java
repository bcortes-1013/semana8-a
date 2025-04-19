package com.semana3.microservicio_veterinario.controller;

import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.service.VeterinarioService;
import com.semana3.microservicio_veterinario.model.ResponseWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService){
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.obtenerVeterinarios();

        if (veterinarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay veterinarios registrados");
        }

        return ResponseEntity.ok(new ResponseWrapper<>("OK", veterinarios.size(), veterinarios));
    }

    @GetMapping("/{id}")
    public Veterinario obtenerVeterinariosPorId(@PathVariable("id") Long id) {
        return veterinarioService.obtenerVeterinarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Veterinario>> crearVeterinario(@Valid @RequestBody Veterinario veterinario) {

        Veterinario creado = veterinarioService.guardarVeterinario(veterinario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("Veterinario creado exitosamente", 1, List.of(creado)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Veterinario>> actualizarVeterinario(@PathVariable Long id, @Valid @RequestBody Veterinario veterinarioActualizado) {
        
        Veterinario actualizado = veterinarioService.actualizarVeterinario(id, veterinarioActualizado);
        return ResponseEntity.ok(new ResponseWrapper<>("Veterinario actualizado exitosamente", 1, List.of(actualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Veterinario eliminado exitosamente", 0, null));
    }
    
}
