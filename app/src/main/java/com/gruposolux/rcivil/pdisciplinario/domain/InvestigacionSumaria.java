package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A InvestigacionSumaria.
 */
@Entity
@Table(name = "investigacion_sumaria")
public class InvestigacionSumaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "compo_tres")
    private String compoTres;

    @OneToMany(mappedBy = "investigacionSumaria")
    private Set<Providencia> providencias = new HashSet<>();

    @OneToMany(mappedBy = "investigacionSumaria")
    private Set<MovimientoInvestigacionSumaria> movimientoInvestSumarias = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompoTres() {
        return compoTres;
    }

    public InvestigacionSumaria compoTres(String compoTres) {
        this.compoTres = compoTres;
        return this;
    }

    public void setCompoTres(String compoTres) {
        this.compoTres = compoTres;
    }

    public Set<Providencia> getProvidencias() {
        return providencias;
    }

    public InvestigacionSumaria providencias(Set<Providencia> providencias) {
        this.providencias = providencias;
        return this;
    }

    public InvestigacionSumaria addProvidencias(Providencia providencia) {
        this.providencias.add(providencia);
        providencia.setInvestigacionSumaria(this);
        return this;
    }

    public InvestigacionSumaria removeProvidencias(Providencia providencia) {
        this.providencias.remove(providencia);
        providencia.setInvestigacionSumaria(null);
        return this;
    }

    public void setProvidencias(Set<Providencia> providencias) {
        this.providencias = providencias;
    }

    public Set<MovimientoInvestigacionSumaria> getMovimientoInvestSumarias() {
        return movimientoInvestSumarias;
    }

    public InvestigacionSumaria movimientoInvestSumarias(Set<MovimientoInvestigacionSumaria> movimientoInvestigacionSumarias) {
        this.movimientoInvestSumarias = movimientoInvestigacionSumarias;
        return this;
    }

    public InvestigacionSumaria addMovimientoInvestSumaria(MovimientoInvestigacionSumaria movimientoInvestigacionSumaria) {
        this.movimientoInvestSumarias.add(movimientoInvestigacionSumaria);
        movimientoInvestigacionSumaria.setInvestigacionSumaria(this);
        return this;
    }

    public InvestigacionSumaria removeMovimientoInvestSumaria(MovimientoInvestigacionSumaria movimientoInvestigacionSumaria) {
        this.movimientoInvestSumarias.remove(movimientoInvestigacionSumaria);
        movimientoInvestigacionSumaria.setInvestigacionSumaria(null);
        return this;
    }

    public void setMovimientoInvestSumarias(Set<MovimientoInvestigacionSumaria> movimientoInvestigacionSumarias) {
        this.movimientoInvestSumarias = movimientoInvestigacionSumarias;
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
        InvestigacionSumaria investigacionSumaria = (InvestigacionSumaria) o;
        if (investigacionSumaria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investigacionSumaria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestigacionSumaria{" +
            "id=" + getId() +
            ", compoTres='" + getCompoTres() + "'" +
            "}";
    }
}
