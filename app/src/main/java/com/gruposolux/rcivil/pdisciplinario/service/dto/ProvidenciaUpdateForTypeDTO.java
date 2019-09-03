package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 10-05-2019.
 */
public class ProvidenciaUpdateForTypeDTO {
    private Long providenciaId;
    private Long providenciaMadreId;
    private Long numeroReferencia;

    public ProvidenciaUpdateForTypeDTO() {
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Long getProvidenciaMadreId() {
        return providenciaMadreId;
    }

    public void setProvidenciaMadreId(Long providenciaMadreId) {
        this.providenciaMadreId = providenciaMadreId;
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setProvidenciaMadreNumeroReferencia(Long providenciaMadreNumeroReferencia) {
        this.numeroReferencia = providenciaMadreNumeroReferencia;
    }

    @Override
    public String toString() {
        return "ProvidenciaUpdateMadreDTO{" +
            "providenciaId=" + providenciaId +
            ", providenciaMadreId=" + providenciaMadreId +
            ", numeroReferencia=" + numeroReferencia +
            '}';
    }

}


