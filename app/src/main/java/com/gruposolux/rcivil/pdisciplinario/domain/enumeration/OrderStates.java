package com.gruposolux.rcivil.pdisciplinario.domain.enumeration;


import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

public enum OrderStates {
    CREADO,//presentado,entregado
    EN_PROCESO,
    ELEVADA,
    TERMINADA
}


