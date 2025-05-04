package com.semana3.microservicio_veterinario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.exception.VeterinarioNotFoundException;
import com.semana3.microservicio_veterinario.repository.VeterinarioRepository;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VeterinarioService {

    // public VeterinarioService(){
    //     veterinarios.add(new Veterinario(1L, LocalDate.of(2024, 1, 10), "Juan Pérez", "Firulais", "Andrea Gomez", "cirugia", "FAC-001", 620000));
    //     veterinarios.add(new Veterinario(2L, LocalDate.of(2025, 2, 12), "María López", "Luna", "Silvia Vargas", "odontologia", "FAC-002", 120000));
    //     veterinarios.add(new Veterinario(3L, LocalDate.of(2024, 3, 15), "Carlos Fernández", "Max", "Andrea Gomez", "cirugia", "FAC-003", 900000));
    //     veterinarios.add(new Veterinario(4L, LocalDate.of(2025, 4, 17), "Ana González", "Rocky", "Esteban Zamora", "dermatologia", "FAC-004", 70000));
    //     veterinarios.add(new Veterinario(5L, LocalDate.of(2024, 5, 20), "Roberto Díaz", "Nina", "Silvia Vargas", "odontologia", "FAC-005", 85000));
    //     veterinarios.add(new Veterinario(6L, LocalDate.of(2025, 6, 22), "Laura Medina", "Toby", "Pedro Pardo", "cardiologia", "FAC-006", 440000));
    //     veterinarios.add(new Veterinario(7L, LocalDate.of(2024, 7, 25), "Miguel Herrera", "Bella", "Esteban Zamora", "dermatologia", "FAC-007", 50000));
    //     veterinarios.add(new Veterinario(8L, LocalDate.of(2025, 8, 27), "Carolina Suárez", "Simba", "Andrea Gomez", "cirugia", "FAC-008", 750000));
    // }

    // public Set<String> obtenerServicios(){
    //     Set<String> serviciosUnicos = new HashSet<>();
        
    //     for (Veterinario v : veterinarios) {
    //         serviciosUnicos.add(v.getServicio());
    //     }
        
    //     return serviciosUnicos;
    // }


    @Autowired
    private VeterinarioRepository repository;

    public VeterinarioService(VeterinarioRepository repository) {
        this.repository = repository;
    }

    public List<Veterinario> obtenerVeterinarios(){
        log.debug("Servicio: obtenerVeterinarios()");

        return repository.findAll(Sort.by("id").ascending());
    } 

    public Veterinario obtenerVeterinarioPorId(Long id){
        log.debug("Servicio: obtenerVeterinarioPorId({})", id);

        return repository.findById(id).orElseThrow(() -> new VeterinarioNotFoundException("No se ha encontrado el ID " + id));
    }

    public Veterinario guardar(Veterinario veterinario){
        log.debug("Servicio: guardar({})", veterinario.getId());

        if(repository.existsById(veterinario.getId())){
            log.error("Ya existe un veterinario con ID {}", veterinario.getId());

            throw new IllegalArgumentException("Ya existe un veterinario con el ID " + veterinario.getId());
        }

        return repository.save(veterinario);

    }

    public Veterinario actualizar(long id, Veterinario veterinarioActualizado){
        log.debug("Servicio: actualizar({}, {})", id, veterinarioActualizado.getId());

        Veterinario existente = repository.findById(id).orElseThrow(() -> new VeterinarioNotFoundException("No se ha encontrado el veterinario ID " + id));

        existente.setEspecialista(veterinarioActualizado.getEspecialista());
        existente.setCliente(veterinarioActualizado.getCliente());
        existente.setMascota(veterinarioActualizado.getMascota());
        existente.setServicio(veterinarioActualizado.getServicio());
        existente.setNumeroFactura(veterinarioActualizado.getNumeroFactura());
        existente.setCosto(veterinarioActualizado.getCosto());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        log.debug("Servicio: eliminar({})", id);

        repository.findById(id).orElseThrow(() -> new VeterinarioNotFoundException("Se ha eliminado el veterinario ID " + id));

        repository.deleteById(id);
    }
}
