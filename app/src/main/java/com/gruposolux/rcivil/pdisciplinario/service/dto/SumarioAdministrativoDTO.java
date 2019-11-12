package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SumarioAdministrativo entity.
 */
public class SumarioAdministrativoDTO implements Serializable {

    private Long id;

    private String campoUno;

    private Long investigacionSumariaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampoUno() {
        return campoUno;
    }

    public void setCampoUno(String campoUno) {
        this.campoUno = campoUno;
    }

    public Long getInvestigacionSumariaId() {
        return investigacionSumariaId;
    }

    public void setInvestigacionSumariaId(Long investigacionSumariaId) {
        this.investigacionSumariaId = investigacionSumariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SumarioAdministrativoDTO sumarioAdministrativoDTO = (SumarioAdministrativoDTO) o;
        if (sumarioAdministrativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sumarioAdministrativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SumarioAdministrativoDTO{" +
            "id=" + getId() +
            ", campoUno='" + getCampoUno() + "'" +
            ", investigacionSumaria=" + getInvestigacionSumariaId() +
            "}";
    }
}
