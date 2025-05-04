package com.semana3.microservicio_veterinario;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Sort;

import com.semana3.microservicio_veterinario.model.Veterinario;
import com.semana3.microservicio_veterinario.repository.VeterinarioRepository;
import com.semana3.microservicio_veterinario.service.VeterinarioService;

public class VeterinarioServiceTest {

    private VeterinarioRepository veterinarioRepository;
    private VeterinarioService veterinarioService;

    @BeforeEach
    public void setUp() {
        veterinarioRepository = mock(VeterinarioRepository.class);
        veterinarioService = new VeterinarioService(veterinarioRepository);
    }

    @Test
    public void testObtenerTodas() {
        Veterinario p1 = new Veterinario(1L, "Andrea Gomez", "Juan Pérez", "Firulais", "cirugia", "FAC-001", 620000);
        Veterinario p2 = new Veterinario(2L, "Silvia Vargas", "María López", "Luna", "odontologia", "FAC-002", 120000);

        when(veterinarioRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2));

        List<Veterinario> resultado = veterinarioService.obtenerVeterinarios();

        assertEquals(2, resultado.size());
        assertEquals("Firulais", resultado.get(0).getMascota());
    }

    @Test
    public void testObtenerPorId_existente() {
        Veterinario veterinario = new Veterinario(3L, "Andrea Gomez", "Carlos Fernández", "Max", "cirugia", "FAC-003", 900000);

        when(veterinarioRepository.findById(3L)).thenReturn(Optional.of(veterinario));

        Veterinario resultado = veterinarioService.obtenerVeterinarioPorId(3L);

        assertEquals("Andrea Gomez", resultado.getEspecialista());
        assertEquals(900000, resultado.getCosto());
    }

    @Test
    public void testGuardarVeterinario() {
        Veterinario veterinario = new Veterinario(4L, "Esteban Zamora", "Ana González", "Rocky", "dermatologia", "FAC-004", 70000);

        when(veterinarioRepository.existsById(4L)).thenReturn(false);

        when(veterinarioRepository.save(veterinario)).thenReturn(veterinario);

        Veterinario resultado = veterinarioService.guardar(veterinario);

        assertNotNull(resultado);
        assertEquals("Ana González", resultado.getCliente());
        verify(veterinarioRepository, times(1)).save(veterinario);
    }

    @Test
    public void testGuardarVeterinarioConIdExistente() {
        Veterinario veterinario = new Veterinario(5L, "Silvia Vargas", "Roberto Díaz", "Nina", "odontologia", "FAC-005", 85000);

        when(veterinarioRepository.existsById(5L)).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            veterinarioService.guardar(veterinario);
        });

        assertEquals("Ya existe un veterinario con el ID 5", thrown.getMessage());
    }

    @Test
    public void testActualizarVeterinario() {
        Veterinario original = new Veterinario(6L, "Pedro Pardo", "Laura Medina", "Toby", "cardiologia", "FAC-006", 440000);
        Veterinario actualizada = new Veterinario(7L, "Esteban Zamora", "Miguel Herrera", "Bella", "dermatologia", "FAC-007", 50000);

        when(veterinarioRepository.findById(1L)).thenReturn(Optional.of(original));
        when(veterinarioRepository.save(any(Veterinario.class))).thenReturn(actualizada);

        Veterinario resultado = veterinarioService.actualizar(1L, actualizada);

        assertEquals("Miguel Herrera", resultado.getCliente());
        assertEquals("Bella", resultado.getMascota());
        assertEquals("Esteban Zamora", resultado.getEspecialista());
    }

    @Test
    public void testEliminarVeterinario() {
        Veterinario veterinario = new Veterinario(8L, "Andrea Gomez", "Carolina Suárez", "Simba", "cirugia", "FAC-008", 750000);
    
        when(veterinarioRepository.findById(8L)).thenReturn(Optional.of(veterinario));
    
        veterinarioService.eliminar(8L);
    
        verify(veterinarioRepository, times(1)).deleteById(8L);
    }
}
