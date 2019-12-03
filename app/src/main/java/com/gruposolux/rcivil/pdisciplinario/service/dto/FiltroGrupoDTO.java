package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;

public class FiltroGrupoDTO implements Serializable
{
    private String nombre;

    public FiltroGrupoDTO()
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
        return "FiltroGrupoDTO{" +
            "nombre='" + nombre + '\'' +
            '}';
    }
}
