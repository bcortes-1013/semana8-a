package com.semana3.microservicio_veterinario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeterinarioNotFoundException extends RuntimeException {
    public VeterinarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}