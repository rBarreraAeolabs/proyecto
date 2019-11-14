package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

public class MovimientoProvidenciaEvent {
    private EstadoProvidencia estadoProvidenciaActual;
    private EstadoProvidencia estadoProvidenciaAnterior;
    private Providencia providencia;

    public MovimientoProvidenciaEvent(EstadoProvidencia estadoProvidenciaActual, EstadoProvidencia estadoProvidenciaAnterior, Providencia providencia) {
        this.estadoProvidenciaActual = estadoProvidenciaActual;
        this.estadoProvidenciaAnterior = estadoProvidenciaAnterior;
        this.providencia = providencia;
    }

    public EstadoProvidencia getEstadoProvidenciaActual() {
        return estadoProvidenciaActual;
    }

    public void setEstadoProvidenciaActual(EstadoProvidencia estadoProvidenciaActual) {
        this.estadoProvidenciaActual = estadoProvidenciaActual;
    }

    public EstadoProvidencia getEstadoProvidenciaAnterior() {
        return estadoProvidenciaAnterior;
    }

    public void setEstadoProvidenciaAnterior(EstadoProvidencia estadoProvidenciaAnterior) {
        this.estadoProvidenciaAnterior = estadoProvidenciaAnterior;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }
}
