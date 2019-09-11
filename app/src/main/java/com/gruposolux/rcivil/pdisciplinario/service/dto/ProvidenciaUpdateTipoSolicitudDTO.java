package com.gruposolux.rcivil.pdisciplinario.service.dto;

public class ProvidenciaUpdateTipoSolicitudDTO {
    private Long providenciaId;
    private String tipoSolicitud;

    public ProvidenciaUpdateTipoSolicitudDTO() {
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    @Override
    public String toString() {
        return "ProvidenciaUpdateTipoSolicitudDTO{" +
            "providenciaId=" + providenciaId +
            ", tipoSolicitud=" + tipoSolicitud +
            '}';
    }
}
