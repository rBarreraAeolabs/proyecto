package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoSumarioAdministrativo;

/**
 * A MovimientoSumarioAdministrativo.
 */
@Entity
@Table(name = "mov_sumario_admin")
public class MovimientoSumarioAdministrativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior")
    private EstadoSumarioAdministrativo estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo")
    private EstadoSumarioAdministrativo estadoNuevo;

    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne
    @JsonIgnoreProperties("movimientosSumarioAdmins")
    private SumarioAdministrativo sumarioAdministrativo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoSumarioAdministrativo getEstadoAnterior() {
        return estadoAnterior;
    }

    public MovimientoSumarioAdministrativo estadoAnterior(EstadoSumarioAdministrativo estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
        return this;
    }

    public void setEstadoAnterior(EstadoSumarioAdministrativo estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoSumarioAdministrativo getEstadoNuevo() {
        return estadoNuevo;
    }

    public MovimientoSumarioAdministrativo estadoNuevo(EstadoSumarioAdministrativo estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
        return this;
    }

    public void setEstadoNuevo(EstadoSumarioAdministrativo estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public MovimientoSumarioAdministrativo fecha(Instant fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public SumarioAdministrativo getSumarioAdministrativo() {
        return sumarioAdministrativo;
    }

    public MovimientoSumarioAdministrativo sumarioAdministrativo(SumarioAdministrativo sumarioAdministrativo) {
        this.sumarioAdministrativo = sumarioAdministrativo;
        return this;
    }

    public void setSumarioAdministrativo(SumarioAdministrativo sumarioAdministrativo) {
        this.sumarioAdministrativo = sumarioAdministrativo;
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
        MovimientoSumarioAdministrativo movimientoSumarioAdministrativo = (MovimientoSumarioAdministrativo) o;
        if (movimientoSumarioAdministrativo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoSumarioAdministrativo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoSumarioAdministrativo{" +
            "id=" + getId() +
            ", estadoAnterior='" + getEstadoAnterior() + "'" +
            ", estadoNuevo='" + getEstadoNuevo() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
