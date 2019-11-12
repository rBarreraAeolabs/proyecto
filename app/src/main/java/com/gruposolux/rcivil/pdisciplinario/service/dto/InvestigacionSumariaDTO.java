package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the InvestigacionSumaria entity.
 */
public class InvestigacionSumariaDTO implements Serializable {

    private Long id;

    private String compoTres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompoTres() {
        return compoTres;
    }

    public void setCompoTres(String compoTres) {
        this.compoTres = compoTres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestigacionSumariaDTO investigacionSumariaDTO = (InvestigacionSumariaDTO) o;
        if (investigacionSumariaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investigacionSumariaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestigacionSumariaDTO{" +
            "id=" + getId() +
            ", compoTres='" + getCompoTres() + "'" +
            "}";
    }
}
