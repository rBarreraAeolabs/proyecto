package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Perfil entity.
 */
public class PerfilDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private Set<Authority> authorities;

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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PerfilDTO perfilDTO = (PerfilDTO) o;
        if (perfilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), perfilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PerfilDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", authorities=" + authorities +
            '}';
    }
}
