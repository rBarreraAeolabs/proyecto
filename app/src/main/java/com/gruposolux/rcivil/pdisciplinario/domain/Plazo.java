package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Plazo.
 */
@Entity
@Table(name = "plazo")
public class Plazo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dias")
    private Integer dias;

    @OneToMany(mappedBy = "plazo")
    private Set<MovimientoProvidencia> movimientosProvidencis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Plazo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDias() {
        return dias;
    }

    public Plazo dias(Integer dias) {
        this.dias = dias;
        return this;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Set<MovimientoProvidencia> getMovimientosProvidencis() {
        return movimientosProvidencis;
    }

    public Plazo movimientosProvidencis(Set<MovimientoProvidencia> movimientoProvidencias) {
        this.movimientosProvidencis = movimientoProvidencias;
        return this;
    }

    public Plazo addMovimientosProvidenci(MovimientoProvidencia movimientoProvidencia) {
        this.movimientosProvidencis.add(movimientoProvidencia);
        movimientoProvidencia.setPlazo(this);
        return this;
    }

    public Plazo removeMovimientosProvidenci(MovimientoProvidencia movimientoProvidencia) {
        this.movimientosProvidencis.remove(movimientoProvidencia);
        movimientoProvidencia.setPlazo(null);
        return this;
    }

    public void setMovimientosProvidencis(Set<MovimientoProvidencia> movimientoProvidencias) {
        this.movimientosProvidencis = movimientoProvidencias;
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
        Plazo plazo = (Plazo) o;
        if (plazo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plazo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plazo{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", dias=" + getDias() +
            "}";
    }
}
