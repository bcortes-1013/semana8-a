package com.semana3.microservicio_veterinario.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.semana3.microservicio_veterinario.controller.VeterinarioController;
import com.semana3.microservicio_veterinario.model.Veterinario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VeterinarioModelAssembler implements RepresentationModelAssembler<Veterinario, EntityModel<Veterinario>> {

    @Override
    public EntityModel<Veterinario> toModel(Veterinario veterinario) {
        return EntityModel.of(
                veterinario, // Entidad original

                // Enlace al detalle de la película (GET /veterinarios/{id})
                linkTo(methodOn(VeterinarioController.class)
                        .obtenerVeterinarioPorId(veterinario.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /veterinarios/{id})
                linkTo(methodOn(VeterinarioController.class)
                        .eliminarVeterinario(veterinario.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /veterinarios/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(VeterinarioController.class)
                        .actualizarVeterinario(veterinario.getId(), null))
                        .withRel("update"),

                // Enlace para ver todas las películas (GET /veterinarios)
                linkTo(methodOn(VeterinarioController.class)
                        .obtenerVeterinarios())
                        .withRel("all"));
    }
}
