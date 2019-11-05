package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SumarioAdministrativo.
 */
@Entity
@Table(name = "sumario_administrativo")
public class SumarioAdministrativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "campo_uno")
    private String campoUno;

    @OneToOne
    @JoinColumn(unique = true)
    private InvestigacionSumaria investigacionSumaria;

    @OneToMany(mappedBy = "sumarioAdministrativo")
    private Set<Providencia> providencias = new HashSet<>();

    @OneToMany(mappedBy = "sumarioAdministrativo")
    private Set<MovimientoSumarioAdministrativo> movimientosSumarioAdmins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampoUno() {
        return campoUno;
    }

    public SumarioAdministrativo campoUno(String campoUno) {
        this.campoUno = campoUno;
        return this;
    }

    public void setCampoUno(String campoUno) {
        this.campoUno = campoUno;
    }

    public InvestigacionSumaria getInvestigacionSumaria() {
        return investigacionSumaria;
    }

    public SumarioAdministrativo investigacionSumaria(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
        return this;
    }

    public void setInvestigacionSumaria(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
    }

    public Set<Providencia> getProvidencias() {
        return providencias;
    }

    public SumarioAdministrativo providencias(Set<Providencia> providencias) {
        this.providencias = providencias;
        return this;
    }

    public SumarioAdministrativo addProvidencias(Providencia providencia) {
        this.providencias.add(providencia);
        providencia.setSumarioAdministrativo(this);
        return this;
    }

    public SumarioAdministrativo removeProvidencias(Providencia providencia) {
        this.providencias.remove(providencia);
        providencia.setSumarioAdministrativo(null);
        return this;
    }

    public void setProvidencias(Set<Providencia> providencias) {
        this.providencias = providencias;
    }

    public Set<MovimientoSumarioAdministrativo> getMovimientosSumarioAdmins() {
        return movimientosSumarioAdmins;
    }

    public SumarioAdministrativo movimientosSumarioAdmins(Set<MovimientoSumarioAdministrativo> movimientoSumarioAdministrativos) {
        this.movimientosSumarioAdmins = movimientoSumarioAdministrativos;
        return this;
    }

    public SumarioAdministrativo addMovimientosSumarioAdmin(MovimientoSumarioAdministrativo movimientoSumarioAdministrativo) {
        this.movimientosSumarioAdmins.add(movimientoSumarioAdministrativo);
        movimientoSumarioAdministrativo.setSumarioAdministrativo(this);
        return this;
    }

    public SumarioAdministrativo removeMovimientosSumarioAdmin(MovimientoSumarioAdministrativo movimientoSumarioAdministrativo) {
        this.movimientosSumarioAdmins.remove(movimientoSumarioAdministrativo);
        movimientoSumarioAdministrativo.setSumarioAdministrativo(null);
        return this;
    }

    public void setMovimientosSumarioAdmins(Set<MovimientoSumarioAdministrativo> movimientoSumarioAdministrativos) {
        this.movimientosSumarioAdmins = movimientoSumarioAdministrativos;
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
        SumarioAdministrativo sumarioAdministrativo = (SumarioAdministrativo) o;
        if (sumarioAdministrativo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sumarioAdministrativo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SumarioAdministrativo{" +
            "id=" + getId() +
            ", campoUno='" + getCampoUno() + "'" +
            "}";
    }
}
