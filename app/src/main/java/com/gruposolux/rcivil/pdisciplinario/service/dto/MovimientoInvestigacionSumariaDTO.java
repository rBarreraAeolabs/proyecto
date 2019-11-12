package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoInvestigacionSumaria;

/**
 * A DTO for the MovimientoInvestigacionSumaria entity.
 */
public class MovimientoInvestigacionSumariaDTO implements Serializable {

    private Long id;

    private EstadoInvestigacionSumaria estadoAnterior;

    private EstadoInvestigacionSumaria estadoNuevo;

    private Instant fecha;

    private Long investigacionSumariaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoInvestigacionSumaria getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoInvestigacionSumaria estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoInvestigacionSumaria getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoInvestigacionSumaria estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
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

        MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO = (MovimientoInvestigacionSumariaDTO) o;
        if (movimientoInvestigacionSumariaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoInvestigacionSumariaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoInvestigacionSumariaDTO{" +
            "id=" + getId() +
            ", estadoAnterior='" + getEstadoAnterior() + "'" +
            ", estadoNuevo='" + getEstadoNuevo() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", investigacionSumaria=" + getInvestigacionSumariaId() +
            "}";
    }
}
