package com.gruposolux.rcivil.pdisciplinario.config.Guards;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.utils.ProvidenciaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class IdleToActiveGuard implements Guard<String, String> {

    private final static Logger logger = LoggerFactory.getLogger(IdleToActiveGuard.class);

    @Override
    public boolean evaluate(StateContext<String, String> context) {
        Providencia providencia = (Providencia) context.getMessageHeader(ProvidenciaConstants.entityHeader);
        if (providencia != null) {
            logger.debug("Guard: protecting the transition.. {}", providencia);
        } else {
            logger.debug("Guard: Wrong transition?");
        }
        return providencia != null;
    }
}
