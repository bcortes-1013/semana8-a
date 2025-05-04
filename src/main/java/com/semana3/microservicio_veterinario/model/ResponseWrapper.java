package com.semana3.microservicio_veterinario.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

import java.util.List;

@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" })
public class ResponseWrapper<T> {

    private String status;
    private int cantidad;
    private LocalDateTime timestamp;
    private List<T> data;

    public ResponseWrapper(String status, int cantidad, List<T> data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}