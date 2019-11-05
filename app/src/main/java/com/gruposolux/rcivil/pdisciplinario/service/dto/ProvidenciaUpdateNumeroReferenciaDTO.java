package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 14-05-2019.
 */
public class ProvidenciaUpdateNumeroReferenciaDTO
{
    private Long providenciaId;
    private Long numeroReferencia;

    public ProvidenciaUpdateNumeroReferenciaDTO() {
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    @Override
    public String toString() {
        return "ProvidenciaUpdateNumeroReferenciaDTO{" +
            "providenciaId=" + providenciaId +
            ", numeroReferencia=" + numeroReferencia +
            '}';
    }
}
