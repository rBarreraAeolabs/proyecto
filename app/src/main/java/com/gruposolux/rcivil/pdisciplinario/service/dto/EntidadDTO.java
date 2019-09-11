package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
public class EntidadDTO {
    private Long id;
    private String nombre;

    public EntidadDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EntidadDTO{" +
            "id=" + id +
            ", nombre=" + nombre +
            '}';
    }
}
