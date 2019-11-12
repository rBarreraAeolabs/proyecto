package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DerivacionDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;

import java.util.Set;

public class PostCrearProvidenciaEvent {

    private ProvidenciaDTO providenciaDTO;

    private User user;

    private Set<AdjuntoDTO> adjuntosDTOs;

    private DerivacionDTO derivacionDTO;

    public PostCrearProvidenciaEvent(ProvidenciaDTO providenciaDTO, User user, Set<AdjuntoDTO> adjuntosDTOs, DerivacionDTO derivacionDTO) {
        this.providenciaDTO = providenciaDTO;
        this.user = user;
        this.adjuntosDTOs = adjuntosDTOs;
        this.derivacionDTO = derivacionDTO;
    }

    public ProvidenciaDTO getProvidenciaDTO() {
        return providenciaDTO;
    }

    public void setProvidenciaDTO(ProvidenciaDTO providenciaDTO) {
        this.providenciaDTO = providenciaDTO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<AdjuntoDTO> getAdjuntosDTOs() {
        return adjuntosDTOs;
    }

    public void setAdjuntosDTOs(Set<AdjuntoDTO> adjuntosDTOs) {
        this.adjuntosDTOs = adjuntosDTOs;
    }

    public DerivacionDTO getDerivacionDTO() {
        return derivacionDTO;
    }

    public void setDerivacionDTO(DerivacionDTO derivacionDTO) {
        this.derivacionDTO = derivacionDTO;
    }
}
