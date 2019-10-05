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
    public void configure(StateMachineTransitionConfigurer<String, String > transitions)
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
            .source(EstadoProvidencia.GESTOR_DOCUMENTAL_ASIGNA_NUMERO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_CONFIRMA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.GESTOR_DOCUMENTAL_ASIGNO_NUMERO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_DEBE_ASIGNAR.name()).target(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.GESTOR_DOCUMENTAL_ASIGNO_NUMERO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SECRETARIA_ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_Y_MEMO.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA.name()).target(EstadoProvidencia.DGD_DESPACHA_A_DN.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SECRETARIA_REVISA.name()).event(AccionesProvidencia.ENVIA_A_DGD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGD_DESPACHA_A_DN.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIADA_A_DGD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNO_TIPO_SOLICITUD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.DGD_RECEPCIONA.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNA_NUMERO.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGD_RECEPCIONA.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGD_REGISTRA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION_VISTA_FISCAL.name()).target(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_ADJUNTO_NOTIFICACION_VISTA_FISCAL.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_NOTIFICACION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FIRMA_DE_SUBDIRECCION_A_NOTIFICACION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_NOTIFICACION.name()).target(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SECRETARIA_ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_NOTIFICA_FISCAL.name()).target(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_FISCAL.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.SECRETARIA_ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGD_DESPACHA_NOTIFICACION_FISCAL.name()).target(EstadoProvidencia.FISCAL_NOTIFICADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DGD_DESPACHO_NOTIFICACION_FISCAL.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.FISCAL_ACEPTO_Y_DA_INICIO.name())
            .event(AccionesProvidencia.FISCAL_ACEPTA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_NOTIFICADO.name()).target(EstadoProvidencia.FISCAL_RECHAZO.name())
            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.FISCAL_ACEPTO_Y_DA_INICIO.name())
            .event(AccionesProvidencia.PRORROGA.name())
//            .event(AccionesProvidencia.GESTOR_DOCUMENTAL_ASIGNO_NUMERO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_ACEPTO_Y_DA_INICIO.name()).target(EstadoProvidencia.FORMULA_CARGOS.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            /**
             * Estados en los que se puede cambiar a proroga.
             *
             */
            .withExternal()
            .source(EstadoProvidencia.FISCAL_ACEPTO_Y_DA_INICIO.name()).target(EstadoProvidencia.PETICION_PRORROGA.name())
            .event(AccionesProvidencia.PRORROGA.name())
//            .event(AccionesProvidencia.FISCAL_RECHAZA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_ACEPTO_Y_DA_INICIO.name()).target(EstadoProvidencia.PETICION_PRORROGA_2.name())
            .event(AccionesProvidencia.PRORROGA2.name())
//            .event(AccionesProvidencia.FISCAL_ADJUNTA_MEMO.name()).event(AccionesProvidencia.ENVIA_A_UPD.name())
            .and()
            /**
             *  Fin de estados para prorroga
             */
            .withExternal()
            .source(EstadoProvidencia.FORMULA_CARGOS.name()).target(EstadoProvidencia.APELACION_INCULPADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_ADJUNTA_MEMO_CIERRE.name()).event(AccionesProvidencia.ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.APELACION_INCULPADO.name()).target(EstadoProvidencia.FORMULA_CARGOS_TERMINO_PROBATORIO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.UPD_REGISTRA_CIERRE.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FORMULA_CARGOS_TERMINO_PROBATORIO.name()).target(EstadoProvidencia.FISCAL_REMITE_EXPEDIENTE.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.FISCAL_NOTIFICO_A_INCULPADO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.FISCAL_REMITE_EXPEDIENTE.name()).target(EstadoProvidencia.DN_RECIBE_SUMARIO_COMPLETO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.REMITE_EXPEDIENTE.name()).event(AccionesProvidencia.ENVIA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DN_RECIBE_SUMARIO_COMPLETO.name()).target(EstadoProvidencia.REVISION_SUMARIO_COMPLETO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.REVISION_SUMARIO_COMPLETO.name()).target(EstadoProvidencia.DGD_RECEPCIONA_SUMARIO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGD_RECEPCIONA_SUMARIO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_SUMARIO.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_ASIGNA_ABOGADO.name()).target(EstadoProvidencia.SECRETARIA_NOTIFICA_A_ABOGADO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_NOTIFICA_A_ABOGADO.name()).target(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME.name()).target(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_INFORME.name()).target(EstadoProvidencia.SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO.name()).target(EstadoProvidencia.SECRETARIA_DESPACHA_A_DGD.name())
            .event(AccionesProvidencia.CREAR_PROVIDENCIA.name())
//            .event(AccionesProvidencia.DN_CONFIRMA_SUMARIO.name()).event(AccionesProvidencia.ENVIAR_A_SUB_DIRECCION.name())
            .and()
/**
 * hasta aqui esta relativamente bien
 */

  //  ------------------------------------------  FLUJO ESPECIFICO PARA PROVIDENCIA SOBRECEDER Y ABSOLVER  --------------------------------------------
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_ABSOLVERSOBRECEDER.name())
//            .event(AccionesProvidencia.SECRETARIA_ENVIA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_ABSOLVERSOBRECEDER.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_ABSOLVERSOBRECEDER.name())
//            .event(AccionesProvidencia.DGDP_ASIGNO_TIPO_SOLICITUD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.UPD_REALIZA_MEMO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_ABSOLVERSOBRECEDER.name())
//            .event(AccionesProvidencia.DGDP_ASIGNA_NUMERO.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REALIZA_MEMO.name()).target(EstadoProvidencia.FOLIO_Y_ARCHIVA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_ABSOLVERSOBRECEDER.name())
//            .event(AccionesProvidencia.UPD_REALIZO_MEMO.name())
            .and()

     // --------------------------------------------------   FLUJO ESPECIFICO PARA PROVIDENCIA SANCION    -----------------------------------------------------

            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.UPD_REDACTA_RESOLUCION_EXCENTA_Y_MEMO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_ASIGNA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REDACTA_RESOLUCION_EXCENTA_Y_MEMO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_EXCENTA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_RESOLUCION_EXCENTA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_RESOLUCION_EXCENTA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.UPD_REDACTA_RESOLUCION_Y_MEMO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_RESOLUCION_EXCENTA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.ENVIADA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.DN_FIRMA_RESOLUCION.name()).event(AccionesProvidencia.ENVIA_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.UPD_ELABORA_NOTIFICACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.DGDP_ASIGNA_NUMERO.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_ELABORA_NOTIFICACION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_NOTIFICACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.UPD_ELABORO_NOTIFICACION.name()).event(AccionesProvidencia.ENVIA_A_SUD_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_NOTIFICACION.name()).target(EstadoProvidencia.UPD_NOTIFICA_A_INCULPADO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_NOTIFICA_A_INCULPADO.name()).target(EstadoProvidencia.PETICION_APELACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_NOTIFICA_A_INCULPADO.name()).target(EstadoProvidencia.CERTIFICACION_NO_APELO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.ENVIAR_A_UPD.name())
            .and()

   //  -------------------------------------------------    FLUJO ESPECIFICO PARA PROVIDENCIA NO APELA  -------------------------------------------------

//            .withExternal()
//            .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.CERTIFICACION_NO_APELO.name())
//            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA.name())
////            .event(AccionesProvidencia.SUB_DIRECCION_ASIGNA.name())
//            .and()
            .withExternal()
            .source(EstadoProvidencia.CERTIFICACION_NO_APELO.name()).target(EstadoProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.ENVIA_A_SUD_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.ENVIA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.DN_FIRMA.name()).event(AccionesProvidencia.DN_ENVIA_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.CONTRALORIA_NOTIFICADA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_SANCION.name())
//            .event(AccionesProvidencia.DGDP_NOTIFICA_CONTRALORIA.name())
            .and()

            //  -------- FLUJO POR LA SELECCION DE  " TOMA DE RAZON " O " REGISTRA " Y " REPRESENTA "   ---------------------
                /**
                 * FLUJO NO APELA "REPRESENTA"
                 */
            .withExternal()
            .source(EstadoProvidencia.CONTRALORIA_NOTIFICADA.name()).target(EstadoProvidencia.RECEPCION_RESPUESTA_CONTRALORIA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.RECEPCIONAR_RESPUESTA_CONTRALORIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.RECEPCION_RESPUESTA_CONTRALORIA.name()).target(EstadoProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name()).event(AccionesProvidencia.UPD_ENVIA_A_SUB_DIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.RECEPCIONAR_RESPUESTA_CONTRALORIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.SUBDIRECCION_FIRMA_VISA.name()).event(AccionesProvidencia.SUBDIRECCION_ENVIA_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.DN_FIRMA.name()).event(AccionesProvidencia.DN_ENVIA_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.CONTRALORIA_NOTIFICADA_REPRESENTA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNO_NUMERO.name()).event(AccionesProvidencia.DGDP_NOTIFICA_CONTRALORIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.CONTRALORIA_NOTIFICADA_REPRESENTA.name()).target(EstadoProvidencia.DGDP_REGISTRA_FIN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REPRESENTA.name())
//            .event(AccionesProvidencia.CONTRALORIA_NOTIFICA_DGDP.name())
            .and()

            /**
             * FLUJO NO APELA "TOMA RAZON" O "REGISTRA"
             */
            .withExternal()
            .source(EstadoProvidencia.CONTRALORIA_NOTIFICADA.name()).target(EstadoProvidencia.CONTRALORIA_NOTIFICO_DGDP.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REGISTRA.name())
//            .event(AccionesProvidencia.CONTRALORIA_NOTFICA_A_DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.CONTRALORIA_NOTIFICO_DGDP.name()).target(EstadoProvidencia.UPD_NOTIFICADO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REGISTRA.name())
//            .event(AccionesProvidencia.DGDP_NOTFICA_A_UPD.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_NOTIFICADO.name()).target(EstadoProvidencia.UPD_REALIZO_MEMO_Y_NOTIFICO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REGISTRA.name())
//            .event(AccionesProvidencia.UPD_REALIZA_MEMO_Y_NOTIFICA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REALIZO_MEMO_Y_NOTIFICO.name()).target(EstadoProvidencia.DGDP_REGISTRO_FIN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA_REGISTRA.name())
//            .event(AccionesProvidencia.UPD_REGISTRA_FIN.name())
            .and()

    //  -------------------------------------------------    FLUJO ESPECIFICO PARA PROVIDENCIA APELA  -------------------------------------------------

            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_NUMERO.name()).target(EstadoProvidencia.ESPERANDO_RESPUESTA_INCULPADO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_CONFIRMA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_RESPUESTA_INCULPADO.name()).target(EstadoProvidencia.DN_RECIBIO_APELACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.UPD_NOTIFICO_A_INCULPADO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DN_RECIBIO_APELACION.name()).target(EstadoProvidencia.SECRETARIA_REVISA_APELACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.UPD_NOTIFICO_A_INCULPADO.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_APELACION.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNANDO_ABOGADO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DN_ENVIA_APELACION_A_SUBDIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_ASIGNANDO_ABOGADO.name()).target(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_ABOGADO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DN_ENVIA_APELACION_A_SUBDIRECCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_ASIGNACION_ABOGADO.name()).target(EstadoProvidencia.ABOGADO_ELABORA_INFORME_APELACION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ABOGADO_ELABORA_INFORME_APELACION.name()).target(EstadoProvidencia.SUB_DIRECCION_RECIBE_INFORME.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ENVIA_A_SUBDIRECCION_JURIDICA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_RECIBE_INFORME.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DN_A_INFORME.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_ENVIA_INFORME_A_DN.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DN_A_INFORME.name()).target(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_DN_INFORME.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SECRETARIA_REVISA_FIRMA_DN_INFORME.name()).target(EstadoProvidencia.SUB_DIRECCION_ASIGNANDO_A_UPD.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_ASIGNANDO_A_UPD.name()).target(EstadoProvidencia.UPD_REALIZA_RESOLUCION_RESUELVE_RECURSO_Y_MEMO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_REALIZA_RESOLUCION_RESUELVE_RECURSO_Y_MEMO.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ABOGADO_ELABORA_INFORME.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DN_FIRMA.name()).event(AccionesProvidencia.DN_ENVIA_A DGDP.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO.name()).target(EstadoProvidencia.UPD_RECIBE_NOTIFICACION_REALIZA_RESOLUCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNO_NUMERO_A_RESOLUCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_RECIBE_NOTIFICACION_REALIZA_RESOLUCION.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA_RESOLUCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION.name()).target(EstadoProvidencia.SUB_DIRECCION_REALIZO_MEMO_CONDUCTOR.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA_RESOLUCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.SUB_DIRECCION_REALIZO_MEMO_CONDUCTOR.name()).target(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.SUB_DIRECCION_FIRMA_VISA_RESOLUCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION.name()).target(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DN_FIRMA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_ASIGNANDO_NUMERO_A_RESOLUCION.name()).target(EstadoProvidencia.DGDP_RECIBE_NOTIFICACION_CONTRALORIA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DGDP_ASIGNA_NUMERO_A_RESOLUCION.name()).event(AccionesProvidencia.DGDP_ENIVA_A_CONTRALORIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.DGDP_RECIBE_NOTIFICACION_CONTRALORIA.name()).target(EstadoProvidencia.ELABORANDO_ALCANCE_O_RESOLUCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.DGDP_CONFIRMA_NOTIFICACION_CONTRALORIA.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.ELABORANDO_ALCANCE_O_RESOLUCION.name()).target(EstadoProvidencia.UPD_ARCHIVA_NOTIFICA_E_INFORMA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA.name())
//            .event(AccionesProvidencia.ELABORADO_ALCANCE_O_RESOLUCION.name())
            .and()
            .withExternal()
            .source(EstadoProvidencia.UPD_ARCHIVA_NOTIFICA_E_INFORMA.name()).target(EstadoProvidencia.APLICA_SANCION.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA_SUSPENCION_MULTA.name())
//            .event(AccionesProvidencia.NOTIFICAR_A_REMUNERACION.name())
            .and()

            .withExternal()
            .source(EstadoProvidencia.UPD_ARCHIVA_NOTIFICA_E_INFORMA.name()).target(EstadoProvidencia.DGDP_ARCHIVA.name())
            .event(AccionesProvidencia.CONTINUAR_FLUJO_APELA_SENSURA_DESTITUCION.name())
//            .event(AccionesProvidencia.NOTIFICAR_A_DGDP.name())
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
