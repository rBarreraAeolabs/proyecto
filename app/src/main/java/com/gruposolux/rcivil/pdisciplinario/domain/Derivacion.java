package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoDerivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoDerivacion;

/**
 * A Derivacion.
 */
@Entity
@Table(name = "derivacion")
public class Derivacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "fecha_derivacion")
    private Instant fechaDerivacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoDerivacion estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoDerivacion tipo;

    @OneToMany(mappedBy = "derivacion")
    private Set<Documento> documentos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("derivaciones")
    private Providencia providencia;

    @ManyToOne
    @JoinColumn(name = "jhi_user_id")
    private User derivadoAUsuario;

    @ManyToOne
    private User derivadoPorUsuario;

    @ManyToOne(targetEntity = Grupo.class)
    private Grupo derivadoAGrupo;

    @ManyToOne(targetEntity = Grupo.class)
    private Grupo derivadoPorGrupo;

    @OneToMany(mappedBy = "derivacion")
    private Set<Adjunto> adjuntos;

    //todo: revisar si se necesita las copias de grupo en la derivacion
//    @ManyToOne
//    @JoinColumn(name = "grupo_id")
//    private Set<Grupo> gruposACopiar;

    public Derivacion() {
        fechaDerivacion = Instant.now();
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public Derivacion observacion(String observacion) {
        this.observacion = observacion;
        return this;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Instant getFechaDerivacion() {
        return fechaDerivacion;
    }

    public Derivacion fechaDerivacion(Instant fechaDerivacion) {
        this.fechaDerivacion = fechaDerivacion;
        return this;
    }

    public void setFechaDerivacion(Instant fechaDerivacion) {
        this.fechaDerivacion = fechaDerivacion;
    }

    public EstadoDerivacion getEstado() {
        return estado;
    }

    public Derivacion estado(EstadoDerivacion estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(EstadoDerivacion estado) {
        this.estado = estado;
    }

    public TipoDerivacion getTipo() {
        return tipo;
    }

    public Derivacion tipo(TipoDerivacion tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoDerivacion tipo) {
        this.tipo = tipo;
    }

    public Set<Documento> getHashesDerivacions() {
        return documentos;
    }

    public Derivacion hashesDerivacions(Set<Documento> documentos) {
        this.documentos = documentos;
        return this;
    }

    public Derivacion addHashesDerivacion(Documento documento) {
        this.documentos.add(documento);
        documento.setDerivacion(this);
        return this;
    }

    public Derivacion removeHashesDerivacion(Documento documento) {
        this.documentos.remove(documento);
        documento.setDerivacion(null);
        return this;
    }

    public void setHashesDerivacions(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public Derivacion providencia(Providencia providencia) {
        this.providencia = providencia;
        return this;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    public User getDerivadoPorUsuario() {
        return derivadoPorUsuario;
    }

    public void setDerivadoPorUsuario(User derivadoPorUsuario) {
        this.derivadoPorUsuario = derivadoPorUsuario;
    }

    public Grupo getDerivadoAGrupo() {
        return derivadoAGrupo;
    }

    public void setDerivadoAGrupo(Grupo derivadoAGrupo) {
        this.derivadoAGrupo = derivadoAGrupo;
    }

    public Grupo getDerivadoPorGrupo() {
        return derivadoPorGrupo;
    }

    public void setDerivadoPorGrupo(Grupo derivadoPorGrupo) {
        this.derivadoPorGrupo = derivadoPorGrupo;
    }

    public User getDerivadoAUsuario() {
        return derivadoAUsuario;
    }

    public void setDerivadoAUsuario(User derivadoAUsuario) {
        this.derivadoAUsuario = derivadoAUsuario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Derivacion derivacion = (Derivacion) o;
        if (derivacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), derivacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Derivacion{" +
            "id=" + id +
            ", observacion='" + observacion + '\'' +
            ", fechaDerivacion=" + fechaDerivacion +
            ", estado=" + estado +
            ", tipo=" + tipo +
            ", documentos=" + documentos +
            ", providencia=" + providencia +
            ", derivadoAUsuario=" + derivadoAUsuario +
            ", derivadoPorUsuario=" + derivadoPorUsuario +
            ", derivadoAGrupo=" + derivadoAGrupo +
            ", derivadoPorGrupo=" + derivadoPorGrupo +
            '}';
    }

}
