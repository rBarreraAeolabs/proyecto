package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.Perfil;
import com.gruposolux.rcivil.pdisciplinario.domain.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Grupo entity.
 */
public class GrupoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private Set<User> usuariosEnGrupo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrupoDTO grupoDTO = (GrupoDTO) o;
        if (grupoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grupoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrupoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
