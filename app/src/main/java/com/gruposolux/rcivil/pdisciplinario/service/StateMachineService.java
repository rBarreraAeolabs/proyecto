package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.OrderEvents;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.OrderStates;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;

import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import org.springframework.statemachine.state.State;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@EnableStateMachine


public class StateMachineService {
//    private final StateMachineFactory<OrderStates, OrderEvents>factory;

//    public StateMachineService(StateMachineFactory<OrderStates, OrderEvents> factory) {
//    this.factory = factory;
//}


//    @Override
//    public void run(String... args) throws Exception {
//        SpringApplication.run(StateMachineService.class, args);
//
//    }
////
//
//    private final StateMachineFactory<OrderStates, OrderEvents>factory;
//@Autowired
//    public StateMachineService(StateMachineFactory<OrderStates, OrderEvents> factory) {
//        this.factory = factory;
//    }
//
//
//    public void Maquina() {
//        StateMachine<OrderStates,OrderEvents> machine = factory.getStateMachine("13232");
//        machine.start();
//      //  log.info ("current state:" + machine.getState().getId().name());
//    }


    @Log
    @Configuration
    @EnableStateMachine
        /**
         *  StateMachineConfigureAdapter  es una clase de la dependencia stateMachine
         */
    class SimpleEnumStatemachineConfiguration extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {

        /**
         * Configuracion de las Transiciones
         * Condicionar por estados
         */
        @Override
        public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
            /** Se deben poner todos los caminos posibles o el estado quedaria estancado
             *            */

            transitions
                .withExternal().source(OrderStates.CREADO).target(OrderStates.EN_PROCESO).event(OrderEvents.INICIA_PROCESO)
                .and()
                .withExternal().source(OrderStates.EN_PROCESO).target(OrderStates.ELEVADA).event(OrderEvents.ELEVAR)
                .and()
                .withExternal().source(OrderStates.EN_PROCESO).target(OrderStates.TERMINADA).event(OrderEvents.REVISAR)
                .and()
                .withExternal().source(OrderStates.ELEVADA).target(OrderStates.TERMINADA).event(OrderEvents.CERRAR)
                .and()
                .withExternal().source(OrderStates.ELEVADA).target(OrderStates.EN_PROCESO).event(OrderEvents.TERMINAR);


        }

        /**
         * configuracion del orden de los estados
         */
        @Override
        public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
            states
                .withStates()
                .initial(OrderStates.CREADO)
                .state(OrderStates.EN_PROCESO)
                .state(OrderStates.ELEVADA)
                .end(OrderStates.TERMINADA);

        }

        @Override
        public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config) throws Exception {

            StateMachineListenerAdapter<OrderStates, OrderEvents> adapter = new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
                @Override
                public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {

                    //log.info(String.format("stateChanged(from: %s, to: %s)",from+"",to+""));
                    //super.stateChanged(from,to);
                }
            };
            config.withConfiguration()
                .autoStartup(false)
                .listener(adapter);

        }


    }


}





