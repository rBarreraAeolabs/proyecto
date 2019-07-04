package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Accion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

/**
 * A MovimientoProvidencia.
 */
@Entity
@Table(name = "movimiento_providencia")
public class MovimientoProvidencia implements Serializable, Comparable<MovimientoProvidencia> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior")
    private EstadoProvidencia estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo")
    private EstadoProvidencia estadoNuevo;

    @Column(name = "fecha")
    private Instant fecha;

    @Column(name = "accion")
    private String accion;

    @OneToMany(mappedBy = "movimientoProvidencia")
    private Set<Documento> documentos = new HashSet<>();

    @OneToMany(mappedBy = "movimientoProvidencia")
    @JsonIgnore
    private Set<Adjunto> adjuntos = new HashSet<>();

    @ManyToOne
    private Providencia providencia;

    @ManyToOne
    @JsonIgnoreProperties("movimientosProvidencis")
    private Plazo plazo;

    @Column(name = "comentario")
    private String comentario;

    //User assigned
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public MovimientoProvidencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoProvidencia getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoProvidencia estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoProvidencia getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoProvidencia estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        MovimientoProvidencia movimientoProvidencia = (MovimientoProvidencia) o;
        if (movimientoProvidencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoProvidencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoProvidencia{" +
            "id=" + id +
            ", estadoAnterior=" + estadoAnterior +
            ", estadoNuevo=" + estadoNuevo +
            ", fecha=" + fecha +
            ", accion=" + accion +
            ", plazo=" + plazo +
            ", comentario='" + comentario + '\'' +
            ", user=" + user +
            '}';
    }

    @Override
    public int compareTo(@NotNull MovimientoProvidencia movimientoProvidencia)
    {
        if (this.getId() > movimientoProvidencia.getId())
        {
            return 1;
        }
        else if (this.getId() < movimientoProvidencia.getId())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}