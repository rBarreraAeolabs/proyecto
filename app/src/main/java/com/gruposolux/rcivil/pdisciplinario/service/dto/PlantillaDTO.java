package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Accion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;

/**
 * A DTO for the Plantilla entity.
 */
public class PlantillaDTO implements Serializable {

    private Long id;

    private String nombre;

    private String contenido;

    private TipoPlantilla tipo;

    private EstadoProvidencia estado;

    private Collection<Accion> acciones;

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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public TipoPlantilla getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlantilla tipo) {
        this.tipo = tipo;
    }

    public EstadoProvidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoProvidencia estado) {
        this.estado = estado;
    }

    public Collection<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(Collection<Accion> acciones) {
        this.acciones = acciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlantillaDTO plantillaDTO = (PlantillaDTO) o;
        if (plantillaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantillaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantillaDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", contenido='" + contenido + '\'' +
            ", tipo=" + tipo +
            ", estado=" + estado +
            ", acciones=" + acciones +
            '}';
    }
}
