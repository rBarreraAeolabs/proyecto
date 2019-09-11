package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by sneiraillanes on 22-04-2019.
 */
@Entity
@Table(name = "respuesta")
public class Respuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JsonIgnore
    private Providencia providencia;

    @OneToOne
    private MovimientoProvidencia movimientoProvidencia;

    @OneToMany(mappedBy = "respuesta", fetch = FetchType.EAGER)
    private Set<Adjunto> adjuntos;

    @OneToMany(mappedBy = "respuesta", fetch = FetchType.EAGER)
    private Set<Documento> documentos;

    @Column(name = "guardada")
    private Boolean guardada;

    @ManyToOne
    private User user;

    @Column(name = "estado_providencia")
    private String estadoProvidencia;

    public Respuesta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }

    public MovimientoProvidencia getMovimientoProvidencia() {
        return movimientoProvidencia;
    }

    public void setMovimientoProvidencia(MovimientoProvidencia movimientoProvidencia) {
        this.movimientoProvidencia = movimientoProvidencia;
    }

    public Set<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Boolean getGuardada() {
        return guardada;
    }

    public void setGuardada(Boolean guardada) {
        this.guardada = guardada;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEstadoProvidencia() {
        return estadoProvidencia;
    }

    public void setEstadoProvidencia(String estadoProvidencia) {
        this.estadoProvidencia = estadoProvidencia;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
            "id=" + id +
            ", comentario='" + comentario + '\'' +
            ", providencia=" + providencia +
            ", movimientoProvidencia=" + movimientoProvidencia +
            ", adjuntos=" + adjuntos +
            ", documentos=" + documentos +
            ", guardada=" + guardada +
            ", user=" + user +
            ", estadoProvidencia=" + estadoProvidencia +
            '}';
    }
}
