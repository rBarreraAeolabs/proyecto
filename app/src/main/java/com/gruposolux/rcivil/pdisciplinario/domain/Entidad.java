package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
@Entity
@Table(name = "entidad")
public class Entidad implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    public Entidad() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entidad entidad = (Entidad) o;

        return getId() != null ? getId().equals(entidad.getId()) : entidad.getId() == null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return "Entidad{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            '}';
    }
}
