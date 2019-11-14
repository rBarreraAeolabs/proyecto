package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoDerivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoDerivacion;

/**
 * A DTO for the Derivacion entity.
 */
public class DerivacionDTO implements Serializable {

    private Long id;

    private String observacion;

    private Instant fechaDerivacion;

    private EstadoDerivacion estado;

    private TipoDerivacion tipo;

    private Long providenciaId;

    private Set<DocumentoDTO> documentosDtos = new HashSet<>();

    private Set<AdjuntoDTO> adjuntosDtos = new HashSet<>();

    private Long derivadoAUsuarioId;

    private Long derivadoPorUsuarioId;

    private Long derivadoAGrupoId;

    private Long derivadoPorGrupoId;

    public DerivacionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Instant getFechaDerivacion() {
        return fechaDerivacion;
    }

    public void setFechaDerivacion(Instant fechaDerivacion) {
        this.fechaDerivacion = fechaDerivacion;
    }

    public EstadoDerivacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoDerivacion estado) {
        this.estado = estado;
    }

    public TipoDerivacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDerivacion tipo) {
        this.tipo = tipo;
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Set<DocumentoDTO> getDocumentosDtos() {
        return documentosDtos;
    }

    public void setDocumentosDtos(Set<DocumentoDTO> documentosDtos) {
        this.documentosDtos = documentosDtos;
    }

    public Long getDerivadoAUsuarioId() {
        return derivadoAUsuarioId;
    }

    public void setDerivadoAUsuarioId(Long derivadoAUsuarioId) {
        this.derivadoAUsuarioId = derivadoAUsuarioId;
    }

    public Long getDerivadoPorUsuarioId() {
        return derivadoPorUsuarioId;
    }

    public void setDerivadoPorUsuarioId(Long derivadoPorUsuarioId) {
        this.derivadoPorUsuarioId = derivadoPorUsuarioId;
    }


    public Long getDerivadoAGrupoId() {
        return derivadoAGrupoId;
    }

    public void setDerivadoAGrupoId(Long derivadoAGrupoId) {
        this.derivadoAGrupoId = derivadoAGrupoId;
    }

    public Long getDerivadoPorGrupoId() {
        return derivadoPorGrupoId;
    }

    public void setDerivadoPorGrupoId(Long derivadoPorGrupoId) {
        this.derivadoPorGrupoId = derivadoPorGrupoId;
    }

    public Set<AdjuntoDTO> getAdjuntosDtos() {
        return adjuntosDtos;
    }

    public void setAdjuntosDtos(Set<AdjuntoDTO> adjuntosDtos) {
        this.adjuntosDtos = adjuntosDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DerivacionDTO derivacionDTO = (DerivacionDTO) o;
        if (derivacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), derivacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DerivacionDTO{" +
            "id=" + id +
            ", observacion='" + observacion + '\'' +
            ", fechaDerivacion=" + fechaDerivacion +
            ", estado=" + estado +
            ", tipo=" + tipo +
            ", providenciaId=" + providenciaId +
            ", documentosDtos=" + documentosDtos +
            ", derivadoAUsuarioId=" + derivadoAUsuarioId +
            ", derivadoPorUsuarioId=" + derivadoPorUsuarioId +
            ", derivadoAGrupoId=" + derivadoAGrupoId +
            ", derivadoPorGrupoId=" + derivadoPorGrupoId +
            '}';
    }
}
