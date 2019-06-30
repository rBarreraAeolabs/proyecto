package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Grupo.
 */
@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> usuariosEnGrupo = new HashSet<>();

    @OneToMany(mappedBy = "derivadoAGrupo", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Derivacion> derivacion;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Grupo() {
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

    public Set<User> getUsuariosEnGrupo() {
        return usuariosEnGrupo;
    }

    public void setUsuariosEnGrupo(Set<User> usuariosEnGrupo) {
        this.usuariosEnGrupo = usuariosEnGrupo;
    }

    public Set<Derivacion> getDerivacion() {
        return derivacion;
    }

    public void setDerivacion(Set<Derivacion> derivacion) {
        this.derivacion = derivacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        return getId().equals(grupo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return "Grupo{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", usuariosEnGrupo=" + usuariosEnGrupo +
            '}';
    }
}
