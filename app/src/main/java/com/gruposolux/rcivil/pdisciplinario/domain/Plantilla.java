package com.gruposolux.rcivil.pdisciplinario.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;

/**
 * A Plantilla.
 */
@Entity
@Table(name = "plantilla")
public class Plantilla implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contenido")
    private String contenido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPlantilla tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProvidencia estado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Plantilla nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public Plantilla contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public TipoPlantilla getTipo() {
        return tipo;
    }

    public Plantilla tipo(TipoPlantilla tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoPlantilla tipo) {
        this.tipo = tipo;
    }

    public EstadoProvidencia getEstado() {
        return estado;
    }

    public Plantilla estado(EstadoProvidencia estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(EstadoProvidencia estado) {
        this.estado = estado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plantilla plantilla = (Plantilla) o;
        if (plantilla.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantilla.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plantilla{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
