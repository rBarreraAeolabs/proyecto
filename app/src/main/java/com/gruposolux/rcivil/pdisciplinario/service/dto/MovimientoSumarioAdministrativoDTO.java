package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoSumarioAdministrativo;

/**
 * A DTO for the MovimientoSumarioAdministrativo entity.
 */
public class MovimientoSumarioAdministrativoDTO implements Serializable {

    private Long id;

    private EstadoSumarioAdministrativo estadoAnterior;

    private EstadoSumarioAdministrativo estadoNuevo;

    private Instant fecha;

    private Long sumarioAdministrativoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoSumarioAdministrativo getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoSumarioAdministrativo estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoSumarioAdministrativo getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoSumarioAdministrativo estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Long getSumarioAdministrativoId() {
        return sumarioAdministrativoId;
    }

    public void setSumarioAdministrativoId(Long sumarioAdministrativoId) {
        this.sumarioAdministrativoId = sumarioAdministrativoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO = (MovimientoSumarioAdministrativoDTO) o;
        if (movimientoSumarioAdministrativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoSumarioAdministrativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoSumarioAdministrativoDTO{" +
            "id=" + getId() +
            ", estadoAnterior='" + getEstadoAnterior() + "'" +
            ", estadoNuevo='" + getEstadoNuevo() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", sumarioAdministrativo=" + getSumarioAdministrativoId() +
            "}";
    }
}
