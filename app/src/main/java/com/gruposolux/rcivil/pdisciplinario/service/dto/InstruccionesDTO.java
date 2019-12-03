package com.gruposolux.rcivil.pdisciplinario.service.dto;

public class InstruccionesDTO {

    private Long id;
    private String nombre;

    public InstruccionesDTO() {}

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
        return "InstruccionesDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            '}';
    }
}
