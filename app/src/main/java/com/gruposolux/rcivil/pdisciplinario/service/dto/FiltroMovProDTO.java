package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 03-05-2019.
 */
public class FiltroMovProDTO
{
    private ProvidenciaDTO providencia;
    private FiltroMovimientoProvidenciaDTO filtroMovimientoProvidencia;

    public FiltroMovProDTO() {
    }

    public ProvidenciaDTO getProvidencia() {
        return providencia;
    }

    public void setProvidencia(ProvidenciaDTO providencia) {
        this.providencia = providencia;
    }

    public FiltroMovimientoProvidenciaDTO getFiltroMovimientoProvidencia() {
        return filtroMovimientoProvidencia;
    }

    public void setFiltroMovimientoProvidencia(FiltroMovimientoProvidenciaDTO filtroMovimientoProvidencia) {
        this.filtroMovimientoProvidencia = filtroMovimientoProvidencia;
    }

    @Override
    public String toString() {
        return "FiltroMovPro{" +
            "providencia=" + providencia +
            ", filtroMovimientoProvidencia=" + filtroMovimientoProvidencia +
            '}';
    }
}
