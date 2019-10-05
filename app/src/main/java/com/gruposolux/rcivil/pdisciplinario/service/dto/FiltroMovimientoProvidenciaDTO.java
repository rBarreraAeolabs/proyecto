package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoAdjunto;

/**
 * Created by sneiraillanes on 30-04-2019.
 */
public class FiltroMovimientoProvidenciaDTO
{
    private String accion;
    private EstadoProvidencia estadoProvidencia;
    private TipoAdjunto tipoAdjunto;

    public FiltroMovimientoProvidenciaDTO() {
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public EstadoProvidencia getEstadoProvidencia() {
        return estadoProvidencia;
    }

    public void setEstadoProvidencia(EstadoProvidencia estadoProvidencia) {
        this.estadoProvidencia = estadoProvidencia;
    }

    public TipoAdjunto getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(TipoAdjunto tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    @Override
    public String toString() {
        return "FiltroMovimientoProvidenciaDTO{" +
            "accion='" + accion + '\'' +
            ", estadoProvidencia=" + estadoProvidencia +
            ", tipoAdjunto=" + tipoAdjunto +
            '}';
    }
}
