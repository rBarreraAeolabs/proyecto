package com.gruposolux.rcivil.pdisciplinario.service.listener;

import com.google.common.collect.Iterables;
import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoDerivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoDerivacion;
import com.gruposolux.rcivil.pdisciplinario.repository.DerivacionRepository;
import com.gruposolux.rcivil.pdisciplinario.service.*;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.events.PostCrearProvidenciaEvent;
import com.gruposolux.rcivil.pdisciplinario.service.events.PreCrearProvidenciaEvent;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.AdjuntoMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.GrupoMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.MovimientoProvidenciaMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Instant;
import java.util.*;

@Component
public class ProvidenciaEventsListener {

    private final Logger log = LoggerFactory.getLogger(ProvidenciaEventsListener.class);
//    private final UserService userService;
//    private final ProvidenciaService providenciaService;
//    private Locale locale = Locale.forLanguageTag("es");
//    private final PerfilService perfilService;
//    private final MovimientoProvidenciaService movimientoProvidenciaService;
//    private final DerivacionRepository derivacionRepository;
//    private final DocumentoService documentoService;
//    private final AdjuntoService adjuntoService;
//    private final AdjuntoMapper adjuntoMapper;
//    private final MovimientoProvidenciaMapper movimientoProvidenciaMapper;
//    private final GrupoService grupoService;
//    private final GrupoMapper grupoMapper;
//    private final DerivacionService derivacionService;
//    private final UserMapper userMapper;

//    @Autowired
//    public ProvidenciaEventsListener(
//        UserService userService, ProvidenciaService providenciaService,
//        PerfilService perfilService, MovimientoProvidenciaService movimientoProvidenciaService,
//        DerivacionRepository derivacionRepository, DocumentoService documentoService, AdjuntoService adjuntoService,
//        AdjuntoMapper adjuntoMapper, MovimientoProvidenciaMapper movimientoProvidenciaMapper, GrupoService grupoService, GrupoMapper grupoMapper, DerivacionService derivacionService, UserMapper userMapper)
//    {
//        this.userService = userService;
//        this.providenciaService = providenciaService;
//        this.perfilService = perfilService;
//        this.movimientoProvidenciaService = movimientoProvidenciaService;
//        this.derivacionRepository = derivacionRepository;
//        this.documentoService = documentoService;
//        this.adjuntoService = adjuntoService;
//        this.adjuntoMapper = adjuntoMapper;
//        this.movimientoProvidenciaMapper = movimientoProvidenciaMapper;
//        this.grupoService = grupoService;
//        this.grupoMapper = grupoMapper;
//        this.derivacionService = derivacionService;
//        this.userMapper = userMapper;
//    }

    @EventListener
    public void handlePreCrearProvidenciaEvent(PreCrearProvidenciaEvent event)
    {
//        ProvidenciaDTO providenciaDTO = event.getProvidenciaDTO();
//
//        this.registrarMovimientoProvidencia(providenciaDTO, providenciaDTO.getEstadoActual(), EstadoProvidencia.ESTADO_1,
//            this.userService.getCurrentUser().getId(), "primera derivación.");
    }

    @EventListener
    public void handlePostCrearProvidenciaEvent(PostCrearProvidenciaEvent event)
    {
//        // recibe providencia enviada desde el metodo save
//        ProvidenciaDTO providenciaDTO = event.getProvidenciaDTO();
//
//        List<DerivacionDTO> derivacionDTOs = this.derivacionService.findLastByProvidencia(providenciaDTO);
//        DerivacionDTO derivacionAnteriorDTO = null;
//
//        if (derivacionDTOs != null && derivacionDTOs.size() > 0) {
//            derivacionAnteriorDTO = derivacionDTOs.get(0);
//        }
//
//        EstadoProvidencia estadoActualProvidencia = providenciaDTO.getEstadoActual();
//
//        // recibe currentUser enviada desde el metodo save
//        User currentUser = event.getUser();
//
//        // observacion de derivacion
//        String observacion = event.getDerivacionDTO().getObservacion();
//
//        //derivadoPor Grupo
//        Grupo derivadoPorGrupo = event.getUser().getPerfil().getGrupo();
//
//        Set<MovimientoProvidenciaDTO> movimientoDeLaProvidencia = this.movimientoProvidenciaService.getAllByIdProvidencia(providenciaDTO);
//        List<MovimientoProvidenciaDTO> listaMovimientos = new ArrayList<>(movimientoDeLaProvidencia);
//        MovimientoProvidenciaDTO lastMovimientos = Iterables.getLast(listaMovimientos, null);
//        EstadoProvidencia estadoAnterior = null;
//        if (lastMovimientos != null && lastMovimientos.getEstadoAnterior() != null) {
//            estadoAnterior = lastMovimientos.getEstadoAnterior();
//        }
//
//
//        User usuarioAnterior = null;
//        if (derivacionAnteriorDTO != null && derivacionAnteriorDTO.getDerivadoPorUsuarioId() != null)
//        {
//            usuarioAnterior = this.userMapper.userFromId(derivacionAnteriorDTO.getDerivadoPorUsuarioId());
//        }
//
//        Perfil perfilAnterior = null;
//        if (usuarioAnterior != null) {
//            perfilAnterior = usuarioAnterior.getPerfil();
//        }
//
//        // crea instancia de derivación e ingresa los datos
//        DerivacionDTO derivacionDTO = new DerivacionDTO();
//        Optional<PerfilDTO> perfilAResponder;
//        TipoDerivacion tipoDerivacion = null;
//
//        switch (estadoActualProvidencia) {
//            case CREADA:
//                // RESPONDE GESTOR DOCUMENTAL
//                perfilAResponder = perfilService.findOne((long) 6);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SECREATARIA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_1_5);
//                derivacionDTO.setDerivadoAGrupoId(2L);
//                break;
//            case ESTADO_1_5:
//                // RESPONDE SECREATARIA SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 3);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SECREATARIA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_2);
//                derivacionDTO.setDerivadoAGrupoId(2L);
//                break;
//            case ESTADO_2:
//                // RESPONDE SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 7);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_3);
//                derivacionDTO.setDerivadoAGrupoId(2L);
//                break;
//            case ESTADO_3:
//                // RESPONDE UDP
//                perfilAResponder = perfilService.findOne((long) 9);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_A_UPD;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_3_5);
//                derivacionDTO.setDerivadoAGrupoId(3L);
//                break;
//            case ESTADO_3_5:
//                // RESPONDE SECREATARIA SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 3);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SECREATARIA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_4);
//                break;
//            case ESTADO_4:
//                // RESPONDE SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 7);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_5);
//                break;
//            case DEVUELTA:
//                // RESPONDE PERFIL ANTERIOR Y SETEA ESTADO ANTERIOR
//                perfilAResponder = perfilService.findOne(perfilAnterior.getId());
//                tipoDerivacion = TipoDerivacion.DEVUELVE;
//                providenciaDTO.setEstadoActual(estadoAnterior);
//                break;
//            case ESTADO_5:
//                // RESPONDE DIRECCION
//                perfilAResponder = perfilService.findOne((long) 8);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_DIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_6);
//                break;
//            case ESTADO_6:
//                // RESPONDE DIRECCION
//                perfilAResponder = perfilService.findOne((long) 8);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_DIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_7);
//                break;
//            case ESTADO_7:
//                // RESPONDE DGDP
//                perfilAResponder = perfilService.findOne((long) 10);
//                tipoDerivacion = TipoDerivacion.DGDP;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_8);
//                break;
//            case ESTADO_8:
//                // RESPONDE UDP
//                perfilAResponder = perfilService.findOne((long) 9);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_A_UPD;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_8_5);
//                break;
//            case ESTADO_8_5:
//                // RESPONDE SECREATARIA SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 3);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SECREATARIA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_9);
//                break;
//            case ESTADO_9:
//                // RESPONDE SUBDIRECCION
//                perfilAResponder = perfilService.findOne((long) 7);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_SUBDIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_10);
//                break;
//            case ESTADO_10:
//                // RESPONDE UDP
//                perfilAResponder = perfilService.findOne((long) 9);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_A_UPD;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_11);
//                break;
//            case ESTADO_11:
//                // ACEPTA O RECHAZA FISCAL
//                perfilAResponder = perfilService.findOne((long) 4);
//                tipoDerivacion = TipoDerivacion.ENTREGA_FISCAL;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_12);
//                break;
//            case FISCAL_ACEPTA_PROVIDENCIA:
//                // SI ACEPTA PROVIDENCIA (se autoasigna para para iniciar investigacion)
//                perfilAResponder = perfilService.findOne((long) 4);
//                tipoDerivacion = TipoDerivacion.RESPONDER;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_13);
//                break;
//            case FISCAL_RECHAZA_PROVIDENCIA:
//                // SI RECHAZADA PROVIDENCIA (se regresa solicitud al principio al director nacional)
//                perfilAResponder = perfilService.findOne((long) 8);
//                tipoDerivacion = TipoDerivacion.PROVIDENCIA_ENVIADA_DIRECCION;
//                providenciaDTO.setEstadoActual(EstadoProvidencia.ESTADO_1);
//                break;
//            default:
//                throw new NotImplementedException();
//        }
//
//        derivacionDTO.setObservacion(observacion);
//        derivacionDTO.setProvidenciaId(providenciaDTO.getId());
//        derivacionDTO.setEstado(EstadoDerivacion.NO_LEIDO);
//        derivacionDTO.setDerivadoPorUsuarioId(currentUser.getId());
//        derivacionDTO.setDerivadoPorGrupoId(derivadoPorGrupo.getId());
//        derivacionDTO.setDocumentosDtos(event.getDerivacionDTO().getDocumentosDtos());
//        derivacionDTO.setTipo(tipoDerivacion);
//        derivacionDTO.setDerivadoAUsuarioId(this.userService.getCurrentUser().getId());
//        derivacionDTO.setFechaDerivacion(Instant.now());
//
//        this.providenciaService.save(providenciaDTO);
//        this.derivacionService.save(derivacionDTO);
//
//
//        //Se envía al usuario asignado por defecto, que debe tener cada grupo
//        this.registrarMovimientoProvidencia(providenciaDTO, providenciaDTO.getEstadoActual(), estadoActualProvidencia,
//            this.userService.getCurrentUser().getId(), observacion);
    }

    private void registrarMovimientoProvidencia(ProvidenciaDTO providenciaDTO, EstadoProvidencia estadoProvidenciaActual,
                                                EstadoProvidencia estadoProvidenciaAnterior, Long userReceiveId, String comentario)
    {
//        MovimientoProvidenciaDTO movimientoProvidenciaDTO = new MovimientoProvidenciaDTO();
//
//        if (estadoProvidenciaAnterior != null) {
//            movimientoProvidenciaDTO.setEstadoAnterior(estadoProvidenciaAnterior);
//        }
//        movimientoProvidenciaDTO.setEstadoNuevo(estadoProvidenciaActual);
//        movimientoProvidenciaDTO.setFecha(Instant.now());
//        movimientoProvidenciaDTO.setProvidenciaId(providenciaDTO.getId());
//        if (userReceiveId != null)
//        {
//            movimientoProvidenciaDTO.setUserId(userReceiveId);
//        }
//        movimientoProvidenciaDTO.setComentario(comentario);
//
//        movimientoProvidenciaDTO = this.movimientoProvidenciaService.save(movimientoProvidenciaDTO);
//        MovimientoProvidencia movimientoProvidencia = this.movimientoProvidenciaMapper.toEntity(movimientoProvidenciaDTO);
//
//        if (providenciaDTO.getAdjuntos() != null && providenciaDTO.getAdjuntos().size() > 0)
//        {
//            this.registrarMovimientoOnAdjuntos(movimientoProvidencia, providenciaDTO);
//        }
    }

    private void registrarMovimientoOnDocumentos(MovimientoProvidencia movimientoProvidencia, Providencia providencia)
    {

    }

    private void registrarMovimientoOnAdjuntos(MovimientoProvidencia movimientoProvidencia, ProvidenciaDTO providenciaDTO)
    {
//        for (Iterator<AdjuntoDTO> it = providenciaDTO.getAdjuntos().iterator(); it.hasNext();)
//        {
//            AdjuntoDTO adjuntoDTO = it.next();
//            adjuntoDTO.setMovimientoProvidenciaId(movimientoProvidencia.getId());
//            adjuntoDTO.setProvidenciaId(providenciaDTO.getId());
//            this.adjuntoService.save(adjuntoDTO);
//        }
    }

}
