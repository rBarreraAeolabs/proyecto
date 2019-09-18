//package com.gruposolux.rcivil.pdisciplinario.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.EnableStateMachine;
//import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
//
//@Configuration
//@EnableStateMachine
//public class PersistHandlerConfig {
//
//    @Autowired
//    private StateMachine<String, String> ProvidenciaStateMachine;
//
//    @Autowired
//    private ProvidenciaPersistStateChangeListener providenciaPersistStateChangeListener;
//
//    @Bean
//    public PersistStateMachineHandler persistStateMachineHandler() {
//        PersistStateMachineHandler handler = new PersistStateMachineHandler(ProvidenciaStateMachine);
//        handler.addPersistStateChangeListener(providenciaPersistStateChangeListener);
//        return handler;
//    }
//}
