package com.gruposolux.rcivil.pdisciplinario.config;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.utils.ProvidenciaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
public class ProvidenciaPersistStateChangeListener implements PersistStateChangeListener {


    private final static Logger logger = LoggerFactory.getLogger(ProvidenciaPersistStateChangeListener.class);

    @Autowired
    private ProvidenciaRepository providenciaRepository;

    @Override
    public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> stateMachine) {
        if (message != null && message.getHeaders().containsKey(ProvidenciaConstants.entityHeader)) {
            Providencia providencia = message.getHeaders().get(ProvidenciaConstants.entityHeader, Providencia.class);
            providencia.setEstadoActual(state.getId());
            logger.debug("Persisting: the new entity.. {}", providencia);
            providenciaRepository.save(providencia);
        }
    }
}

