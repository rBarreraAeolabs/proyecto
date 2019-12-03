package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;

public class FiltroPerfilDTO implements Serializable
{
    private String nombre;

    public FiltroPerfilDTO()
    {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "FiltroPerfilDTO{" +
            "nombre='" + nombre + '\'' +
            '}';
    }
}
