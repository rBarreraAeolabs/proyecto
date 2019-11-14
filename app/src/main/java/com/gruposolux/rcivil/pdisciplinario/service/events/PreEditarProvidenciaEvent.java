package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.User;

public class PreEditarProvidenciaEvent {
    private Providencia providencia;
    private User user;

    public PreEditarProvidenciaEvent(Providencia providencia, User user) {
        this.providencia = providencia;
        this.user = user;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public User getUser() {
        return user;
    }
}
