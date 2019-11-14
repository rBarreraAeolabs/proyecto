package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import java.util.Set;

/**
 * Created by sneiraillanes on 04-04-2019.
 */
public class ProvidenciaResponseDTO
{
    private Long providenciaId;
    private String estadoActual;
    private EstadoProvidencia etapa;

    private EstadoProvidencia subEtapa;

    private EstadoProvidencia requisito;
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

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
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

    public EstadoProvidencia getEtapa() {
        return etapa;
    }

    public void setEtapa(EstadoProvidencia etapa) {
        this.etapa = etapa;
    }

    public EstadoProvidencia getSubEtapa() {
        return subEtapa;
    }

    public void setSubEtapa(EstadoProvidencia subEtapa) {
        this.subEtapa = subEtapa;
    }

    public EstadoProvidencia getRequisito() {
        return requisito;
    }

    public void setRequisito(EstadoProvidencia requisito) {
        this.requisito = requisito;
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
            ", estadoActual='" + estadoActual + '\'' +
            ", requisito='" + requisito + '\'' +
            ", subEtapa='" + subEtapa + '\'' +
            ", adjuntosDTOs=" + adjuntosDTOs +
            ", documentosDTOs=" + documentosDTOs +
            ", observacion='" + observacion + '\'' +
            '}';
    }
}
