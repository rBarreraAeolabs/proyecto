package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.io.Serializable;

public class ProvidenciaDerivacionDTO implements Serializable {

    private ProvidenciaDTO providenciaDTO;

    private DerivacionDTO derivacionDTO;

    public ProvidenciaDerivacionDTO() {
    }

    public ProvidenciaDTO getProvidenciaDTO() {
        return providenciaDTO;
    }

    public void setProvidenciaDTO(ProvidenciaDTO providenciaDTO) {
        this.providenciaDTO = providenciaDTO;
    }

    public DerivacionDTO getDerivacionDTO() {
        return derivacionDTO;
    }

    public void setDerivacionDTO(DerivacionDTO derivacionDTO) {
        this.derivacionDTO = derivacionDTO;
    }

    @Override
    public String toString() {
        return "ProvidenciaDerivacionDTO{" +
            "providenciaDTO=" + providenciaDTO +
            ", derivacionDTO=" + derivacionDTO +
            '}';
    }
}
