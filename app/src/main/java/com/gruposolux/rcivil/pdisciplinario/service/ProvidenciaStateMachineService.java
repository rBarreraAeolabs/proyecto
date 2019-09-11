package com.gruposolux.rcivil.pdisciplinario.service;

import com.google.common.collect.Lists;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.AccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.utils.ProvidenciaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableStateMachine
class ProvidenciaStateMachineService {

    @Autowired
    private ProvidenciaRepository entityRepository;

    @Autowired
    private PersistStateMachineHandler persistStateMachineHandler;

    public List<Providencia> getEntities() {
        return Lists.newArrayList(entityRepository.findAll());
    }

    public Providencia getEntity(Long id) {
        return entityRepository.findOne(id);
    }

    public Providencia createEntity(Providencia entity) {
        return entityRepository.save(entity);
    }

    public Boolean nextState(Long id, AccionesProvidencia accion, EstadoProvidencia estado) {
        Providencia providencia = entityRepository.findOne(id);
        return persistStateMachineHandler.handleEventWithState(
            MessageBuilder.withPayload(accion.name()).setHeader(ProvidenciaConstants.entityHeader, providencia).build(),
            estado.name()
        );
    }

}
