package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Plazo entity.
 */
public class PlazoDTO implements Serializable {

    private Long id;

    private String nombre;

    private Integer dias;

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

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlazoDTO plazoDTO = (PlazoDTO) o;
        if (plazoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plazoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlazoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", dias=" + getDias() +
            "}";
    }
}
