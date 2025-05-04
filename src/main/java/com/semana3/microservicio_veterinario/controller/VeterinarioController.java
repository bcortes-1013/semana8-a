package com.semana3.microservicio_veterinario.controller;

import com.semana3.microservicio_veterinario.hateoas.VeterinarioModelAssembler;
import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.service.VeterinarioService;
import com.semana3.microservicio_veterinario.model.ResponseWrapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;
    private final VeterinarioModelAssembler assembler;

    public VeterinarioController(VeterinarioService veterinarioService, VeterinarioModelAssembler assembler){
        this.veterinarioService = veterinarioService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Veterinario>>> obtenerVeterinarios() {
        log.info("GET /veterinarios - Obteniendo todos los veterinarios");

        List<Veterinario> veterinarios = veterinarioService.obtenerVeterinarios();

        if (veterinarios.isEmpty()) {
            log.warn("No hay películas registradas actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Veterinario>> modelos = veterinarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(VeterinarioController.class).obtenerVeterinarios()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Veterinario>> obtenerVeterinarioPorId(@PathVariable Long id) {
        log.info("GET /veterinarios/{} - Buscando veterinario por ID", id);

        Veterinario veterinario = veterinarioService.obtenerVeterinarioPorId(id);

        return ResponseEntity.ok(assembler.toModel(veterinario));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Veterinario>> crearVeterinario(@Valid @RequestBody Veterinario veterinario) {
        log.info("POST /veterinarios - Creando veterinario: {}", veterinario.getId());

        Veterinario creado = veterinarioService.guardar(veterinario);

        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Veterinario>> actualizarVeterinario(@PathVariable Long id, @Valid @RequestBody Veterinario veterinarioActualizado) {
        log.info("PUT /veterinarios/{} - Actualizando veterinario", id);
        
        Veterinario actualizada = veterinarioService.actualizar(id, veterinarioActualizado);

        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarVeterinario(@PathVariable Long id) {
        log.warn("DELETE /veterinarios/{} - Eliminando veterinario", id);

        veterinarioService.eliminar(id);

        return ResponseEntity.ok(new ResponseWrapper<>("Veterinario eliminado con éxito", 0, null));
    }
}
