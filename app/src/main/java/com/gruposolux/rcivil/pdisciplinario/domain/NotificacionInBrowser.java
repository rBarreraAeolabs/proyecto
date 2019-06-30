package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A NotificacionInBrowser.
 */
@Entity
@Table(name = "notificacion_in_browser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificacionInBrowser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fecha_creado", nullable = false)
    private Instant fechaCreado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "visto")
    private Boolean visto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaCreado() {
        return fechaCreado;
    }

    public NotificacionInBrowser fechaCreado(Instant fechaCreado) {
        this.fechaCreado = fechaCreado;
        return this;
    }

    public void setFechaCreado(Instant fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NotificacionInBrowser descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isVisto() {
        return visto;
    }

    public NotificacionInBrowser visto(Boolean visto) {
        this.visto = visto;
        return this;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
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
        NotificacionInBrowser notificacionInBrowser = (NotificacionInBrowser) o;
        if (notificacionInBrowser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificacionInBrowser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificacionInBrowser{" +
            "id=" + getId() +
            ", fechaCreado='" + getFechaCreado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", visto='" + isVisto() + "'" +
            "}";
    }
}
