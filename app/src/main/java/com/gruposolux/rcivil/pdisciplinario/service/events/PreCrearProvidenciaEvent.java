package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;

public class PreCrearProvidenciaEvent
{
    private ProvidenciaDTO providenciaDTO;

    public PreCrearProvidenciaEvent(ProvidenciaDTO providenciaDTO)
    {
        this.providenciaDTO = providenciaDTO;
    }

    public ProvidenciaDTO getProvidenciaDTO() {
        return providenciaDTO;
    }

    public void setProvidenciaDTO(ProvidenciaDTO providenciaDTO) {
        this.providenciaDTO = providenciaDTO;
    }
}
