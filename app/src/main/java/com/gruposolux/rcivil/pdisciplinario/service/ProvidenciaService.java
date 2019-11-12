package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.*;
import com.gruposolux.rcivil.pdisciplinario.repository.*;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.*;
import com.gruposolux.rcivil.pdisciplinario.storage.AlfrescoStorageService;
//import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Providencia.
 */
@Service
@Transactional
public class ProvidenciaService {

    private final Logger log = LoggerFactory.getLogger(ProvidenciaService.class);
    private final ProvidenciaRepository providenciaRepository;
    private final ProvidenciaMapper providenciaMapper;
    private final AdjuntoService adjuntoService;
    private final AdjuntoMapper adjuntoMapper;
    private final ApplicationEventPublisher publisher;
    private final UserService userService;
    private final DerivacionRepository derivacionRepository;
    private final NotificacionInBrowserRepository notificacionInBrowserRepository;
    private final GrupoService grupoService;
    private final GrupoMapper grupoMapper;
    private final DocumentoMapper documentoMapper;
    private final DocumentoService documentoService;
    private final MovimientoProvidenciaService movimientoProvidenciaService;
    private final PlantillaService plantillaService;
    private final EntidadService entidadService;
    private final EntidadMapper entidadMapper;
    private final InvestigacionSumariaService investigacionSumariaService;
    private final SumarioAdministrativoService sumarioAdministrativoService;
    private final InvestigacionSumariaMapper investigacionSumariaMapper;
    private final SumarioAdministrativoMapper sumarioAdministrativoMapper;
    private final ProvidenciaStateMachineService providenciaStateMachineService;
    private final StateMachine stateMachine;
    private final UserRepository userRepository;
    private final PlazosHastaService plazosHastaService;
    private final DocumentoRepository documentoRepository;
    private final AdjuntoRepository adjuntoRepository;
    @Autowired
    private final AlfrescoStorageService alfrescoStorageService;


    public ProvidenciaService(
        ProvidenciaRepository providenciaRepository,
        ProvidenciaMapper providenciaMapper,
        AdjuntoMapper adjuntoMapper,
        ApplicationEventPublisher publisher,
        UserService userService,
        AdjuntoService adjuntoService,
        DerivacionRepository derivacionRepository,
        NotificacionInBrowserRepository notificacionInBrowserRepository,
        GrupoService grupoService,
        GrupoMapper grupoMapper,
        DocumentoMapper documentoMapper,
        DocumentoService documentoService,
        MovimientoProvidenciaService movimientoProvidenciaService,
        PlantillaService plantillaService,
        EntidadService entidadService,
        EntidadMapper entidadMapper,
        InvestigacionSumariaService investigacionSumariaService,
        SumarioAdministrativoService sumarioAdministrativoService,
        InvestigacionSumariaMapper investigacionSumariaMapper,
        SumarioAdministrativoMapper sumarioAdministrativoMapper,
        AlfrescoStorageService alfrescoStorageService,
        ProvidenciaStateMachineService providenciaStateMachineService,
        StateMachine stateMachine,
        UserRepository userRepository,
        PlazosHastaService plazosHastaService,
        DocumentoRepository documentoRepository, AdjuntoRepository adjuntoRepository) {
        this.providenciaRepository = providenciaRepository;
        this.providenciaMapper = providenciaMapper;
        this.adjuntoMapper = adjuntoMapper;
        this.publisher = publisher;
        this.userService = userService;
        this.adjuntoService = adjuntoService;
        this.derivacionRepository = derivacionRepository;
        this.notificacionInBrowserRepository = notificacionInBrowserRepository;
        this.grupoService = grupoService;
        this.grupoMapper = grupoMapper;
        this.documentoMapper = documentoMapper;
        this.documentoService = documentoService;
        this.movimientoProvidenciaService = movimientoProvidenciaService;
        this.plantillaService = plantillaService;
        this.entidadService = entidadService;
        this.entidadMapper = entidadMapper;
        this.investigacionSumariaService = investigacionSumariaService;
        this.sumarioAdministrativoService = sumarioAdministrativoService;
        this.investigacionSumariaMapper = investigacionSumariaMapper;
        this.sumarioAdministrativoMapper = sumarioAdministrativoMapper;
        this.alfrescoStorageService = alfrescoStorageService;
        this.providenciaStateMachineService = providenciaStateMachineService;
        this.stateMachine = stateMachine;
        this.userRepository = userRepository;
        this.plazosHastaService = plazosHastaService;

        this.documentoRepository = documentoRepository;
        this.adjuntoRepository = adjuntoRepository;
    }

    /**
     * Save a providencia.
     *
     * @param providenciaDTO the entity to save
     * @return the persisted entity
     */
    public ProvidenciaDTO save(ProvidenciaDTO providenciaDTO) {
        log.debug("Respuesta Save Providencia : {}", providenciaDTO);
        Providencia providencia = providenciaMapper.toEntity(providenciaDTO);
        EstadoProvidencia requisitoEstado = this.newState(providencia, AccionesProvidencia.CREAR_PROVIDENCIA);
        EstadoProvidencia subEtapa = this.determinaSubEtapa(requisitoEstado, EstadoProvidencia.NUEVA_PROVIDENCIA);
        EstadoProvidencia etapa = this.determinaEtapa(requisitoEstado);
        String estadoProviCompleto = this.concatenarEstado(requisitoEstado, subEtapa, etapa);
        String estadoInicial = this.determinaEstadoInicial(EstadoProvidencia.NUEVA_PROVIDENCIA);
        providencia.setFechaCreacion(Instant.now());
        providencia.setRequisito(requisitoEstado);
        providencia.setSubEtapa(subEtapa);
        providencia.setEtapa(etapa);
        providencia.setStandby(false);
        log.debug(" Estado completo es  " + estadoProviCompleto);
        log.debug(" EstadoInicial completo es   " + estadoInicial);
        providencia.setEstadoActual(estadoProviCompleto);


        // si la providencia tiene un tipo (SUMARIO y/o ADMINISTRATIVO) se debe instanciar ese tipo
        insertaTipoProvidencia(providencia);

        providencia = providenciaRepository.save(providencia);

        // Verifica que existan Adjuntos y los guarda
        verificaAdjuntosYGuarda(providenciaDTO, providencia);

        //Settear el ID de la providencia creada en los adjuntos debido a que providencia manda en la relación.
        List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(providencia, providenciaDTO.getAdjuntos());

        providenciaDTO = this.providenciaMapper.toDto(providencia);

        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
            providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
        }

        Grupo groupAnswer = this.determineGroupAnswer(providencia);

        Derivacion derivacion = this.registerDerivation("desde el director nacional", providencia, null, groupAnswer);

        // si la creacion de la providecia se debe notificar se debe descomentar
//        this.registryNotificacion("desde el director nacional", groupAnswer);

        this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, providencia.getId(), derivacion.getObservacion(),
            null, providenciaDTO.getAdjuntos(), "Derivado");

               this.calcularPlazos(providencia);

        return providenciaDTO;
    }

    public void calcularPlazos(Providencia providencia){
        EstadoProvidencia requisito = providencia.getRequisito();
        log.debug("estos son los plazos");
        switch (requisito){
             case FORMULA_CARGOS:
                plazosHastaService.dias(providencia.getId(),2);
                break;

            case FORMULA_CARGOS_Y_NOTIFICA:
                plazosHastaService.dias(providencia.getId(),2);
                break;
            case PRORROGA1_CREADA:
                plazosHastaService.dias(providencia.getId(),20);
                break;
            case PRORROGA2_CREADA:
                plazosHastaService.dias(providencia.getId(),20);
                break;
            case INVESTIGACION:
                plazosHastaService.dias(providencia.getId(),20);
                break;


        }

    }

    //  Metodo que verifica que existan Adjuntos y los guarda
    private void verificaAdjuntosYGuarda(ProvidenciaDTO providenciaDTO, Providencia providencia) {
        if (providenciaDTO.getAdjuntos().size() > 0) {
            providencia.setAdjuntos(providenciaDTO.getAdjuntos().stream().map(this.adjuntoMapper::toEntity)
                .collect(Collectors.toSet()));
            this.alfrescoStorageService.moveToArchivosFolder(providenciaDTO.getAdjuntos().
                stream().map(AdjuntoDTO::getHash).collect(Collectors.toList()), providencia);
        }
    }

    // Metodo que verifica el tipo (SUMARIO y/o ADMINISTRATIVO) y lo instancia en la Providencia
    private void insertaTipoProvidencia(Providencia providencia) {
        if (providencia.getTipo() != null) {

            if (providencia.getTipo().equals(TipoProvidencia.INVESTIGACION_SUMARIA)) {
                InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();
                InvestigacionSumariaDTO investigacionSumariaDTO = this.investigacionSumariaService
                    .save(this.investigacionSumariaMapper.toDto(investigacionSumaria));

                investigacionSumaria = this.investigacionSumariaMapper.toEntity(investigacionSumariaDTO);
                providencia.setInvestigacionSumaria(investigacionSumaria);

            } else if (providencia.getTipo().equals(TipoProvidencia.SUMARIO_ADMINISTRATIVO)) {

                SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();
                SumarioAdministrativoDTO sumarioAdministrativoDTO = this.sumarioAdministrativoService
                    .save(this.sumarioAdministrativoMapper.toDto(sumarioAdministrativo));

                sumarioAdministrativo = this.sumarioAdministrativoMapper.toEntity(sumarioAdministrativoDTO);
                providencia.setSumarioAdministrativo(sumarioAdministrativo);
            }
        }
    }

    private String concatenarEstado(EstadoProvidencia requisitoEstado, EstadoProvidencia subEtapa, EstadoProvidencia etapa) {

        String estadoCompleto = null;
        estadoCompleto = etapa + " - " + subEtapa + " - " + requisitoEstado;
        return estadoCompleto;
    }

    private String determinaEstadoInicial(EstadoProvidencia requisito) {

        log.debug("Entrando a DeterminarEstadoInical" +  requisito);
        String estadoInicial = null;
        EstadoProvidencia requisitoInicial;
        EstadoProvidencia etapaInicial;
        EstadoProvidencia subEtapaInicial;

        if (requisito == EstadoProvidencia.PROVIDENCIA_SELECCION_FISCAL) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial, requisito);

        } else if (requisito == EstadoProvidencia.PROVIDENCIA_REABRIR || requisito == EstadoProvidencia.PROVIDENCIA_SANCION
            || requisito == EstadoProvidencia.PROVIDENCIA_SOBRECEDER || requisito == EstadoProvidencia.PROVIDENCIA_ABSOLVER) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial, requisito);

        } else if (requisito == EstadoProvidencia.PROVIDENCIA_SANCION_APELO || requisito == EstadoProvidencia.PROVIDENCIA_SANCION_NO_APELO) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial, requisito);

            // cuando se defina de que eta nace prorroga cambiar nuevaprovidencia
        } else if (requisito == EstadoProvidencia.PROVIDENCIA_PRORROGA || requisito == EstadoProvidencia.PROVIDENCIA_PRORROGA_2) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial, requisito);

        } else {
            requisitoInicial = requisito;
            subEtapaInicial = this.determinaSubEtapa(requisito, requisito);
            etapaInicial = this.determinaEtapa(requisito);
        }
        estadoInicial = this.concatenarEstado(requisitoInicial, subEtapaInicial, etapaInicial);

        log.debug("Saliendo de DeterminarEstadoInical" +  estadoInicial);
        return estadoInicial;
    }

    /**
     * Determina la SubEtapa en la que esta la providencia partiendo de los requesitosEstados.
     *
     * @param requisitoEstado requisito o Estado en el que se encuentra la providenia
     * @return subEtapa
     */
    private EstadoProvidencia determinaSubEtapa(EstadoProvidencia requisitoEstado, EstadoProvidencia etapa) {

        log.debug(" determinar subEtapa  con requisito " + requisitoEstado);
        EstadoProvidencia subEtapa = null;
        EstadoProvidencia requisito = requisitoEstado;

        switch (requisito) {

            case NUEVA_PROVIDENCIA:
            case PROVIDENCIA_CREADA:
            case GESTOR_DOCUMENTAL_ASIGNA_NUMERO:
            case SUB_DIRECCION_DEBE_ASIGNAR:

                switch (etapa){
                    case NUEVA_PROVIDENCIA:
                        subEtapa = EstadoProvidencia.NUEVA_PROVIDENCIA;
                        break;
                    case PROVIDENCIA_SELECCION_FISCAL:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_SELECCION_FISCAL;
                        break;
                    case PROVIDENCIA_SOBRECEDER:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_SOBRESEER;
                        break;
                    case PROVIDENCIA_ABSOLVER:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_ABSOLVER;
                        break;
                    case PROVIDENCIA_REABRIR:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_REABRIR;
                        break;
                    case PROVIDENCIA_SANCION:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_SANCION;
                        break;
                    case PROVIDENCIA_SANCION_APELO:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_SANCION_APELO;
                        break;
                    case PROVIDENCIA_SANCION_NO_APELO:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_SANCION_NO_APELO;
                        break;
                    case PROVIDENCIA_PRORROGA:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_PRORROGA;
                        break;
                    case PROVIDENCIA_PRORROGA_2:
                        subEtapa = EstadoProvidencia.CREADA_PROVIDENCIA_PRORROGA_2;
                        break;
                }
                break;
            case SECRETARIA_REVISA_NUMERO:
            case SECRETARIA_REVISA_ASIGNACION:
            case SECRETARIA_REVISA_FIRMA:
            case SECRETARIA_REVISA_RESOLUCION_Y_MEMO:
            case SECRETARIA_REVISA_NOTIFICACION:
            case SECRETARIA_REVISA_FIRMA_NOTIFICACION:
            case SECRETARIA_REVISA_RESOLUCION_EXCENTA:
            case SECRETARIA_REVISA_FIRMA_RESOLUCION_EXCENTA:
                subEtapa = EstadoProvidencia.REVISION_SECRETARIA;
                break;
            case UPD_REDACTA_RESOLUCION_Y_MEMO:
            case ENVIAR_A_SUB_DIRRECION_JURIDICA:
            case UPD_REDACTA_RESOLUCION_EXCENTA_Y_MEMO:
                subEtapa = EstadoProvidencia.RESOLUCION_Y_MEMO;
                break;
            case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION:
            case DGD_DESPACHA_A_DN:
                subEtapa = EstadoProvidencia.FIRMA_SUBDIRECCION_JURIDICA;
                break;
            case ESPERANDO_FIRMA_DEL_DN:
                subEtapa = EstadoProvidencia.FIRMA_DIRECTOR_NACIONAL;
                break;
            case DGDP_ASIGNANDO_NUMERO:
            case CONTRALORIA_NOTIFICADA:
                subEtapa = EstadoProvidencia.NUMERO_DGDP;
                break;
            case DGD_RECEPCIONA:
            case UPD_ELABORA_NOTIFICACION_VISTA_FISCAL:
            case DGD_DESPACHA_NOTIFICACION_FISCAL:
            case ENVIADO_A_SUBDIRECCION_JURIDICA:
            case ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION:
            case UPD_NOTIFICA_FISCAL:
            case FISCAL_NOTIFICADO:
                subEtapa = EstadoProvidencia.ELABORACION_NOTIFICACION_FISCAL;
                break;

            case FISCAL_RECHAZO:
            case FISCAL_REDACTA_MEMO:
            case INVESTIGACION: //  le da una sub etapa al requisito
                subEtapa = EstadoProvidencia.DA_INICIO;
                break;
            case FORMULA_CARGOS:
            case FORMULA_CARGOS_Y_NOTIFICA:
            case INCULPADO_ENVIA_MEMO:
            case INCULPADO_NO_ENVIA_MEMO:
            case FISCAL_REMITE_EXPEDIENTE:
            case REMITE_VISTA_FISCAL:
                if (etapa==EstadoProvidencia.INVESTIGACION){
                    subEtapa = EstadoProvidencia.DA_INICIO;
                    break;
                }

            case ENVIAR_SUMARIO_A_SUB_DIRECCION:
            case DGD_DESPACHA_SUMARIO:
            case SECRETARIA_REVISA_SUMARIO:
            case SUB_DIRECCION_ASIGNA_ABOGADO:
            case SECRETARIA_REVISA_SUMARIO_Y_NOTIFICA_A_ABOGADO:
            case ABOGADO_ELABORA_INFORME:
                subEtapa = EstadoProvidencia.ASIGNACION_SJ;
                break;

            case SI_DE_ACUERDO:
            case NO_REABRE:
            case NO_PROPONE:
            case SECRETARIA_REVISA_INFORME:
            case SUB_DIRECCION_REVISA_INFORME:
            case SECRETARIA_DESPACHA_INFORME:
            case DGD_DESPACHA_SUMARIO_COMPLETO:
                subEtapa = EstadoProvidencia.ABOGADO_RESPONDE;
                break;

            case FORMULA_CARGOS_TERMINO_PROBATORIO:
            case APELACION_INCULPADO:
            case UPD_ELABORA_NOTIFICACION:
            case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_NOTIFICACION:
            case UPD_NOTIFICA_A_INCULPADO:
                subEtapa = EstadoProvidencia.FISCAL;
                break;
            case DN_RECIBE_SUMARIO_COMPLETO:
            case REVISION_SUMARIO_COMPLETO:
            case DGD_RECEPCIONA_SUMARIO:
                subEtapa = EstadoProvidencia.VISTA_FISCAL;
                break;
            case UPD_REALIZA_MEMO:
                subEtapa = EstadoProvidencia.MEMO;
                break;
            case PETICION_APELACION:
                subEtapa = EstadoProvidencia.APELACION_SOLICITADA;
                break;
//            case PETICION_NO_APELACION:
//                subEtapa = EstadoProvidencia.NO_APELACION_SOLICTADA;
//                break;
            case FOLIO_Y_ARCHIVA:
                subEtapa = EstadoProvidencia.FINALIZADO;
                break;
            case CERTIFICACION_NO_APELO:
            case UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA:
                subEtapa = EstadoProvidencia.NO_APELO;
                break;
            case PETICION_PRORROGA:
            case PETICION_PRORROGA_2:
                subEtapa = EstadoProvidencia.PRORROGA_SOLICITADA;
                break;
            case CONTRALORIA_NOTIFICO_DGDP:
            case UPD_NOTIFICADO:
            case UPD_REALIZO_MEMO_Y_NOTIFICO:
                subEtapa = EstadoProvidencia.TOMA_DE_RAZON_O_REGISTRA;
                break;
            case RECEPCION_RESPUESTA_CONTRALORIA:
                subEtapa = EstadoProvidencia.REPRESENTA;
                break;
            case DGDP_REGISTRA_FIN:
                subEtapa = EstadoProvidencia.NOTIFICACIONES;
                break;
            case ESPERANDO_RESPUESTA_INCULPADO:
            case DN_RECIBIO_APELACION:
                subEtapa = EstadoProvidencia.APELACION;
                break;
            case SECRETARIA_REVISA_APELACION:
            case SUB_DIRECCION_ASIGNANDO_ABOGADO:
            case SECRETARIA_REVISA_ASIGNACION_ABOGADO:
            case ABOGADO_ELABORA_INFORME_APELACION:
            case SUB_DIRECCION_RECIBE_INFORME:
            case ESPERANDO_FIRMA_DN_A_INFORME:
                subEtapa = EstadoProvidencia.INFORME_ABOGADO;
                break;
            case SECRETARIA_REVISA_FIRMA_DN_INFORME:
            case SUB_DIRECCION_ASIGNANDO_A_UPD:
            case UPD_REALIZA_RESOLUCION_RESUELVE_RECURSO_Y_MEMO:
            case UPD_RECIBE_NOTIFICACION_REALIZA_RESOLUCION:
            case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION:
            case SUB_DIRECCION_REALIZO_MEMO_CONDUCTOR:
            case ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION:
                subEtapa = EstadoProvidencia.RESOLUCION_DE_RECURSO;
                break;
            case DGDP_ASIGNANDO_NUMERO_A_RESOLUCION:
            case DGDP_RECIBE_NOTIFICACION_CONTRALORIA:
                subEtapa = EstadoProvidencia.NUMERO_DGDP_NOTIFICACION;
                break;
            case ELABORANDO_ALCANCE_O_RESOLUCION:
            case UPD_ARCHIVA_NOTIFICA_E_INFORMA:
                subEtapa = EstadoProvidencia.ALCANCE_O_RESOLUCION;
                break;
            case APLICA_SANCION:
                subEtapa = EstadoProvidencia.SUSPENSION_MULTA;
                break;
            case DGDP_ARCHIVA:
                subEtapa = EstadoProvidencia.SENSURA_DESTITUCION;
                break;
        }
        return subEtapa;
    }

    /**
     * Determina la Etapa partiendo de la subEtapa
     *
     * @param requisitoActual requisitoActual en la que se encuentra la providenciaNueva o la ProvidenciaMadre
     * @return etapa correspondiente al requisitoActual
     */
    private EstadoProvidencia determinaEtapa(EstadoProvidencia requisitoActual) {
        log.debug(" determinar Etapa  con REQUISITO " + requisitoActual);
        EstadoProvidencia etapa = null;
        EstadoProvidencia requisito = requisitoActual;

        switch (requisito) {
            // Setea variable Etapa segun la subEtapa en que se encuentre
            case NUEVA_PROVIDENCIA:
            case PROVIDENCIA_CREADA:
                etapa = EstadoProvidencia.NUEVA_PROVIDENCIA;
                break;
            case FISCAL_RECHAZO:
                etapa = EstadoProvidencia.PROVIDENCIA_SELECCION_FISCAL;
                break;
            case DGD_DESPACHA_NOTIFICACION_FISCAL:
                etapa = EstadoProvidencia.INVESTIGACION;
                break;

        }
        log.debug(" La Etapa es " + etapa);
        return etapa;
    }

    /**
     * @CreatedBy Ruben Barrera
     * @param requisitoActual
     * @param etapaActual
     * @return
     *
     * determinar etapa en el flujo
     *
     */

    private EstadoProvidencia determinaEtapaenFlujo(EstadoProvidencia subEtapaActual,EstadoProvidencia requisitoActual, EstadoProvidencia etapaActual) {
        log.debug(" determinar Etapa en flujo  con REQUISITO " + requisitoActual);
        EstadoProvidencia etapa = etapaActual;
        EstadoProvidencia requisito = requisitoActual;
        EstadoProvidencia subEtapa = subEtapaActual;
        switch (requisito) {
            // Setea variable Etapa segun la subEtapa en que se encuentre
            case INVESTIGACION:
                etapa = EstadoProvidencia.INVESTIGACION;
                break;
            case FORMULA_CARGOS:
                if (subEtapa== EstadoProvidencia.DA_INICIO){
                    etapa = EstadoProvidencia.INVESTIGACION;
                }
                break;
        }
        switch (subEtapaActual){
            case ASIGNACION_SJ:
            case ABOGADO_RESPONDE:
                etapa = EstadoProvidencia.REVISION_SUMARIO;
                break;
        }

        log.debug(" La Etapa en el flujo  es " + etapa);
        return etapa;
    }

    @Transactional
    public ProvidenciaDTO update(ProvidenciaDTO providenciaDTO, Set<AdjuntoDTO> adjuntoDTOs) {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);

        log.debug("Dentro del update , id de la madre es " + providencia.getProvidencia_madre_id());

        // si la providencia tiene un tipo, se debe; instanciar ese tipo y relacionarlo con la providencia
        if (providencia.getTipo() != null) {
            if (providencia.getTipo().equals(TipoProvidencia.INVESTIGACION_SUMARIA) && providencia.getInvestigacionSumaria() == null) {
                InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();
                InvestigacionSumariaDTO investigacionSumariaDTO = this.investigacionSumariaService
                    .save(this.investigacionSumariaMapper.toDto(investigacionSumaria));

                investigacionSumaria = this.investigacionSumariaMapper.toEntity(investigacionSumariaDTO);

                providencia.setInvestigacionSumaria(investigacionSumaria);
            } else if (providencia.getTipo().equals(TipoProvidencia.SUMARIO_ADMINISTRATIVO) && providencia.getSumarioAdministrativo() == null) {
                SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();
                SumarioAdministrativoDTO sumarioAdministrativoDTO = this.sumarioAdministrativoService
                    .save(this.sumarioAdministrativoMapper.toDto(sumarioAdministrativo));

                sumarioAdministrativo = this.sumarioAdministrativoMapper.toEntity(sumarioAdministrativoDTO);
                providencia.setSumarioAdministrativo(sumarioAdministrativo);
            }
        }

        this.providenciaRepository.save(providencia);

        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
            //Settear el ID de la providencia creada en los adjuntos debido a que providencia manda en la relación.
            List<AdjuntoDTO> adjuntos = this.setIdProvidenciaOnAdjuntos(providencia, adjuntoDTOs);

            providenciaDTO = this.providenciaMapper.toDto(providencia);
            providenciaDTO.setAdjuntos(adjuntos.stream().collect(Collectors.toSet()));
        }
        log.debug("saliendo del update " + providenciaDTO);
        return providenciaDTO;
    }


    @Transactional(readOnly = true)
    public Optional<DetalleProvidenciaDTO> findOneDetalle(Long id) {
        log.debug("resultado del findOneDetalle: {}", id);

        return providenciaRepository.findById(id).map(providencia -> {
            Integer sumaAdjuntos =adjuntoRepository.Contar(providencia.getId())+documentoRepository.Contar(providencia.getId());

            log.debug("ruben-cantidad adjuntos: {}", sumaAdjuntos);
//            this.providenciaMapper
//                .toDto(this.providenciaRepository.getOne (providencia.getId())
//           );

            return new DetalleProvidenciaDTO(
                providencia.getId(),
                providencia.getNumeroReferencia(),
                providencia.getNumeroProvidencia(),
                providencia.getNumeroDgdp(),
                providencia.getNumeroDgd(),
                providencia.getEstadoActual(),
                providencia.getEtapa(),
                providencia.getSubEtapa(),
                providencia.getRequisito(),
                providencia.getTipo(),
                providencia.getComentario(),
                providencia.getFechaSolicitud(),
                providencia.getFechaCreacion(),
                providencia.getInstrucciones(),
//                providencia.getSumarioAdministrativo().getId(),
//                providencia.getInvestigacionSumaria().getId(),
                providencia.getFechaHasta(),
                providencia.getRunSolicitante(),
                providencia.getNombreSolicitante(),
                providencia.getRunImplicado(),
                providencia.getNombreImplicado(),
                providencia.getNombreFiscalAsignado(),
                providencia.getProvidencia_madre_id(),
                providencia.getStandby(),
                sumaAdjuntos);
        });
    }


    /**
     * Get all the providencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProvidenciaItemListDTO> findAll(Pageable pageable) {

        return providenciaRepository.findAll(pageable).map(providencia -> {

            String nombreGrupo = this.determineGroupAnswer(providencia).getNombre();

            MovimientoProvidencia  mp =  movimientoProvidenciaService.buscarPorId(providencia.getId());
            log.debug(" resumen resultado del movimiento: "+mp);

            Long sinresultados= Long.valueOf(0);
            /**
             * si agregas mas cosas al providenciaitemlistdto debes seguir el orden  del dto cuando muestres los datos aca
             */
            if (mp != null) {
                return new ProvidenciaItemListDTO(
                    providencia.getId(),
                    providencia.getFechaCreacion(),
                    providencia.getEstadoActual(),
                    nombreGrupo,
                    ChronoUnit.DAYS.between(providencia.getFechaCreacion(), Instant.now()),
                    ChronoUnit.DAYS.between(providencia.getFechaCreacion(),mp.getFecha()),
                    providencia.getFechaHasta(),
                    providencia.getStandby()

                );
            }else {
                return new ProvidenciaItemListDTO(
                    providencia.getId(),
                    providencia.getFechaCreacion(),
                    providencia.getEstadoActual(),
                    nombreGrupo,
                    ChronoUnit.DAYS.between(providencia.getFechaCreacion(), Instant.now()),
                    sinresultados,
                    providencia.getFechaHasta(),
                    providencia.getStandby()
                );
            }});
    }

    /**
     * Get all the Providencia with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ProvidenciaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return providenciaRepository.findAll(pageable).map(this.providenciaMapper::toDto);
    }

    /**
     * Get one providencia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ProvidenciaDTO> findOne(Long id) {
        log.debug("resultado del fynById: {}", id);
        return providenciaRepository.findById(id).map(this.providenciaMapper::toDto);
    }

    //
    // buscar por numero de referencia y traer todas las providencias ccon ese numero
    @Transactional(readOnly = true)
    public Set<ProvidenciaDTO> findAllNro() {
        return providenciaRepository.findByAllnroReferencia().stream().map(this.providenciaMapper::toDto).collect(Collectors.toSet());
    }
    /**
     * Delete the providencia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Providencia : {}", id);
        providenciaRepository.deleteById(id);
    }

    /*
     Método que permite settear el id de la providencia en los adjuntos que colaboran con el objeto.
     */
    private List<AdjuntoDTO> setIdProvidenciaOnAdjuntos(Providencia providencia,  Set<AdjuntoDTO> adjuntosDTOs) {
        if (adjuntosDTOs != null && adjuntosDTOs.size() > 0) {
            for (Iterator<AdjuntoDTO> it = adjuntosDTOs.iterator(); it.hasNext(); ) {
                AdjuntoDTO adjuntoDTO = it.next();
                if (adjuntoDTO.getProvidenciaId() == null) {
                    adjuntoDTO.setProvidenciaId(providencia.getId());
                    this.adjuntoService.save(adjuntoDTO);
                }
            }

            return this.adjuntoService.getByProvidencia(providencia).stream().map(this.adjuntoMapper::toDto)
                .collect(Collectors.toList());
        }

        return null;
    }

    /*
     Método que permite registrar una derivación.
     */

    private Derivacion registerDerivation(String observacion, Providencia providencia, User derivadoAUsuario, Grupo derivadoAGrupo) {

        Derivacion derivacion = new Derivacion();

        derivacion.setObservacion(observacion);
        derivacion.setProvidencia(providencia);
        derivacion.setDerivadoAUsuario(derivadoAUsuario);
        derivacion.setDerivadoPorUsuario(this.userService.getCurrentUser());
        derivacion.setDerivadoAGrupo(derivadoAGrupo);
        derivacion.setDerivadoPorGrupo(this.userService.getCurrentUser().getGrupo());
        derivacion.setEstado(EstadoDerivacion.NO_LEIDO);
        derivacion = this.derivacionRepository.save(derivacion);

        return derivacion;
    }

    // notificaciones created by: Ruben Barrera
    private NotificacionInBrowser registryNotificacion(String observacion, Grupo derivadoAGrupo, AccionesProvidencia evento) {


        List<Long> usuariosUNOporUNO=this.userRepository.findByAllGrupo(derivadoAGrupo.getId());


        for (int i= 0; i < usuariosUNOporUNO.size(); i++){

            NotificacionInBrowser notificacion =  new NotificacionInBrowser();

            log.debug("en el foreach este es el id de los usuarios del grupo: "+usuariosUNOporUNO.get(i));
            switch (evento){
                default:
                    notificacion.setContenido(observacion);
                    break;
                case FISCAL_NOTIFICA_A_UPD_CIERRE:
                    notificacion.setContenido("Fiscal A Adado Cierre a Investigacion de la Providencia: ");
                    break;
                case FISCAL_ACEPTA:
                    notificacion.setContenido("Fiscal A Aceptado  Investigacion de la Providencia: ");
                    break;


                  }

            notificacion.setGrupo(derivadoAGrupo);
            notificacion.setCreatedAt(Instant.now());
            notificacion.setVisto(false);
            Optional<User> usuario = this.userRepository.findById(usuariosUNOporUNO.get(i));
            notificacion.setUser(usuario.get());
            log.debug("ESTA ES LA NOTIFICACION: "+notificacion);
            notificacion = this.notificacionInBrowserRepository.save(notificacion);

        }
        return  null;
    }
    /**
     * BOTONES que ENVIA UN EVENTO PARA CONTINUAR FLUJO DE ESTADOS.
     *
     * @param providenciaResponseDTO
     */
    @Transactional
    public void reply(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton CONTINUAR paso: ");
        AccionesProvidencia evento = AccionesProvidencia.CREAR_PROVIDENCIA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void fiscalNotificaCierre(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton desde fiscal acepta y da inicio paso: ");
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_NOTIFICA_A_UPD_CIERRE;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void formularCargos(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton formularCargos: ");
        AccionesProvidencia evento = AccionesProvidencia.FORMULAR_CARGOS;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void terminoProbatorio (ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton terminoProbatorio: ");
        AccionesProvidencia evento = AccionesProvidencia.TERMINO_PROBATORIO;
        this.changeStage(providenciaResponseDTO, evento);
    }
    @Transactional
    public void siDeAcuerdo (ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton siDeAcuerdo: ");
        AccionesProvidencia evento = AccionesProvidencia.SI_DE_ACUERDO;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void noReabro(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton noReabro: ");
        AccionesProvidencia evento = AccionesProvidencia.NO_REABRE;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void noPropone (ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton noPropone: ");
        AccionesProvidencia evento = AccionesProvidencia.NO_PROPONE;
        this.changeStage(providenciaResponseDTO, evento);
    }

//    @Transactional
//    public void sobreseer (ProvidenciaResponseDTO providenciaResponseDTO) {
//        log.debug("boton sobreseer: ");
//        AccionesProvidencia evento = AccionesProvidencia.FLUJO_SOBRESEER;
//        this.changeStage(providenciaResponseDTO, evento);
////        ProvidenciaDTO provi = findOne(providenciaResponseDTO.getProvidenciaId()).get();
////        this.save(provi);
//    }

 @Transactional
    public void remiteExpediente(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton de fiscal remite expdiente: ");
        AccionesProvidencia evento = AccionesProvidencia.REMITE_EXPEDIENTE;
        this.changeStage(providenciaResponseDTO, evento);
    }


    @Transactional
    public void aceptar(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton ACEPTAR paso: ");
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_ACEPTA;
       this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void rechazar(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton RECHAZAR paso: ");
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_RECHAZA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void prorroga(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton PRORROGA paso: ");
        AccionesProvidencia evento = AccionesProvidencia.PRORROGA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    // BOTON REPRESENTA PARA FLUJO DE SANCION APELA
    @Transactional
    public void apela(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton APELA paso: ");
        AccionesProvidencia evento = AccionesProvidencia.CONTINUAR_FLUJO_APELA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    // BOTON REPRESENTA PARA FLUJO DE SANCION NO APELA
    @Transactional
    public void noApela(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton NO APELA paso: ");
        AccionesProvidencia evento = AccionesProvidencia.CONTINUAR_FLUJO_NO_APELA;
        this.changeStage(providenciaResponseDTO, evento);
    }
  // BOTON REPRESENTA PARA FLUJO DE SANCION NO APELA luego envia memo
    @Transactional
    public void inculpadoEnviaMemo(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton inculado envia memo paso: ");
        AccionesProvidencia evento = AccionesProvidencia.INCULPADO_ENVIA_MEMO;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void inculpadoNoEnviaMemo(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton NO APELA paso: ");
        AccionesProvidencia evento = AccionesProvidencia.INCULPADO_NO_ENVIA_MEMO;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void asignaFolio(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton asignaFolio paso: ");
        AccionesProvidencia evento = AccionesProvidencia.ASIGNA_FOLIO;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void notificaDemandado(ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("boton notificaDemandado paso: ");
        AccionesProvidencia evento = AccionesProvidencia.NOTIFICA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    /**
     * Método que recibe una devolución.
     *
     * @param providenciaResponseDTO
     */
    @Transactional
    public void goBackwards(ProvidenciaResponseDTO providenciaResponseDTO) {
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_RECHAZA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    private void standby( Providencia providencia) {
        EstadoProvidencia requisitoAntes = providencia.getRequisito();
        log.debug("entro al metodo standby: "+providencia.getStandby());
        switch (requisitoAntes) {
            /**
             * providencia detenida             */
            case PETICION_PRORROGA:
            case PETICION_PRORROGA_2:
            case SECRETARIA_DESPACHA_A_DGD:
            case PETICION_APELACION:
            case PETICION_NO_APELACION:
            case FISCAL_RECHAZO:
            case DGD_DESPACHA_SUMARIO_COMPLETO:
                providencia.setStandby(true);
                log.debug("sale al metodo standby: "+providencia.getStandby());
                break;

            /**
             *  providencia no detenida
             */
            case FORMULA_CARGOS_TERMINO_PROBATORIO:
                providencia.setStandby(false);
                break;
        }
    }
    /*
     Método que permite determinar el nuevo estado de la providencia y transmite la información necesaria a la clase
     Derivación y MovimientoProvidencia.
     */
    private void changeStage(ProvidenciaResponseDTO providenciaResponseDTO, AccionesProvidencia evento) {
        Optional<Providencia> providenciaOptional = this.providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId());
        Providencia providencia = null;
        ProvidenciaDTO providenciaDTO = null;
        EstadoProvidencia requisitoDespues = null;
        EstadoProvidencia subEtapa = null;
        EstadoProvidencia subEtapaAntes = null;
        EstadoProvidencia etapa = null;
        Long iDProvidenciaMadre = null;
        log.debug("ruben: estado al entrar: " + requisitoDespues);
        if (providenciaOptional.isPresent()) {
            AccionesProvidencia eventoBoton = null;
            providencia = providenciaOptional.get();
            etapa = providencia.getEtapa();

            //DEPENDE DE LA ETAPA LA ACCION CAMBIA PARA QUE LA PROVIDENCIA SIGA SU CAMINO CORRECTO

            log.debug("antes de entrar al metodo determianr evento " + evento);

            eventoBoton = determinarEvento(providencia);

            if (eventoBoton == null) {
                eventoBoton = evento;
            }
            log.debug("saliendo del metodo determinar evento " + eventoBoton);
            subEtapaAntes = providencia.getSubEtapa();
            providencia.setRequisito(this.newState(providencia, eventoBoton)); // AQUI ES DONDE SE LLAMA A LA MAQUINA
            requisitoDespues = providencia.getRequisito();
            log.debug(" requisto nuevo despues del cambio de estado "+ requisitoDespues);

            // setear subEtapa a requisitos repetidos en otros tipo de Providencia
//            if (etapa == EstadoProvidencia.PROVIDENCIA_SANCION_APELO && ( (requisitoDespues==EstadoProvidencia.ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION) ||
//                (requisitoDespues==EstadoProvidencia.ESPERANDO_FIRMA_DEL_DN) || (requisitoDespues==EstadoProvidencia.DGDP_ASIGNANDO_NUMERO) )){
//                subEtapa = EstadoProvidencia.RESOLUCION_DE_RECURSO;

            subEtapa = getNextSubEtapa(subEtapaAntes, requisitoDespues, etapa, eventoBoton);
            providencia.setSubEtapa(subEtapa);

            /**
             * determinar etapara  ebn el chambio de estado verificar que no afecte el flujo
             * RubenEtapaNUEV
             */
            etapa = this.determinaEtapaenFlujo(subEtapa,requisitoDespues,providencia.getEtapa());
            log.debug("Ruben- set Etapa nueva: " +etapa);
            providencia.setEtapa(etapa);
            log.debug("Ruben- get Etapa nueva: "+providencia.getEtapa());
            subEtapa = providencia.getSubEtapa();
            iDProvidenciaMadre = providencia.getProvidencia_madre_id();
            providencia.setEstadoActual(this.concatenarEstado(requisitoDespues, subEtapa, etapa));

            /**
             * stanby actualiza el booleano en la db
             */
            standby(providencia);
            providenciaDTO = this.update(this.providenciaMapper.toDto(providencia), providenciaResponseDTO.getAdjuntosDTOs());
            Grupo groupAnswer = this.determineGroupAnswer(providencia);
            log.debug("despues del grupo " + providencia.getProvidencia_madre_id());
            Derivacion derivacion = null;

            //crea la notificacion
            this.registryNotificacion("pendiente por hacer " + requisitoDespues, groupAnswer,evento);

                if (providencia.getNombreFiscalAsignado() != null) {
                    Optional<User> userOptional = this.userService.findByFullName(providencia.getNombreFiscalAsignado());

                    if (userOptional.isPresent()) {
                        derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                            this.providenciaMapper.toEntity(providenciaDTO), userOptional.get(), groupAnswer);
                    }
                }

                derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                    this.providenciaMapper.toEntity(providenciaDTO), null, groupAnswer);
                String accion = "";

                if (this.userService.getCurrentUser().getGrupo().getId() == 1 || this.userService.getCurrentUser().getGrupo().getId() == 2) {
                    accion = "Derivar";
                } else {
                    accion = "Enviar";
                }

                this.movimientoProvidenciaService.save(providenciaResponseDTO.getEstadoActual(), providenciaDTO.getEstadoActual(),
                    providencia.getId(), derivacion.getObservacion(), providenciaResponseDTO.getDocumentosDTOs(),
                    providenciaResponseDTO.getAdjuntosDTOs(), accion);
            }
            providencia.setProvidencia_madre_id(iDProvidenciaMadre);
        this.calcularPlazos(providencia);
            log.debug("Salida viendo cambio de id: " + providencia.getProvidencia_madre_id());
        }

        // Metodo para determinar la subEtapa de una providencia
    private EstadoProvidencia getNextSubEtapa(EstadoProvidencia subEtapaAntes,EstadoProvidencia requisitoDespues,
                                              EstadoProvidencia etapa, AccionesProvidencia eventoBoton) {
        EstadoProvidencia nextSubEtapa;

        if(requisitoDespues == EstadoProvidencia.FISCAL_REMITE_SUMARIO_A_DN){
            nextSubEtapa = subEtapaSegunSeleccion(eventoBoton);
        }else if (subEtapaAntes==EstadoProvidencia.SOBRESEER || subEtapaAntes==EstadoProvidencia.PROVIDENCIA_SOBRESEER_CREADA){
            nextSubEtapa = subEtapaAntes;
        }else{
            nextSubEtapa = this.determinaSubEtapa(requisitoDespues, etapa);
        }
        return nextSubEtapa;
    }

    // Metodo para poder Setear la subEtapa para FiscalRemiteExpediente Segun la opcion Seleccionada (Termino Probatorio o Formula Cargos)
    private EstadoProvidencia subEtapaSegunSeleccion(AccionesProvidencia eventoBoton) {

        EstadoProvidencia subEtapa = null;

        if (eventoBoton == AccionesProvidencia.FORMULAR_CARGOS){
            subEtapa = EstadoProvidencia.FORMULA_CARGOS;
        }else{
            subEtapa = EstadoProvidencia.TERMINO_PROBATORIO;
        }
        return subEtapa;
    }

    // METODO QUE DA UNA ACCION PARA SEGUIR EL FLUJO SEGUN LA ETAPA DE LA PROVIDENCIA
        private AccionesProvidencia determinarEvento (Providencia providencia){

            EstadoProvidencia requisitoAntes = providencia.getRequisito();
            EstadoProvidencia subEtapaAntes = providencia.getSubEtapa();
            EstadoProvidencia etapa = providencia.getEtapa();
            AccionesProvidencia evento = null;

            switch (requisitoAntes) {
//                //Requisitos Compartidos entre Providencia
//                case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION:
//                case ESPERANDO_FIRMA_DEL_DN:
//                case DGDP_ASIGNANDO_NUMERO:
//                case SECRETARIA_REVISA_ASIGNACION:

                    // Para que el flujo salte al requisito PETICION PRORROGA2
                case INVESTIGACION:
                    switch (etapa) {
                        case PROVIDENCIA_PRORROGA:
                            evento = AccionesProvidencia.PRORROGA2;
                            break;
                    }
                    break;
                // Para que la prorroga salte estados hasta Fiscal Acepto
                case SECRETARIA_REVISA_NUMERO:
                    switch (etapa) {
                        case PROVIDENCIA_PRORROGA:
                        case PROVIDENCIA_PRORROGA_2:
                            evento = AccionesProvidencia.PRORROGA;
                            break;
                    }
                    break;
            }
                // Evento para cuando sea un OrdenJuridico SOBRESEER
            switch (subEtapaAntes) {
                case SOBRESEER:
                    evento = AccionesProvidencia.FLUJO_SOBRESEER;
                    break;
                case ABSOLVER:
                    evento = AccionesProvidencia.FLUJO_ABSOLVER;
                    break;
                case REABRIR:
                    evento = AccionesProvidencia.FLUJO_REABRIR;
                    break;
            }
            return evento;
        }

    /*
     Estos métodos devuelven el nuevo estado de la Providencia.
     */
        private EstadoProvidencia newState (Providencia providencia, AccionesProvidencia accion){

            Long idProvidencia = providencia.getId();
            EstadoProvidencia siguienteEstado = null;
            EstadoProvidencia requisitoActual = providencia.getRequisito();
            log.debug(" Requisito Actual " + requisitoActual);

            if (requisitoActual == null) {
                siguienteEstado = EstadoProvidencia.PROVIDENCIA_CREADA;
                log.debug(" Requisito Actual es null " + siguienteEstado);

            } else {
                providenciaStateMachineService.nextState(idProvidencia, accion, requisitoActual);
                siguienteEstado = EstadoProvidencia.valueOf(stateMachine.getState().getId().toString());
                log.debug(" Requisito Actual no es null " + siguienteEstado);
            }
            log.debug(" Antes del return ");
            return siguienteEstado;
        }

    /*
        Método que permite, en función del estado, determinar qué grupo o departamento debe hacerse cargo del flujo de
        una providencia.
     */
        private Grupo determineGroupAnswer (Providencia providencia){
            Grupo groupAnswer = null;
            Optional<Grupo> optionalGroup = null;
            EstadoProvidencia etapa = providencia.getEtapa();

            switch (etapa) {
                // Permiso para el grupo Dirección Nacional DN((Toda la Etapa 1 PRUEBA))
                case NUEVA_PROVIDENCIA:
                    optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                    if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                    break;

                case PROVIDENCIA_SELECCION_FISCAL:
                case INVESTIGACION:
                case REVISION_SUMARIO:
                case INFORME_JURIDICO:
                    optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                    if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                    break;

                case PROVIDENCIA_REABRIR:
                case PROVIDENCIA_SANCION:
                case PROVIDENCIA_SOBRECEDER:
                case PROVIDENCIA_ABSOLVER:
                    optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                    if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                    break;

                case PROVIDENCIA_SANCION_APELO:
                case PROVIDENCIA_SANCION_NO_APELO:
                    optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                    if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                    break;

                case PROVIDENCIA_PRORROGA:
                case PROVIDENCIA_PRORROGA_2:
                    optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                    if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                    break;


            }
            return groupAnswer;
        }

        /**
         * Método que permite saber qué acciones están permitidas en función del Requisito de la providencia.
         * Los permisos también están limitados por el perfil y grupo. Todas estás acciones se ven reflejadas solo en el detalle de la providencia.
         * Tambien permite la visualizacion de Botones segun Requisito
         *
         * @param providenciaDTO
         * @return
         */
        @Transactional(readOnly = true)
        public HashMap<String, Boolean> getActionsPermitted (ProvidenciaResponseDTO providenciaDTO){

            EstadoProvidencia requisito = providenciaDTO.getRequisito();
            EstadoProvidencia etapa = providenciaDTO.getEtapa();
            EstadoProvidencia subEtapa= providenciaDTO.getSubEtapa();
            if (providenciaDTO.getEtapa() == null && providenciaDTO.getSubEtapa() == null && providenciaDTO.getRequisito() == null) {
                return null;
            }
            Grupo grupoCurrentUser = this.userService.getCurrentUser().getGrupo();
            Perfil perfilUser = this.userService.getCurrentUser().getPerfil();

            if (grupoCurrentUser == null) {
                return null;
            }
            HashMap<String, Boolean> actionsPermitted = new HashMap<>();
            // Se agregan las acciones con un valor false de inicio.
            // "reply" permite mostrar u ocultar el botón continuar para cambiar de estado.
            // "goBackwards" permite mostrar u ocultar el botón que da pie a volver o rechazar a la etapa anterior.
            // "watchTabRespuesta" permite mostrar u ocultar el tab que da pie a crear una respuesta.
            // "asignarFiscal" permite mostrar u ocultar el botón que da pie a abrir un model y asignar el nombre del fiscal.
            // "relacionarProvidencia" permite mostrar u ocultar el botón que da pie a relacionar una providencia.
            // "numerarReferencia" permite mostrar u ocultar el botón que da pie a asignar el número de referencia.
            // "tipoSolicitud" permite mostrar u ocultar el botón que da pie a asignar el tipo de solicitud.
            actionsPermitted.put("reply", false);
            actionsPermitted.put("fiscalNotificaCierre", false);
            actionsPermitted.put("fiscalNotificaUPD", false);
            actionsPermitted.put("goBackwards", false);
            actionsPermitted.put("watchTabRespuesta", true);
            actionsPermitted.put("asignarFiscal", false);
            actionsPermitted.put("relacionarProvidencia", false);
            actionsPermitted.put("numerarReferencia", false);
            actionsPermitted.put("tipoSolicitud", false);
            actionsPermitted.put("aceptar", false);
            actionsPermitted.put("rechazar", false);
            actionsPermitted.put("prorroga", false);
            actionsPermitted.put("apela", false);
            actionsPermitted.put("noApela", false);
            actionsPermitted.put("representa", false);
            actionsPermitted.put("registra", false);
            actionsPermitted.put("inculpadoEnviaMemo", false);
            actionsPermitted.put("inculpadoNoEnviaMemo", false);
            actionsPermitted.put("formularCargos", false);
            actionsPermitted.put("terminoProbatorio", false);
            actionsPermitted.put("siDeAcuerdo", false);
            actionsPermitted.put("noReabro", false);
            actionsPermitted.put("noPropone", false);
            actionsPermitted.put("remiteExpediente", false);
            actionsPermitted.put("asignaFolio", false);
            actionsPermitted.put("notificaDemandado", false);

            switch (requisito) {   // Falta un switch anidado para los casos especificos de que salte (muestre un boton o accion diferente) a otro requisito si es alguna etapa especifica

                case NUEVA_PROVIDENCIA:
                case PROVIDENCIA_CREADA:
                case GESTOR_DOCUMENTAL_ASIGNA_NUMERO:
                case SECRETARIA_REVISA_NUMERO:
                case SECRETARIA_REVISA_ASIGNACION:
                case SECRETARIA_REVISA_FIRMA:
                case DGD_DESPACHA_A_DN:
                case SECRETARIA_REVISA_NOTIFICACION:
                case SECRETARIA_REVISA_RESOLUCION_Y_MEMO:
                case SECRETARIA_REVISA_FIRMA_NOTIFICACION:
                case ESPERANDO_FIRMA_DE_SUBDIRECCION_A_NOTIFICACION:
                case SUB_DIRECCION_DEBE_ASIGNAR:
                case UPD_REDACTA_RESOLUCION_Y_MEMO:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)   || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5))   {
                        actionsPermitted.put("reply", true);
                        actionsPermitted.put("numerarReferencia", true);
                        actionsPermitted.put("asignarFiscal", false);
                        actionsPermitted.put("relacionarProvidencia", true);
                    }
                    break;
                case ENVIAR_A_SUB_DIRRECION_JURIDICA:
                case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION:
                case ESPERANDO_FIRMA_DEL_DN:
                case DGDP_ASIGNANDO_NUMERO:
                case DGD_RECEPCIONA:
                case UPD_ELABORA_NOTIFICACION_VISTA_FISCAL:
                case DGD_DESPACHA_NOTIFICACION_FISCAL:
                case ENVIADO_A_SUBDIRECCION_JURIDICA:
                case UPD_NOTIFICA_FISCAL:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1) || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5)) {
                        actionsPermitted.put("reply", true);
                        actionsPermitted.put("goBackwards", true);
                        actionsPermitted.put("asignarFiscal", false);
                        actionsPermitted.put("numerarReferencia", true);

                    } else {
                        actionsPermitted.put("watchTabRespuesta", false);
                    }
                    break;

                case FORMULA_CARGOS_Y_NOTIFICA:
                    if (subEtapa== EstadoProvidencia.DA_INICIO){

                        if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
                            actionsPermitted.put("inculpadoEnviaMemo", true);
                            actionsPermitted.put("inculpadoNoEnviaMemo",true);
                        } else {
                            actionsPermitted.put("watchTabRespuesta", false);
                        }
                        break;
                    }
                case INCULPADO_ENVIA_MEMO:
                case INCULPADO_NO_ENVIA_MEMO:
                    if (subEtapa== EstadoProvidencia.DA_INICIO){

                        if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
                            actionsPermitted.put("formularCargos", true);
                            actionsPermitted.put("terminoProbatorio", true);
                        } else {
                            actionsPermitted.put("watchTabRespuesta", false);
                        }
                        break;
                    }

                case FORMULA_CARGOS:
                    break;
                case APELACION_INCULPADO:
                case FORMULA_CARGOS_TERMINO_PROBATORIO:
                case FISCAL_REMITE_EXPEDIENTE:
                    if (subEtapa== EstadoProvidencia.DA_INICIO
                    ){

                        if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                            actionsPermitted.put("apela", true);
//                            actionsPermitted.put("noApela", true);
                            actionsPermitted.put("remiteExpediente", true);

                        } else {
                            actionsPermitted.put("watchTabRespuesta", false);
                        }
                        break;
                    }

                case FISCAL_REMITE_SUMARIO_A_DN:
                case ENVIAR_SUMARIO_A_SUB_DIRECCION:
                case DGD_DESPACHA_SUMARIO:
                case SECRETARIA_REVISA_SUMARIO:
                case SUB_DIRECCION_ASIGNA_ABOGADO:
                case SECRETARIA_REVISA_SUMARIO_Y_NOTIFICA_A_ABOGADO:
                case SI_DE_ACUERDO:
                case NO_REABRE:
                case NO_PROPONE:
                case SECRETARIA_REVISA_INFORME:
                case SUB_DIRECCION_REVISA_INFORME:
                case SECRETARIA_DESPACHA_INFORME:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1) || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5)) {
                        actionsPermitted.put("reply", true);
                    }
                    break;

                case ABOGADO_ELABORA_INFORME:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1) || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5)) {
                        actionsPermitted.put("siDeAcuerdo", true);
                        actionsPermitted.put("noReabro", true);
                        actionsPermitted.put("noPropone", true);
                    }
                    break;

//                case REVISION_SUMARIO_COMPLETO:
//                case DGD_RECEPCIONA_SUMARIO:
//                case SECRETARIA_REVISA_SUMARIO:
//                case DN_RECIBE_SUMARIO_COMPLETO:
//                case SUB_DIRECCION_ASIGNA_ABOGADO:
//                case SECRETARIA_NOTIFICA_A_ABOGADO:
//                case ABOGADO_ELABORA_INFORME:
//                case SECRETARIA_REVISA_INFORME:
//                case SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO:
//
//                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                        actionsPermitted.put("reply", true);
//                        actionsPermitted.put("asignarFiscal", true);
//                        actionsPermitted.put("numerarReferencia", true);
//                    } else {
//                        actionsPermitted.put("watchTabRespuesta", false);
//                    }
//                    break;
                case FISCAL_NOTIFICADO:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
                        actionsPermitted.put("aceptar", true);
                        actionsPermitted.put("rechazar", true);
                    } else {
                        actionsPermitted.put("watchTabRespuesta", false);
                    }
                    break;
                case INVESTIGACION: // requisito el estado en la maquina de estado
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                        actionsPermitted.put("reply", true);
//                        actionsPermitted.put("fiscalNotificaUPD", true);
                        actionsPermitted.put("fiscalNotificaCierre", true);
                        actionsPermitted.put("prorroga", true);
                    }
                    break;

                case SJ_RECIBE_PROVIDENCIA:
                case SECRETARIA_REVISA_PROVIDENCIA:
                case SUB_DIRECCION_ASIGNA_A_RESOLUCION_Y_MEMO:
                case SECRETARIA_DESPACHA_A_UPD:
                case RECIBE_SJ_PARA_MEMO:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1) || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5)) {
                        actionsPermitted.put("reply", true);
                    }
                    break;

                case REDACCION_MEMO:
                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1) || (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 5)) {
                        actionsPermitted.put("asignaFolio", true);
                        actionsPermitted.put("notificaDemandado", true);
                    }
                    break;

//                case UPD_REALIZA_MEMO:
//                case UPD_REDACTA_RESOLUCION_EXCENTA_Y_MEMO:
//                case SECRETARIA_REVISA_RESOLUCION_EXCENTA:
//                case SECRETARIA_REVISA_FIRMA_RESOLUCION_EXCENTA:
//                case UPD_ELABORA_NOTIFICACION:
//                case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_NOTIFICACION:
//                case CERTIFICACION_NO_APELO:
//                case UPD_REALIZO_RESOLUCION_Y_MEMO_REPRESENTA:
//                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                        actionsPermitted.put("reply", true);
//                    } else {
//                        actionsPermitted.put("watchTabRespuesta", false);
//                    }
//                    break;
//                case CONTRALORIA_NOTIFICADA:
//                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                        actionsPermitted.put("registra", true);
//                        actionsPermitted.put("representa", true);
//                    } else {
//                        actionsPermitted.put("watchTabRespuesta", false);
//                    }
//                    break;
//                case RECEPCION_RESPUESTA_CONTRALORIA:
//                case DGDP_NOTIFICADO:
//                case UPD_REALIZO_MEMO_Y_NOTIFICO:
//                case ESPERANDO_RESPUESTA_INCULPADO:
//                case DN_RECIBIO_APELACION:
//                case SECRETARIA_REVISA_APELACION:
//                case SUB_DIRECCION_ASIGNANDO_ABOGADO:
//                case ABOGADO_ELABORA_INFORME_APELACION:
//                case SECRETARIA_REVISA_ASIGNACION_ABOGADO:
//                case ABOGADO_ELABORO_INFORME:
//                case SUB_DIRECCION_RECIBE_INFORME:
//                case ESPERANDO_FIRMA_DN_A_INFORME:
//                case SECRETARIA_REVISA_FIRMA_DN_INFORME:
//                case SUB_DIRECCION_ASIGNANDO_A_UPD:
//                case UPD_REALIZA_RESOLUCION_RESUELVE_RECURSO_Y_MEMO:
//                case UPD_RECIBE_NOTIFICACION_REALIZA_RESOLUCION:
//                case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION_A_RESOLUCION:
//                case SUB_DIRECCION_REALIZO_MEMO_CONDUCTOR:
//                case ESPERANDO_FIRMA_DEL_DN_A_RESOLUCION:
//                case DGDP_ASIGNANDO_NUMERO_A_RESOLUCION:
//                case DGDP_RECIBE_NOTIFICACION_CONTRALORIA:
//                case ELABORANDO_ALCANCE_O_RESOLUCION:
//                case UPD_ARCHIVA_NOTIFICA_E_INFORMA:
//                    if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
//                        actionsPermitted.put("reply", true);
//                    } else {
//                        actionsPermitted.put("watchTabRespuesta", false);
//                    }
//                    break;
            }
            return actionsPermitted;
        }

        /**
         * Método que permite obtener las plantillas en función del estado en el que se encuentra la providencia.
         *
         * @param providenciaDTO
         * @return
         */
        @Transactional(readOnly = true)
        public List<PlantillaDTO> getPlantillasEnabled (ProvidenciaDTO providenciaDTO){
            List<PlantillaDTO> plantillasEnabled = null;

            switch (providenciaDTO.getRequisito()) {
                case UPD_REDACTA_RESOLUCION_Y_MEMO:
                    plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                        if (p.getTipo().equals(TipoPlantilla.MEMORANDUM) || p.getTipo().equals(TipoPlantilla.RESOLUCION)) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    break;
                case FISCAL_REMITE_EXPEDIENTE:
                    plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                        if (p.getTipo().equals(TipoPlantilla.NOTIFICACION)) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    break;

//                case FISCAL_ACEPTO_Y_DA_INICIO:
//                    plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
//                        if (p.getTipo().equals(TipoPlantilla.MEMORANDUM)) {
//                            return true;
//                        }
//                        return false;
//                    }).collect(Collectors.toList());
//                    break;

                case INVESTIGACION:
                    plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                        if (p.getTipo().equals(TipoPlantilla.MEMORANDUM)) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    break;



//                case PROVIDENCIA_CREADA:
//                    plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
//                        if (p.getTipo().equals(TipoPlantilla.MEMORANDUM)) {
//                            return true;
//                        }
//                        return false;
//                    }).collect(Collectors.toList());
//                    break;
            }
            return plantillasEnabled;
        }

        /**
         * Método que permite obtener las providencias que consideran al mismo implicado.
         *
         * @param runImplicado
         * @param entidadImplicadaId
         * @param providenciaId
         * @return
         */
        @Transactional
        public Set<ProvidenciaDTO> findAllByRunOrEntidadImplicada (String runImplicado, Long entidadImplicadaId, Long
        providenciaId){
            if (runImplicado != null && entidadImplicadaId != 0) {
                return this.providenciaRepository.findAllByRunOrEntidadImplicada(runImplicado,
                    this.entidadMapper.toEntity(this.entidadService.findOne(entidadImplicadaId).get()), providenciaId).stream()
                    .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
            } else if (runImplicado == null && entidadImplicadaId != 0) {
                return this.providenciaRepository.findAllByRunOrEntidadImplicada(null,
                    this.entidadMapper.toEntity(this.entidadService.findOne(entidadImplicadaId).get()), providenciaId).stream()
                    .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
            } else if (runImplicado != null && entidadImplicadaId == 0) {
                return this.providenciaRepository.findAllByRunOrEntidadImplicada(runImplicado, null, providenciaId).stream()
                    .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
            }

            return new HashSet<>();
        }

        @Transactional
        public ProvidenciaDTO updateNombreFiscalAsignado (ProvidenciaDTO providenciaDTO){
            Providencia providencia = this.providenciaRepository.save(this.providenciaMapper.toEntity(providenciaDTO));
            return this.providenciaMapper.toDto(providencia);
        }

        @Transactional
        public Set<ProvidenciaDTO> findAllWithoutPagination () {
            return this.providenciaRepository.findAll().stream().map(this.providenciaMapper::toDto).collect(Collectors.toSet());
        }

        @Transactional
        public ProvidenciaDTO updateNumeroReferencia (ProvidenciaUpdateNumeroReferenciaDTO
        providenciaUpdateNumeroReferenciaDTO){
            Providencia providencia = null;

            if (providenciaUpdateNumeroReferenciaDTO.getProvidenciaId() != null && providenciaUpdateNumeroReferenciaDTO
                .getProvidenciaId() > 0 && providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia() != null &&
                providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia() > 0) {
                this.providenciaRepository.updateNumeroReferencia(providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia(),
                    providenciaUpdateNumeroReferenciaDTO.getProvidenciaId());

                providencia = this.providenciaRepository.getOne(providenciaUpdateNumeroReferenciaDTO.getProvidenciaId());
            }

            return this.providenciaMapper.toDto(providencia);
        }

        @Transactional
        public ProvidenciaDTO updateTipoSolicitud (ProvidenciaUpdateTipoSolicitudDTO providenciaUpdateTipoSolicitudDTO){
            Providencia providencia = null;

            if (providenciaUpdateTipoSolicitudDTO.getProvidenciaId() != null && providenciaUpdateTipoSolicitudDTO
                .getProvidenciaId() > 0 && providenciaUpdateTipoSolicitudDTO.getTipoSolicitud() != null) {
                this.providenciaRepository.updateTipoSolicitud(providenciaUpdateTipoSolicitudDTO.getTipoSolicitud(),
                    providenciaUpdateTipoSolicitudDTO.getProvidenciaId());

                providencia = this.providenciaRepository.getOne(providenciaUpdateTipoSolicitudDTO.getProvidenciaId());
            }
            return this.providenciaMapper.toDto(providencia);
        }

        // Metodo que obtiene la providencia madre correspondiente al tipo de Providencia a Crear
        @Transactional(readOnly = true)
        public Providencia getProvidenciaNumeroReferencia (Long numeroReferencia, String tipoProvidencia){
            log.debug("Request to get Providencia : {}", numeroReferencia);
            List<Providencia> providencias = null;

            if (tipoProvidencia == "SeleccionFiscal") {
                providencias = providenciaRepository.findByNumeroRefeSeleccionFiscal(numeroReferencia);
            } else if (tipoProvidencia == "ordenJuridico") {
                providencias = providenciaRepository.findByNumeroRefeOrdenJuridico(numeroReferencia);
            } else if (tipoProvidencia == "seleccionApelacion") {
                providencias = providenciaRepository.findByNumeroRefeSeleccionApelacion(numeroReferencia);
            }
            return providencias.get(0);
        }


        // Metodo que crea una Providencia de tipo SELECCIONFISCAL u ORDENJURIDICO
//        public ProvidenciaDTO createdProvidenciaForType (ProvidenciaDTO providenciaDTO, Providencia providenciaMadre,
//            OrdenJuridico ordenJuridicoSeleccionado){
//
//            Providencia provi = new Providencia();
////        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
//            OrdenJuridico oJuridicoSeleccionado = ordenJuridicoSeleccionado;
//            EstadoProvidencia etapa = null;
//
//            if (oJuridicoSeleccionado == null) {
//                etapa = this.determinaEtapa(providenciaMadre.getRequisito());
//            } else {
//                etapa = this.determinaEtapaOrdenJuridico(ordenJuridicoSeleccionado);
//            }
//            EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
//            EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito, etapa);
//            String estadoProviCompleto = this.concatenarEstado(requisito, subEtapa, etapa);
//            String estadoInicial = this.determinaEstadoInicial(etapa);
//
//            provi.setFechaCreacion(Instant.now());
//            provi.setEtapa(etapa);
//            provi.setSubEtapa(subEtapa);
//            provi.setRequisito(requisito);
//            provi.setEstadoActual(estadoProviCompleto);
//            provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());
//
//            if (providenciaMadre.getEtapa() == EstadoProvidencia.NUEVA_PROVIDENCIA) {
//                provi.setProvidencia_madre_id(providenciaMadre.getId());
//            } else {
//                provi.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
//            }
//
//            insertaTipoProvidencia(provi);
//            provi = providenciaRepository.save(provi);
//            verificaAdjuntosYGuarda(providenciaDTO, provi);
//            //Settear el ID de la providencia creada en los adjuntos
//            List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
//            ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);
//
//            if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
//                providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
//            }
//
//            Grupo grupoAnswer = this.determineGroupAnswer(provi);
//            Derivacion derivacion = this.registerDerivation("desde el director nacional", provi, null, grupoAnswer);
//
////        this.registryNotificacion("createdProvidenciaForType", grupoAnswer);
//
//            this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
//                null, proviDto.getAdjuntos(), "Derivado");
//
//            return proviDto;
//        }
//
//        // Metodo que crea una tipo de providencia Sancion APELO o NO APELO
//        public ProvidenciaDTO createdProvidenciaSancion (ProvidenciaDTO providenciaDTO,
//            Providencia providenciaMadre, Apelacion tipoApelacion){
//
//            Providencia provi = new Providencia();
////        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
//            Apelacion tipoApleacion = tipoApelacion;
//            EstadoProvidencia etapa = null;
//
//            if (tipoApleacion == Apelacion.APELO) {
//                etapa = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
//            } else {
//                etapa = EstadoProvidencia.PROVIDENCIA_SANCION_NO_APELO;
//            }
//            EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
//            EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito, etapa);
//            String estadoProviCompleto = this.concatenarEstado(requisito, subEtapa, etapa);
//            String estadoInicial = this.determinaEstadoInicial(etapa);
//
//            provi.setFechaCreacion(Instant.now());
//            provi.setEtapa(etapa);
//            provi.setSubEtapa(subEtapa);
//            provi.setRequisito(requisito);
//            provi.setEstadoActual(estadoProviCompleto);
//            provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());
//            provi.setProvidencia_madre_id(providenciaMadre.getId());
//
//            insertaTipoProvidencia(provi);
//            provi = providenciaRepository.save(provi);
//            verificaAdjuntosYGuarda(providenciaDTO, provi);
//            //Settear el ID de la providencia creada en los adjuntos
//            List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
//            ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);
//
//            if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
//                providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
//            }
//
//            Grupo grupoAnswer = this.determineGroupAnswer(provi);
//            Derivacion derivacion = this.registerDerivation("desde el director nacional", provi, null, grupoAnswer);
//
//            this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
//                null, proviDto.getAdjuntos(), "Derivado");
//
//            return proviDto;
//        }

        // Metodo para Determinar la Etapa en base a la seleccion del OrdenJuridico hecho por el usuario
//        private EstadoProvidencia determinaEtapaOrdenJuridico (OrdenJuridico ordenJuridicoSeleccionado){
//
//            EstadoProvidencia etapaOrdenJuridico = null;
//
//            switch (ordenJuridicoSeleccionado) {
//                case REABRIR:
//                    etapaOrdenJuridico = EstadoProvidencia.PROVIDENCIA_REABRIR;
//                    break;
//                case SANCIONAR:
//                    etapaOrdenJuridico = EstadoProvidencia.PROVIDENCIA_SANCION;
//                    break;
//                case SOBRESEER:
//                    etapaOrdenJuridico = EstadoProvidencia.CREADA_PROVIDENCIA_SOBRESEER;
//                    break;
//                case ABSOLVER:
//                    etapaOrdenJuridico = EstadoProvidencia.PROVIDENCIA_ABSOLVER;
//                    break;
//            }
//            return etapaOrdenJuridico;
//        }


        @Transactional(readOnly = true)
        public Optional<ProvidenciaDTO> findAllProrroga (Long idMadre){

            log.debug("Request to get Providencia : {}", idMadre);
            Optional result = providenciaRepository.findById(idMadre);
            return result;
        }

        @Transactional(readOnly = true)
        public Providencia getProvidenciaMadreid (Long idMadre){
            log.debug("Request to get Providencia : {}", idMadre);
            Optional<Providencia> providencia = null;

            providencia = (Optional<Providencia>) providenciaRepository.findOneWithEagerRelationships(idMadre);

            log.debug("mi providencia madre de prorroga: " + providencia.get());
            return providencia.get();
        }

//    // crear prorroga
//    public ProvidenciaDTO createdProvidenciProrroga( Providencia providenciaMadre) {
////        ProvidenciaDTO providenciaDTO= null;
//        Providencia provi = new Providencia();
////        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
//        ///
//        EstadoProvidencia etapa = null;
//        providenciaRepository.updateRequisito(providenciaMadre.getId());
//        if (providenciaMadre.getEtapa() == EstadoProvidencia.PRORROGA) {
//            etapa = EstadoProvidencia.PRORROGA2;
//        } else {
//            etapa = EstadoProvidencia.PRORROGA;
//        }
//        // EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
//        //  EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito);
//        String estadoProviCompleto = this.concatenarEstado(providenciaMadre.getRequisito(), providenciaMadre.getSubEtapa(), etapa);
//
//        String estadoInicial = estadoProviCompleto;
//
//        //setear providencia nueva con datos de la madre
//        provi.setFechaCreacion(Instant.now());
//        provi.setFechaSolicitud(providenciaMadre.getFechaSolicitud());
//        provi.setEtapa(etapa);
//        provi.setSubEtapa(providenciaMadre.getSubEtapa());
//        provi.setRequisito(providenciaMadre.getRequisito());
//        provi.setEstadoActual(estadoProviCompleto);
//        provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());
//        provi.setNombreImplicado(providenciaMadre.getNombreImplicado());
//        provi.setRunImplicado(providenciaMadre.getRunImplicado());
//        provi.setNombreSolicitante(providenciaMadre.getNombreSolicitante());
//        provi.setRunSolicitante(providenciaMadre.getRunSolicitante());
//        // si la prorroga 2 debe ten er la madre de la  1 modificar esto en caso contrario eliminar y dejar providenciamadre.getid
//        if (providenciaMadre.getEtapa() == EstadoProvidencia.NUEVA_PROVIDENCIA) {
//            provi.setProvidencia_madre_id(providenciaMadre.getId());
//        } else {
//            provi.setProvidencia_madre_id(providenciaMadre.getId());
//        }
//
//        insertaTipoProvidencia(provi);
//        //el save toma los datos y crea la providencia nueva
//        provi = providenciaRepository.save(provi);
////        verificaAdjuntosYGuarda(providenciaDTO, provi);
//        //Settear el ID de la providencia creada en los adjuntos
////        List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
//        ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);
//
////        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
////            providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
////        }
//
//        Grupo grupoAnswer = this.determineGroupAnswer(provi);
//        Derivacion derivacion = this.registerDerivation("Creacion de Prorroga", provi, null, grupoAnswer);
//        this.registryNotificacion("Creacion de Prorroga", grupoAnswer);
//        this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
//            null, proviDto.getAdjuntos(), "Derivado");
//
//        return proviDto;
//    }


        //determinar la etapa para prorroga
//    private EstadoProvidencia determinaEtapaProrroga(Prorroga tipoProrroga) {
//
//        EstadoProvidencia etapaProrroga = null;
//
//        switch (tipoProrroga) {
//            case NUEVA_PROVIDENCIA:
//                etapaProrroga = EstadoProvidencia.PRORROGA;
//                break;
//            case PRORROGA:
//                etapaProrroga = EstadoProvidencia.PRORROGA2;
//                break;
//        }
//        return etapaProrroga;
//    }

        // Metodo que actualiza la providencia por tipo
        public ProvidenciaDTO updateProvidenciaForType (ProvidenciaUpdateForTypeDTO providenciaUpdateTypeDTO){

            Providencia providencia = null;
            log.debug("referencia del forype de la madre: " + providenciaUpdateTypeDTO.getNumeroReferencia());

            Long numeroReferencia = providenciaUpdateTypeDTO.getNumeroReferencia();
            Providencia providenciaMadre = providenciaRepository.findForNumberReferent(numeroReferencia).get(0);
            EstadoProvidencia requisito = providenciaMadre.getRequisito();
            log.debug("El requisito para actualizar la provi es " + requisito);

            EstadoProvidencia etapaActualizada = null;

            switch (requisito) {

                    // Aqui se relaciona una Providencia Seleccion Fiscal
                case FISCAL_RECHAZO:
                    etapaActualizada = EstadoProvidencia.PROVIDENCIA_SELECCION_FISCAL;

                    providencia = this.providenciaRepository.getOne(providenciaUpdateTypeDTO.getProvidenciaId());

                    if (providenciaMadre.getProvidencia_madre_id() != null) {
                        providencia.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
                    } else {
                        providencia.setProvidencia_madre_id(providenciaUpdateTypeDTO.getProvidenciaMadreId());
                    }
                    providencia.setNumeroReferencia(numeroReferencia);
                    providencia.setEtapa(etapaActualizada);
                    providencia.setEstadoActual(this.concatenarEstado(providencia.getRequisito(), providencia.getSubEtapa(), etapaActualizada));
                    break;

                    // Aqui se relaciona una Providencia Prorroga 1 y Prorroga 2
                case PETICION_PRORROGA:
                case PETICION_PRORROGA_2:

                    if (providenciaMadre.getEtapa() == EstadoProvidencia.PROVIDENCIA_PRORROGA) {

                        log.debug("entro en prorroga endpoint cuando es prorroga1");
                        etapaActualizada = EstadoProvidencia.PROVIDENCIA_PRORROGA_2;
                    } else {
                        etapaActualizada = EstadoProvidencia.PROVIDENCIA_PRORROGA;
                    }

                    providencia = this.providenciaRepository.getOne(providenciaUpdateTypeDTO.getProvidenciaId());

                    if (providenciaMadre.getProvidencia_madre_id() != null) {
                        providencia.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
                    } else {
                        providencia.setProvidencia_madre_id(providenciaUpdateTypeDTO.getProvidenciaMadreId());
                    }
                    providencia.setNombreFiscalAsignado(providenciaMadre.getNombreFiscalAsignado());
                    providencia.setNumeroReferencia(numeroReferencia);
                    providencia.setEtapa(etapaActualizada);
                    providencia.setEstadoActual(this.concatenarEstado(providencia.getRequisito(), providencia.getSubEtapa(), etapaActualizada));
                    break;

                // Aqui se relaciona una Providencia Orden Juridico (Sancion, Sobreceder, Absolver, Reabrir)
                case DGD_DESPACHA_SUMARIO_COMPLETO:

                    etapaActualizada = EstadoProvidencia.INFORME_JURIDICO;
                    EstadoProvidencia subEtapaActualizada = determinarSubEtapa(providenciaUpdateTypeDTO.getOrdenJuridico());
                    providencia = this.providenciaRepository.getOne(providenciaUpdateTypeDTO.getProvidenciaId());

                    if (providenciaMadre.getProvidencia_madre_id() != null) {
                        providencia.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
                    } else {
                        providencia.setProvidencia_madre_id(providenciaUpdateTypeDTO.getProvidenciaMadreId());
                    }
                    providencia.setNumeroReferencia(numeroReferencia);
                    providencia.setEtapa(etapaActualizada);
                    providencia.setSubEtapa(subEtapaActualizada);
                    providencia.setEstadoActual(this.concatenarEstado(providencia.getRequisito(), providencia.getSubEtapa(), etapaActualizada));
                    break;

                // Aqui se relaciona una Providencia Sancion Apela
//                case PETICION_APELACION:
//
//                    etapaActualizada = determinaApelacion(requisito);
//                    providencia = this.providenciaRepository.getOne(providenciaUpdateTypeDTO.getProvidenciaId());
//
//                    if (providenciaMadre.getProvidencia_madre_id() != null) {
//                        providencia.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
//                    } else {
//                        providencia.setProvidencia_madre_id(providenciaUpdateTypeDTO.getProvidenciaMadreId());
//                    }
//                    providencia.setNumeroReferencia(numeroReferencia);
//                    providencia.setEtapa(etapaActualizada);
//                    providencia.setEstadoActual(this.concatenarEstado(providencia.getRequisito(), providencia.getSubEtapa(), etapaActualizada));
//                    break;
            }
            return this.providenciaMapper.toDto(providencia);
        }

    private EstadoProvidencia determinarSubEtapa(OrdenJuridico ordenJuridico) {

            EstadoProvidencia subEtapaNueva = null;

        switch (ordenJuridico) {
            case SOBRECEDER:
                subEtapaNueva = EstadoProvidencia.SOBRESEER;
                break;
            case ABSOLVER:
                subEtapaNueva = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
                break;
            case SANCIONAR:
                subEtapaNueva = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
                break;
            case REABRIR:
                subEtapaNueva = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
                break;
        }
         return subEtapaNueva;
    }

    //  Determina el estado, si el inculpado Apelo o No
        private EstadoProvidencia determinaApelacion (EstadoProvidencia tipoApelacion){

            EstadoProvidencia seleccionApelacion = null;

            switch (tipoApelacion) {
                case PETICION_NO_APELACION:
                    seleccionApelacion = EstadoProvidencia.PROVIDENCIA_SANCION_NO_APELO;
                    break;
                case PETICION_APELACION:
                    seleccionApelacion = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
                    break;
            }
            return seleccionApelacion;
        }
}

