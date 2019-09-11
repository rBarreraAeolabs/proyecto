package com.gruposolux.rcivil.pdisciplinario.config;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Configuration
/**
 *  StateMachineConfigureAdapter  es una clase de la dependencia stateMachine
 */
@EnableStateMachine(name = "providenciaStateMachine")
public class ProvidenciaStateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {

    private static Logger logger = LoggerFactory.getLogger(ProvidenciaStateMachineConfiguration.class);

//    @Autowired
//    private IdleToActiveGuard idleToActiveGuard;
//
//    @Autowired
//    private IdleToActiveAction idleToActiveAction;

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
        throws Exception {
        Set<String> stringStates = new HashSet<>();
        EnumSet.allOf(EstadoProvidencia.class).forEach(entity -> stringStates.add(entity.name()));
        states.withStates()
            .initial(EstadoProvidencia.NUEVA_PROVIDENCIA.name())
            .state(EstadoProvidencia.RESOLUCION_Y_MEMO.name())
            .states(stringStates);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
        throws Exception {
        transitions
            .withExternal()
            .source(EstadoProvidencia.NUEVA_PROVIDENCIA.name()).target(EstadoProvidencia.PROVIDENCIA_CREADA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.PROVIDENCIA_CREADA.name()).target(EstadoProvidencia.GESTOR_DOCUMENTAL_ASIGNA_NUMERO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.GESTOR_DOCUMENTAL_ASIGNO_NUMERO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.GESTOR_DOCUMENTAL_ASIGNA_NUMERO.name()).target(EstadoProvidencia.SUB_DIRECCION_RECIBE_PROVIDENCIA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_CONFIRMA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_RECIBE_PROVIDENCIA.name()).target(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.GESTOR_DOCUMENTAL_ASIGNO_NUMERO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_ASIGNA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.ENVIAR_A_SUB_DIRRECION_JURIDICA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ENVIAR_A_SUB_DIRRECION_JURIDICA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.ENVIAR_A_DGD.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ENVIAR_A_DGD.name()).target(EstadoProvidencia.ENVIAR_A_DN.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIADA_A_DGD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ENVIAR_A_DN.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIADA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNA_TIPO_SOLICITUD.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_FIRMA_RESOLUCION.name()).event(AccionesProvidencia.ENVIA_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNA_TIPO_SOLICITUD.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNO_TIPO_SOLICITUD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.RECEPCION_DGD.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNA_NUMERO.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.RECEPCION_DGD.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGD_REGISTRA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name()).target(EstadoProvidencia.UPD_ELABORO_NOTIFICACION_VISTA_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_ADJUNTO_NOTIFICACION_VISTA_FISCAL.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_ELABORO_NOTIFICACION_VISTA_FISCAL.name()).target(EstadoProvidencia.ENVIADO_A_SUBDIRECCION_JURIDICA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ENVIADO_A_SUBDIRECCION_JURIDICA.name()).target(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIAR_A_UPD.name()).event(AccionesProvidencia.ENVIA_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name()).target(EstadoProvidencia.FISCAL_NOTIFICADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_NOTIFICO_A_FISCAL.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.FISCAL_ACEPTO.name())
            .event(AccionesProvidencia.FISCAL_ACEPTA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.FISCAL_RECHAZO.name())
            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_ACEPTO.name()).target(EstadoProvidencia.FISCAL_REDACTA_MEMO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_REDACTA_MEMO.name()).target(EstadoProvidencia.UPD_DA_INICIO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_ADJUNTA_MEMO.name()).event(AccionesProvidencia.ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_DA_INICIO.name()).target(EstadoProvidencia.FISCAL_COMIENZA_INVESTIGACION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_DIO_INICIO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_COMIENZA_INVESTIGACION.name()).target(EstadoProvidencia.FISCAL_ENVIA_A_UPD_MEMO_CIERRE.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_TERMINO_INVESTIGACION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_ENVIA_A_UPD_MEMO_CIERRE.name()).target(EstadoProvidencia.UPD_REGISTRA_CIERRE.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_ADJUNTA_MEMO_CIERRE.name()).event(AccionesProvidencia.ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REGISTRA_CIERRE.name()).target(EstadoProvidencia.FISCAL_NOTIFICA_A_INCULPADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_REGISTRA_CIERRE.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_NOTIFICA_A_INCULPADO.name()).target(EstadoProvidencia.INCULPADO_ENVIA_MEMO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_NOTIFICO_A_INCULPADO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.INCULPADO_ENVIA_MEMO.name()).target(EstadoProvidencia.FISCAL_FORMULA_CARGOS.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.INCULPADO_ENVIO_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_FORMULA_CARGOS.name()).target(EstadoProvidencia.TERMINO_PROBATORIO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_FORMULO_CARGOS.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.TERMINO_PROBATORIO.name()).target(EstadoProvidencia.FISCAL_REMITE_EXPEDIENTE.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.CONFIRMA_TERMINO_PROBATORIO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_REMITE_EXPEDIENTE.name()).target(EstadoProvidencia.DN_RECIBE_SUMARIO_COMPLETO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.REMITE_EXPEDIENTE.name()).event(AccionesProvidencia.ENVIA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DN_RECIBE_SUMARIO_COMPLETO.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name()).target(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_ASIGNO_ABOGADO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name()).target(EstadoProvidencia.SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ABOGADO_ADJUNTA_INFORME.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {

        StateMachineListenerAdapter<String, String> adapter = new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                logger.info("Listener stateChanged from {} to {}", (from != null) ? from.getId() : null, (to != null) ? to.getId() : null);
            }

            @Override
            public void eventNotAccepted(Message<String> event) {
                logger.info("Listener eventNotAccepted {}", event);
            }

            @Override
            public void stateEntered(State<String, String> state) {
                logger.info("Listener stateEntered in {}", state.getId());
            }

            @Override
            public void stateExited(State<String, String> state) {
                logger.info("Listener stateExited in {}", state.getId());
            }

            @Override
            public void transition(Transition<String, String> transition) {
                logger.info("Listener transition from {} to {}", (transition.getSource() != null) ? transition.getSource().getId()
                    : null, (transition.getTarget() != null) ? transition.getTarget().getId() : null);
            }

            @Override
            public void transitionStarted(Transition<String, String> transition) {
                logger.info("Listener transitionStarted from {} to {}", (transition.getSource() != null) ? transition.getSource().getId()
                    : null, (transition.getTarget() != null) ? transition.getTarget().getId() : null);
            }

            @Override
            public void transitionEnded(Transition<String, String> transition) {
                logger.info("Listener transitionEnded from {} to {}", (transition.getSource() != null) ? transition.getSource().getId()
                    : null, (transition.getTarget() != null) ? transition.getTarget().getId() : null);
            }

            @Override
            public void stateMachineStarted(StateMachine<String, String> stateMachine) {
                logger.info("Listener stateMachineStarted");
            }

            @Override
            public void stateMachineStopped(StateMachine<String, String> stateMachine) {
                logger.info("Listener stateMachineStopped");
            }

            @Override
            public void stateMachineError(StateMachine<String, String> stateMachine, Exception exception) {
                logger.info("Listener stateMachineError {}", exception.getMessage());
            }

            @Override
            public void stateContext(StateContext<String, String> stateContext) {
                logger.info("Listener stateContext (Message Payload: {}, Message Headers: {}, Variables: {}", (stateContext.getMessage() != null)
                    ? stateContext.getMessage().getPayload() : null, stateContext.getMessageHeaders(), stateContext.getExtendedState().getVariables());
            }
        };
        config.withConfiguration()
            .autoStartup(true)
            .listener(adapter);
    }
}
