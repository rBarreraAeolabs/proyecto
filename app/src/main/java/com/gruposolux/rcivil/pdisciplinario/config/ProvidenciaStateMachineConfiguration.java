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
 *
 *  CREATED BY RUBEN BARRERA
 */
@EnableStateMachine(name = "providenciaStateMachine")
public class ProvidenciaStateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {

    private static Logger logger = LoggerFactory.getLogger(ProvidenciaStateMachineConfiguration.class);

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
    public void configure(StateMachineTransitionConfigurer<String, String > transitions)
        throws Exception {


        {
            transitions
                .withExternal()
                .source(EstadoProvidencia.NUEVA_PROVIDENCIA.name()).target(EstadoProvidencia.PROVIDENCIA_CREADA.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.PROVIDENCIA_CREADA.name()).target(EstadoProvidencia.GESTOR_DOCUMENTAL_ASIGNA_NUMERO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.GESTOR_DOCUMENTAL_ASIGNA_NUMERO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name()).target(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA.name()).target(EstadoProvidencia.DGD_DESPACHA_A_DN.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_A_DN.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.DGD_RECEPCIONA.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_RECEPCIONA.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name()).target(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name()).target(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_FISCAL.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_FISCAL.name()).target(EstadoProvidencia.FISCAL_NOTIFICADO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.INVESTIGACION.name())
                .event(AccionesProvidencia.FISCAL_ACEPTA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.FISCAL_RECHAZO.name())
                .event(AccionesProvidencia.FISCAL_RECHAZA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.INVESTIGACION.name()).target(EstadoProvidencia.FORMULA_CARGOS_Y_NOTIFICA.name())
                .event(AccionesProvidencia.FISCAL_NOTIFICA_A_UPD_CIERRE.name())
                .and();
        }
            /**
             *   FORMULA CARGOS 1  CONTINUA EL FLUJO CON MAS OPCIONES
             */
        {
            transitions
                .withExternal()
                .source(EstadoProvidencia.FORMULA_CARGOS_Y_NOTIFICA.name()).target(EstadoProvidencia.INCULPADO_ENVIA_MEMO.name())
                .event(AccionesProvidencia.INCULPADO_ENVIA_MEMO.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.FORMULA_CARGOS_Y_NOTIFICA.name()).target(EstadoProvidencia.INCULPADO_NO_ENVIA_MEMO.name())
                .event(AccionesProvidencia.INCULPADO_NO_ENVIA_MEMO.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.INCULPADO_ENVIA_MEMO.name()).target(EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN.name())
                .event(AccionesProvidencia.FORMULAR_CARGOS.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.INCULPADO_ENVIA_MEMO.name()).target(EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN.name())
                .event(AccionesProvidencia.TERMINO_PROBATORIO.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.INCULPADO_NO_ENVIA_MEMO.name()).target(EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN.name())
                .event(AccionesProvidencia.FORMULAR_CARGOS.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.INCULPADO_NO_ENVIA_MEMO.name()).target(EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN.name())
                .event(AccionesProvidencia.TERMINO_PROBATORIO.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN.name()).target(EstadoProvidencia.ENVIAR_SUMARIO_A_SUB_DIRECCION.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ENVIAR_SUMARIO_A_SUB_DIRECCION.name()).target(EstadoProvidencia.DGD_DESPACHA_SUMARIO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_SUMARIO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO_Y_NOTIFICA_A_ABOGADO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO_Y_NOTIFICA_A_ABOGADO.name()).target(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name()).target(EstadoProvidencia.SI_DE_ACUERDO.name())
                .event(AccionesProvidencia.SI_DE_ACUERDO.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name()).target(EstadoProvidencia.NO_REABRE.name())
                .event(AccionesProvidencia.NO_REABRE.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name()).target(EstadoProvidencia.NO_PROPONE.name())
                .event(AccionesProvidencia.NO_PROPONE.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SI_DE_ACUERDO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.NO_REABRE.name()).target(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.NO_PROPONE.name()).target(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name()).target(EstadoProvidencia.SUB_DIRECCION_REVISA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUB_DIRECCION_REVISA_INFORME.name()).target(EstadoProvidencia.SECRETARIA_DESPACHA_INFORME.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_DESPACHA_INFORME.name()).target(EstadoProvidencia.DGD_DESPACHA_SUMARIO_COMPLETO.name())
                .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
                .and()

                /**
                 * Estados en los que se puede saltar a peticion de prorroga
                 *
                 */
                .withExternal()
                .source(EstadoProvidencia.INVESTIGACION.name()).target(EstadoProvidencia.PETICION_PRORROGA_1.name())
                .event(AccionesProvidencia.PRORROGA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
                //
                .and()
                .withExternal()
                .source(EstadoProvidencia.INVESTIGACION.name()).target(EstadoProvidencia.PETICION_PRORROGA_2.name())
                .event(AccionesProvidencia.PRORROGA2.name())
//            .event(AccionesProvidencia.FISCAL_ADJUNTA_MEMO.name()).event(AccionesProvidencia.ENVIA_A_UPD.name())
                .and();
        }

//     ----------------------------------  PRORROGA 1  ----------------------------------------------------------------------
        {
            transitions
                .withExternal()
                .source(EstadoProvidencia.DGD_RECEPCIONA.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_PRORROGA_1.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_PRORROGA_1.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION_PRORROGA_1.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION_PRORROGA_1.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name()).target(EstadoProvidencia.UPD_NOTIFICA_PRORROGA_1_FISCAL.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_NOTIFICA_PRORROGA_1_FISCAL.name()).target(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_PRORROGA_1_FISCAL.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_PRORROGA_1_FISCAL.name()).target(EstadoProvidencia.INVESTIGACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                //     ----------------------------------  PRORROGA 2  ----------------------------------------------------------------------
                .withExternal()
                .source(EstadoProvidencia.DGD_RECEPCIONA.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_PRORROGA_2.name())
                .event(AccionesProvidencia.PRORROGA2.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_PRORROGA_2.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION_PRORROGA_2.name())
                .event(AccionesProvidencia.PRORROGA2.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION_PRORROGA_2.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name()).target(EstadoProvidencia.UPD_NOTIFICA_PRORROGA_2_FISCAL.name())
                .event(AccionesProvidencia.PRORROGA2.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_NOTIFICA_PRORROGA_2_FISCAL.name()).target(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_PRORROGA_2_FISCAL.name())
                .event(AccionesProvidencia.PRORROGA2.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_PRORROGA_2_FISCAL.name()).target(EstadoProvidencia.INVESTIGACION.name())
                .event(AccionesProvidencia.PRORROGA.name())
                .and();
        }

            //  ------------------------------------------  FLUJO ESPECIFICO PARA PROVIDENCIA SOBRECEDER Y ABSOLVER --------------------------------------------

        {
            transitions

                .withExternal()
                .source(EstadoProvidencia.PROVIDENCIA_CREADA.name()).target(EstadoProvidencia.SJ_RECIBE_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBE_PROVIDENCIA.name()).target(EstadoProvidencia.SECRETARIA_REVISA_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_PROVIDENCIA.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNA_A_RESOLUCION_Y_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUB_DIRECCION_ASIGNA_A_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.SECRETARIA_DESPACHA_A_UPD.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_DESPACHA_A_UPD.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_VISA.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_VISA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.ASIGNACION_DE_NUMERO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ASIGNACION_DE_NUMERO.name()).target(EstadoProvidencia.RECIBE_SJ_PARA_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.RECIBE_SJ_PARA_MEMO.name()).target(EstadoProvidencia.REDACCION_NOTIFICACION_INCULPADO.name())
                .event(AccionesProvidencia.FLUJO_SOBRESEER_ABSOLVER.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REDACCION_NOTIFICACION_INCULPADO.name()).target(EstadoProvidencia.DEMANDADO_NOTIFICADO.name())
                .event(AccionesProvidencia.NOTIFICA.name())
                .and();
        }


            // --------------------------------------------------   FLUJO ESPECIFICO PARA PROVIDENCIA SANCION    -----------------------------------------------------
        {
            transitions

                .withExternal()
                .source(EstadoProvidencia.PROVIDENCIA_CREADA.name()).target(EstadoProvidencia.SJ_RECIBE_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBE_PROVIDENCIA.name()).target(EstadoProvidencia.SECRETARIA_REVISA_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_PROVIDENCIA.name()).target(EstadoProvidencia.SUBDIRECCION_ASIGNA_UPD_RESOLUCION_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUBDIRECCION_ASIGNA_UPD_RESOLUCION_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_UPD.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_UPD.name()).target(EstadoProvidencia.REDACCION_RESOLUCION_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REDACCION_RESOLUCION_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_MEMO.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_MEMO.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_VISA_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_VISA_SUBDIRECCION.name()).target(EstadoProvidencia.DGD_DESPACHA_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_RESOLUCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION.name()).target(EstadoProvidencia.SJ_RECIBE_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBE_RESOLUCION.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_RESOLUCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION_RESOLUCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_RESOLUCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_DE_SUBDIRECCION_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_DE_SUBDIRECCION_A_RESOLUCION.name()).target(EstadoProvidencia.NOTIFICAR_INCULPADO.name())
                .event(AccionesProvidencia.FLUJO_SANCION.name())
                .and()


                //  -------------------------------------------------    FLUJO ESPECIFICO PARA PROVIDENCIA NO APELA  -------------------------------------------------

                .withExternal()
                .source(EstadoProvidencia.SE_NOTIFICO_INCULPADO.name()).target(EstadoProvidencia.CERTIFICACION_NO_APELA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.CERTIFICACION_NO_APELA.name()).target(EstadoProvidencia.REDACCION_RESOLUCION_MEMO_NO_APELA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REDACCION_RESOLUCION_MEMO_NO_APELA.name()).target(EstadoProvidencia.REVISION_RESOLUCION_MEMO_NO_APELA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REVISION_RESOLUCION_MEMO_NO_APELA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_Y_VISA_DE_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_FIRMA_Y_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.REVISANDO_FIRMA_Y_VISA_DE_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REVISANDO_FIRMA_Y_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.GESTOR_DOCUMENTAL_DESPACHA_A_DN.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.GESTOR_DOCUMENTAL_DESPACHA_A_DN.name()).target(EstadoProvidencia.DN_FIRMA_RESOLUCION_NO_APELA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DN_FIRMA_RESOLUCION_NO_APELA.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION_NO_APELA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION_NO_APELA.name()).target(EstadoProvidencia.TOMA_DE_RAZON_O_REGISTRA_O_REPRESENTA.name())
                .event(AccionesProvidencia.FLUJO_NO_APELA.name())
                .and()
                //  -----------------------------   FLUJO ESPECIFICO PARA PROVIDENCIA TOMA RAZON O REGISTRA  ------------------------------------

                .withExternal()
                .source(EstadoProvidencia.TOMA_DE_RAZON_O_REGISTRA_O_REPRESENTA.name()).target(EstadoProvidencia.TOMA_DE_RAZON.name())
                .event(AccionesProvidencia.FLUJO_TOMA_RAZON.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.TOMA_DE_RAZON_O_REGISTRA_O_REPRESENTA.name()).target(EstadoProvidencia.REGISTRA.name())
                .event(AccionesProvidencia.FLUJO_REGISTRA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.TOMA_DE_RAZON.name()).target(EstadoProvidencia.SJ_RECIBE_PARA_REDACCION.name())
                .event(AccionesProvidencia.FLUJO_TOMA_RAZON.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REGISTRA.name()).target(EstadoProvidencia.SJ_RECIBE_PARA_REDACCION.name())
                .event(AccionesProvidencia.FLUJO_REGISTRA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBE_PARA_REDACCION.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_MEMO.name())
                .event(AccionesProvidencia.FLUJO_TOMA_RAZON.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_MEMO.name()).target(EstadoProvidencia.REALIZAR_NOTIFICACIONES.name())
                .event(AccionesProvidencia.FLUJO_TOMA_RAZON.name())
                .and()

                //  -----------------------------    FLUJO ESPECIFICO PARA PROVIDENCIA REPRESENTA  -----------------------------------------------

                .withExternal()
                .source(EstadoProvidencia.TOMA_DE_RAZON_O_REGISTRA_O_REPRESENTA.name()).target(EstadoProvidencia.MEMO.name())
                .event(AccionesProvidencia.FLUJO_REPRESENTA.name())
                .and()

                //  -------------------------------------------------    FLUJO ESPECIFICO PARA PROVIDENCIA APELA  ----------------------------------------------------

                .withExternal()
                .source(EstadoProvidencia.SE_NOTIFICO_INCULPADO.name()).target(EstadoProvidencia.APELACION_RECIBIDA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.APELACION_RECIBIDA.name()).target(EstadoProvidencia.SJ_RECEPCIONA_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECEPCIONA_APELACION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_APELACION.name()).target(EstadoProvidencia.ASIGNANDO_ABOGADO_A_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ASIGNANDO_ABOGADO_A_APELACION.name()).target(EstadoProvidencia.ABOGADO_ELABORA_INFORME_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME_APELACION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_INFORME_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_INFORME_APELACION.name()).target(EstadoProvidencia.SUBDIRECCION_REVISA_INFORME_APELACION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUBDIRECCION_REVISA_INFORME_APELACION.name()).target(EstadoProvidencia.SECRETARIA_DESPACHA_INFORME_APELACION_A_DGD.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_DESPACHA_INFORME_APELACION_A_DGD.name()).target(EstadoProvidencia.DGD_DESPACHA_INFORME_APELACION_A_DN.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGD_DESPACHA_INFORME_APELACION_A_DN.name()).target(EstadoProvidencia.DN_EMITE_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DN_EMITE_PROVIDENCIA.name()).target(EstadoProvidencia.PROVIDENCIA_EN_SJ.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.PROVIDENCIA_EN_SJ.name()).target(EstadoProvidencia.REVISION_DE_PROVIDENCIA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REVISION_DE_PROVIDENCIA.name()).target(EstadoProvidencia.SUBDIRECCION_ASIGNA_REDACCION_RESOLUCION_MEMO.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SUBDIRECCION_ASIGNA_REDACCION_RESOLUCION_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_REDACCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_REDACCION.name()).target(EstadoProvidencia.REDACCION_RESOLUCION_MEMO_UPD.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REDACCION_RESOLUCION_MEMO_UPD.name()).target(EstadoProvidencia.SECRETARIA_REVISA_REDACCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_REDACCION.name()).target(EstadoProvidencia.VISA_Y_FIRMA_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.VISA_Y_FIRMA_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_VISA_Y_FIRMA_SUBDIRECCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_VISA_Y_FIRMA_SUBDIRECCION.name()).target(EstadoProvidencia.DESPACHA_A_DN.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DESPACHA_A_DN.name()).target(EstadoProvidencia.DN_FIRMA_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DN_FIRMA_RESOLUCION.name()).target(EstadoProvidencia.DGDP_ASIGNA_NUMERO.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DGDP_ASIGNA_NUMERO.name()).target(EstadoProvidencia.SJ_RECIBE_RESOLUCION_CON_NUMERO.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBE_RESOLUCION_CON_NUMERO.name()).target(EstadoProvidencia.REDACCION_DE_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.REDACCION_DE_RESOLUCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION.name()).target(EstadoProvidencia.SUBDIRECCION_VISA_FIRMA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                //          SOLO SI LE DIO AL BOTON REALIZAR MEMO CONDUCTO ES QUE PUEDE MOVERSE AL OTRO ESTADO " REALIZADO_MEMO_CONDUCTOR ".
                .withExternal()
                .source(EstadoProvidencia.REALIZADO_MEMO_CONDUCTOR.name()).target(EstadoProvidencia.SECRETARIA_REVISA_VISA_FIRMA_MEMO_CONDUCTOR.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SECRETARIA_REVISA_VISA_FIRMA_MEMO_CONDUCTOR.name()).target(EstadoProvidencia.DESPACHO_MEMO_CONDUCTOR_A_DN.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.DESPACHO_MEMO_CONDUCTOR_A_DN.name()).target(EstadoProvidencia.FIRMA_DEL_DN_A_RESOLUCION.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.FIRMA_DEL_DN_A_RESOLUCION.name()).target(EstadoProvidencia.ESPERANDO_ASIGNACION_DE_NUMERO.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ESPERANDO_ASIGNACION_DE_NUMERO.name()).target(EstadoProvidencia.EXAMEN_DE_LEGALIDAD.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                //  ------------------------------------------  ALCANCE Y RESOLUCION  --------------------------------------------------

                .withExternal()
                .source(EstadoProvidencia.EXAMEN_DE_LEGALIDAD.name()).target(EstadoProvidencia.ALCANCE_O_RESOLUCION.name())
                .event(AccionesProvidencia.SELECCIONO_EXAMEN_LEGALIDAD.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.ALCANCE_O_RESOLUCION.name()).target(EstadoProvidencia.SJ_RECIBIO_RESOLUCION.name())
                .event(AccionesProvidencia.SELECCIONO_RESOLUCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBIO_RESOLUCION.name()).target(EstadoProvidencia.NOTIFICA_E_INFORMA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SELECCION_ALCANCE.name()).target(EstadoProvidencia.SJ_RECIBIO_ALCANCE.name())
                .event(AccionesProvidencia.SELECCIONO_ALCANCE_CON_RESOLUCION.name())
                .and()
                .withExternal()
                .source(EstadoProvidencia.SJ_RECIBIO_ALCANCE.name()).target(EstadoProvidencia.NOTIFICA_E_INFORMA.name())
                .event(AccionesProvidencia.FLUJO_APELA.name())
                .and()

//            ------------------------------------------  SUSPENSION / MULTA / SENSURA / DESTITUCION  ---------------------------------------

            .withExternal()
            .source(EstadoProvidencia.NOTIFICA_E_INFORMA_SUSPENSION.name()).target(EstadoProvidencia.SUSPENSION.name())
            .event(AccionesProvidencia.NOTIFICO_A_REMUNERACION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.NOTIFICA_E_INFORMA_MULTA.name()).target(EstadoProvidencia.MULTA.name())
            .event(AccionesProvidencia.NOTIFICO_A_REMUNERACION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.NOTIFICA_E_INFORMA_SENSURA.name()).target(EstadoProvidencia.SENSURA.name())
            .event(AccionesProvidencia.NOTIFICAR_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.NOTIFICA_E_INFORMA_DESTITUCION.name()).target(EstadoProvidencia.DESTITUCION.name())
            .event(AccionesProvidencia.NOTIFICAR_A_DGDP.name())
            .and()



            ;}

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
            public void transition(Transition<String, String> transition) {
                logger.info("Listener transition from {} to {}" , (transition.getSource() != null) ? transition.getSource().getId()
                    :null, (transition.getTarget() != null) ? transition.getTarget().getId() : null);
            }

            @Override
            public void transitionStarted(Transition<String, String> transition) {
                logger.info("Listener transitionStarted from {} to {}", (transition.getSource() != null) ? transition.getSource().getId()
                    : null, (transition.getTarget() != null) ? transition.getTarget().getId() : null);
            }

            @Override
            public void transitionEnded(Transition<String, String> transition) {
                logger.info("Listener transitionEnded from {} to {}" , (transition.getSource() != null) ? transition.getSource().getId()
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
