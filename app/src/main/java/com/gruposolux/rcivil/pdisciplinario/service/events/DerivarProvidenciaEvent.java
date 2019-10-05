package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;

import java.util.Set;

public class DerivarProvidenciaEvent {

    private Providencia providencia;
    private Derivacion derivacion;
    private Set<Documento> documentos;
    private String observacion;

    public DerivarProvidenciaEvent(Providencia providencia, Derivacion derivacion, Set<Documento> documentos, String observacion) {
        this.providencia = providencia;
        this.derivacion = derivacion;
        this.documentos = documentos;
        this.observacion = observacion;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public Derivacion getDerivacion(){
        return derivacion;
    }

    public Set<Documento> getAdjuntos(){
        return documentos;
    }

    public String getObservacion(){
        return observacion;
    }
}
