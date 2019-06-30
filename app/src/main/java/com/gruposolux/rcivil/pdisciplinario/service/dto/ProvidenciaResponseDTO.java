package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import java.util.Set;

/**
 * Created by sneiraillanes on 04-04-2019.
 */
public class ProvidenciaResponseDTO
{
    private Long providenciaId;
    private EstadoProvidencia estadoActual;
    private Set<AdjuntoDTO> adjuntosDTOs;
    private Set<DocumentoDTO> documentosDTOs;
    private String observacion;

    public ProvidenciaResponseDTO() {}

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public EstadoProvidencia getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoProvidencia estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Set<AdjuntoDTO> getAdjuntosDTOs() {
        return adjuntosDTOs;
    }

    public void setAdjuntosDTOs(Set<AdjuntoDTO> adjuntosDTOs) {
        this.adjuntosDTOs = adjuntosDTOs;
    }

    public Set<DocumentoDTO> getDocumentosDTOs() {
        return documentosDTOs;
    }

    public void setDocumentosDTOs(Set<DocumentoDTO> documentosDTOs) {
        this.documentosDTOs = documentosDTOs;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "ProvidenciaResponseDTO{" +
            "providenciaId=" + providenciaId +
            ", estadoActual=" + estadoActual +
            ", adjuntosDTOs=" + adjuntosDTOs +
            ", documentosDTOs=" + documentosDTOs +
            ", observacion='" + observacion + '\'' +
            '}';
    }
}
