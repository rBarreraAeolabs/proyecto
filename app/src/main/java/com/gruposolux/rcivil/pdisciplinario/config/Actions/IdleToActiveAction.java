//package com.gruposolux.rcivil.pdisciplinario.config.Actions;
//
//import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
//import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
//import com.gruposolux.rcivil.pdisciplinario.utils.ProvidenciaConstants;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.statemachine.StateContext;
//import org.springframework.statemachine.action.Action;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//
//@Component
//public class IdleToActiveAction implements Action<String, String> {
//
//    private final static Logger logger = LoggerFactory.getLogger(IdleToActiveAction.class);
//
//    @Override
//    public void execute(StateContext<String, String> context) {
//        Providencia providencia = (Providencia) context.getMessageHeader(ProvidenciaConstants.entityHeader);
//        if (providencia == null) {
//            logger.debug("Action: Wrong transition?");
//        } else {
//            logger.debug("Action: changing the idle entity to active.. {}", providencia);
//        }
//    }
////    public HashMap<String, Boolean> getActionsPermitted(){
////
////        if (providenciaDTO.getEstadoActual() == null)
////        {
////            return null;
////        }
////        HashMap<String, Boolean> actionsPermitted = new HashMap<>();
////        // Se agregan las acciones con un valor false de inicio.
////        // "reply" permite mostrar u ocultar el botón continuar para cambiar de estado.
////        // "goBackwards" permite mostrar u ocultar el botón que da pie a volver o rechazar a la etapa anterior.
////        // "watchTabRespuesta" permite mostrar u ocultar el tab que da pie a crear una respuesta.
////        // "asignarFiscal" permite mostrar u ocultar el botón que da pie a abrir un model y asignar el nombre del fiscal.
////        // "relacionarProvidencia" permite mostrar u ocultar el botón que da pie a relacionar una providencia.
////        // "numerarReferencia" permite mostrar u ocultar el botón que da pie a asignar el número de referencia.
////        // "tipoSolicitud" permite mostrar u ocultar el botón que da pie a asignar el tipo de solicitud.
////        actionsPermitted.put("reply", false);
////        actionsPermitted.put("goBackwards", false);
////        actionsPermitted.put("watchTabRespuesta", true);
////        actionsPermitted.put("asignarFiscal",false);
////        actionsPermitted.put("relacionarProvidencia",false);
////        actionsPermitted.put("numerarReferencia",false);
////        actionsPermitted.put("tipoSolicitud",false);
////
////        return actionsPermitted;
////    }
//}
