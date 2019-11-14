//package com.gruposolux.rcivil.pdisciplinario.service;
//
//import com.gruposolux.rcivil.pdisciplinario.AppApp;
//import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.AccionesProvidencia;
//import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
////import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Estados;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppApp.class)
//public class ProvidenciaStateMachineServiceTest {
//
//    @Autowired
//    private StateMachine<String, String> stateMachine;
//
//    @Autowired
//    private ProvidenciaStateMachineService providenciaStateMachineService;
//
//    @Test
//    public void contextLoads() {
//        Assertions.assertThat(stateMachine).isNotNull();
//        //Assertions.assertThat(stateMachine.getInitialState().getId()).isEqualTo(Estados.NUEVA_PROVIDENCIA.name());
//    }
//    @Test
//    public void firstStepTest() {
//        // Acccion dada
//        stateMachine.sendEvent(AccionesProvidencia.CREAR_PROVIDENCIA.name());
//        // Estado esperado
//       // stateMachine.getState().getId().equals(Estados.RESOLUCION_Y_MEMO.name());
//    }
//    @Test
//    public void testGreenWay() {
//        // Arrange
//        // Act
//        stateMachine.sendEvent(AccionesProvidencia.CREAR_PROVIDENCIA.name());
//        stateMachine.sendEvent(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name());
//        // Assert
//       // Assertions.assertThat(stateMachine.getState().getId()).isEqualTo(Estados.FIRMA_RESOLUCION.name());
//    }
//        //Test o Metodo creado para probar un cambio de estado de un estado aleatorio y obteniendo en una variable el estado siguiente si el caso es positivo
//    @Test
//    public void testWithProvidencia() {
//
//        Long providenciaId = Long.valueOf(1003);
//        EstadoProvidencia estadoActual = EstadoProvidencia.RESOLUCION_Y_MEMO;
//        AccionesProvidencia evento = AccionesProvidencia.CREAR_PROVIDENCIA;
//        providenciaStateMachineService.nextState(providenciaId, evento, estadoActual );
//        String estado = stateMachine.getState().getId();
//    }
//}
