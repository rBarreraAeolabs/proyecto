package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 10-05-2019.
 */
public class ProvidenciaUpdateMadreDTO
{
    private Long providenciaId;
    private Long providenciaMadreId;

    public ProvidenciaUpdateMadreDTO() {
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

    @Override
    public String toString() {
        return "ProvidenciaUpdateMadreDTO{" +
            "providenciaId=" + providenciaId +
            ", providenciaMadreId=" + providenciaMadreId +
            '}';
    }
}
