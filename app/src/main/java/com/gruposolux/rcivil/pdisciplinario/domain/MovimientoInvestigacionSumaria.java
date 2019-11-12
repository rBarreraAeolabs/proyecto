package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoInvestigacionSumaria;

/**
 * A MovimientoInvestigacionSumaria.
 */
@Entity
@Table(name = "mov_invest_sum")
public class MovimientoInvestigacionSumaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior")
    private EstadoInvestigacionSumaria estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo")
    private EstadoInvestigacionSumaria estadoNuevo;

    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne
    @JsonIgnoreProperties("movimientoInvestSumarias")
    private InvestigacionSumaria investigacionSumaria;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoInvestigacionSumaria getEstadoAnterior() {
        return estadoAnterior;
    }

    public MovimientoInvestigacionSumaria estadoAnterior(EstadoInvestigacionSumaria estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
        return this;
    }

    public void setEstadoAnterior(EstadoInvestigacionSumaria estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoInvestigacionSumaria getEstadoNuevo() {
        return estadoNuevo;
    }

    public MovimientoInvestigacionSumaria estadoNuevo(EstadoInvestigacionSumaria estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
        return this;
    }

    public void setEstadoNuevo(EstadoInvestigacionSumaria estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public MovimientoInvestigacionSumaria fecha(Instant fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public InvestigacionSumaria getInvestigacionSumaria() {
        return investigacionSumaria;
    }

    public MovimientoInvestigacionSumaria investigacionSumaria(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
        return this;
    }

    public void setInvestigacionSumaria(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
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
        MovimientoInvestigacionSumaria movimientoInvestigacionSumaria = (MovimientoInvestigacionSumaria) o;
        if (movimientoInvestigacionSumaria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoInvestigacionSumaria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoInvestigacionSumaria{" +
            "id=" + getId() +
            ", estadoAnterior='" + getEstadoAnterior() + "'" +
            ", estadoNuevo='" + getEstadoNuevo() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
