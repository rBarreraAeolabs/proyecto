package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.*;
import com.gruposolux.rcivil.pdisciplinario.repository.DerivacionRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.*;
import com.gruposolux.rcivil.pdisciplinario.storage.AlfrescoStorageService;
import com.sun.el.stream.*;
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
        StateMachine stateMachine
    ) {
        this.providenciaRepository = providenciaRepository;
        this.providenciaMapper = providenciaMapper;
        this.adjuntoMapper = adjuntoMapper;
        this.publisher = publisher;
        this.userService = userService;
        this.adjuntoService = adjuntoService;
        this.derivacionRepository = derivacionRepository;
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
        EstadoProvidencia subEtapa = this.determinaSubEtapa(requisitoEstado);
        EstadoProvidencia etapa = this.determinaEtapa(requisitoEstado);
        String estadoProviCompleto = this.concatenarEstado(requisitoEstado, subEtapa, etapa);
        String estadoInicial = this.determinaEstadoInicial(EstadoProvidencia.NUEVA_PROVIDENCIA);
        providencia.setFechaCreacion(Instant.now());
        providencia.setRequisito(requisitoEstado);
        providencia.setSubEtapa(subEtapa);
        providencia.setEtapa(etapa);
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

        this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, providencia.getId(), derivacion.getObservacion(), ////agREGAR UNA VARIABLE QUE INVOLUCRE EL ESTADO COMPLETO DE LA PROVIDENCIA CON VARIABLE "newState"
            null, providenciaDTO.getAdjuntos(), "Derivado");

        return providenciaDTO;
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

        String estadoInicial = null;
        EstadoProvidencia requisitoInicial;
        EstadoProvidencia etapaInicial;
        EstadoProvidencia subEtapaInicial;

        if (requisito == EstadoProvidencia.PROVIDENCIA_SELECCION_FISCAL) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial);

        } else if (requisito == EstadoProvidencia.IJ_PROVIDENCIA_REABRIR || requisito == EstadoProvidencia.IJ_PROVIDENCIA_SANCIONAR
            || requisito == EstadoProvidencia.IJ_PROVIDENCIA_SOBRECEDER || requisito == EstadoProvidencia.IJ_PROVIDENCIA_ABSOLVER) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial);
        } else if (requisito == EstadoProvidencia.PROVIDENCIA_SANCION_APELO || requisito == EstadoProvidencia.PROVIDENCIA_SANCION_NO_APELO) {
            etapaInicial = requisito;
            requisitoInicial = EstadoProvidencia.NUEVA_PROVIDENCIA;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial);
        } else {
            requisitoInicial = requisito;
            subEtapaInicial = this.determinaSubEtapa(requisitoInicial);
            etapaInicial = this.determinaEtapa(requisito);
        }
        estadoInicial = this.concatenarEstado(requisitoInicial, subEtapaInicial, etapaInicial);

        return estadoInicial;
    }

    /**
     * Determina la SubEtapa en la que esta la providencia partiendo de los requesitosEstados.
     *
     * @param requisitoEstado requisito o Estado en el que se encuentra la providenia
     * @return subEtapa
     */
    private EstadoProvidencia determinaSubEtapa(EstadoProvidencia requisitoEstado) {

        log.debug(" determinar subEtapa  con requisito " + requisitoEstado);
        EstadoProvidencia subEtapa = null;
        EstadoProvidencia requisito = requisitoEstado;

        switch (requisito) {

            case NUEVA_PROVIDENCIA:
            case PROVIDENCIA_CREADA:
            case GESTOR_DOCUMENTAL_ASIGNA_NUMERO:
            case SUB_DIRECCION_RECIBE_PROVIDENCIA:
            case SUB_DIRECCION_DEBE_ASIGNAR:
                subEtapa = EstadoProvidencia.NUEVA_PROVIDENCIA;
                break;
            case UPD_REDACTA_RESOLUCION_Y_MEMO:
            case ENVIAR_A_SUB_DIRRECION_JURIDICA:
            case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION:
            case ENVIAR_A_DGD:
            case ENVIAR_A_DN:
                subEtapa = EstadoProvidencia.RESOLUCION_Y_MEMO;
                break;
            case ESPERANDO_FIRMA_DEL_DN:
                subEtapa = EstadoProvidencia.FIRMA_RESOLUCION;
                break;
            case DGDP_ASIGNA_TIPO_SOLICITUD:
            case DGDP_ASIGNANDO_NUMERO:
                subEtapa = EstadoProvidencia.NUMERO_DGDP;
                break;
            case RECEPCION_DGD:
            case UPD_ELABORA_NOTIFICACION_VISTA_FISCAL:
            case UPD_ELABORO_NOTIFICACION_VISTA_FISCAL:
            case ENVIADO_A_SUBDIRECCION_JURIDICA:
                subEtapa = EstadoProvidencia.ELABORACION_NOTIFICACION_FISCAL;
                break;
            case UPD_NOTIFICA_FISCAL:
                subEtapa = EstadoProvidencia.NOTIFICACION_FISCAL;
                break;
            case FISCAL_NOTIFICADO:
            case FISCAL_ACEPTO:
            case FISCAL_RECHAZO:
            case FISCAL_REDACTA_MEMO:
            case UPD_DA_INICIO:
            case FISCAL_COMIENZA_INVESTIGACION:
            case FISCAL_ENVIA_A_UPD_MEMO_CIERRE:
            case UPD_REGISTRA_CIERRE:
                subEtapa = EstadoProvidencia.INVESTIGACION;
                break;
            case FISCAL_NOTIFICA_A_INCULPADO:
                subEtapa = EstadoProvidencia.NOTIFICACION_INCULPADO;
                break;
            case INCULPADO_ENVIA_MEMO:
            case FISCAL_FORMULA_CARGOS:
            case TERMINO_PROBATORIO:
                subEtapa = EstadoProvidencia.TERMINO_PROBATORIO;
                break;
            case FISCAL_REMITE_EXPEDIENTE:
            case DN_RECIBE_SUMARIO_COMPLETO:
                subEtapa = EstadoProvidencia.VISTA_FISCAL;
                break;
            case SUB_DIRECCION_ASIGNA_ABOGADO:
            case ABOGADO_ELABORA_INFORME:
            case SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO:
                subEtapa = EstadoProvidencia.INFORME_JURIDICO;
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
                //PRORROGA AGREGAR ACA

            case PRORROGA:
                etapa = EstadoProvidencia.PRORROGA2;
                break;

//            case SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO:
//                etapa = EstadoProvidencia.PROVIDENCIA_INFORME_JURIDICO;
//                break;
        }
        log.debug(" La Etapa es " + etapa);
        return etapa;
    }

    @Transactional
    public ProvidenciaDTO update(ProvidenciaDTO providenciaDTO, Set<AdjuntoDTO> adjuntoDTOs) {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);

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
        return providenciaDTO;
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
            Set<MovimientoProvidenciaDTO> mp = this.movimientoProvidenciaService.getAllByIdProvidencia(this.providenciaMapper
                .toDto(this.providenciaRepository.getOne(providencia.getId())));

            TreeSet<MovimientoProvidenciaDTO> ts = new TreeSet<>(mp);

            MovimientoProvidenciaDTO mpLast = ts.pollLast();
            MovimientoProvidenciaDTO mpBeforeLast = ts.pollLast();
            String nombreGrupo = this.determineGroupAnswer(providencia).getNombre();

            if (mpBeforeLast != null) {
                return new ProvidenciaItemListDTO(
                    providencia.getId(),
                    providencia.getFechaCreacion(),
                    providencia.getEstadoActual(),
                    nombreGrupo,
                    ChronoUnit.DAYS.between(providencia.getFechaCreacion(), Instant.now()),
                    ChronoUnit.DAYS.between(mpLast.getFecha(), mpBeforeLast.getFecha()),
                    null
                );
            }
            return new ProvidenciaItemListDTO(
                providencia.getId(),
                providencia.getFechaCreacion(),
                providencia.getEstadoActual(),
                nombreGrupo,
                ChronoUnit.DAYS.between(providencia.getFechaCreacion(), Instant.now()),
                ChronoUnit.DAYS.between(providencia.getFechaCreacion(), mpLast.getFecha()),
                null
            );
        });
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
        log.debug("Request to get Providencia : {}", id);
        return providenciaRepository.findById(id).map(this.providenciaMapper::toDto);
    }

    //
    // buscar por numero de referencia y traer todas las providencias ccon ese numero
    @Transactional(readOnly = true)
    public List<NroReferenciaDTO> findAllNro(Long numeroReferencia) {

        log.debug("Request to get Providencia : {}", numeroReferencia);
        List nro = providenciaRepository.findByAllnroReferencia(numeroReferencia);
        return nro;
    }


    // madre prorroga
    @Transactional(readOnly = true)
    public Optional<ProvidenciaDTO> findAllProrroga(Long idMadre) {

        log.debug("Request to get Providencia : {}", idMadre);
        Optional result = providenciaRepository.findById(idMadre);
        return result;
    }

    /*
    varchar cambiarlo a string el comentario al crear providencia
    sacar la hora al crear providencia
    el formato pdf

    tipo, estado, numero providencia lo mas importante
     */

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
    private List<AdjuntoDTO> setIdProvidenciaOnAdjuntos(Providencia providencia, Set<AdjuntoDTO> adjuntosDTOs) {
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

    /**
     * Método que recibe una respuesta.
     *
     * @param providenciaResponseDTO
     */
    @Transactional
    public void reply(ProvidenciaResponseDTO providenciaResponseDTO) {
        AccionesProvidencia evento = AccionesProvidencia.CREAR_PROVIDENCIA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void aceptar(ProvidenciaResponseDTO providenciaResponseDTO) {
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_ACEPTA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    @Transactional
    public void rechazar(ProvidenciaResponseDTO providenciaResponseDTO) {
        AccionesProvidencia evento = AccionesProvidencia.FISCAL_RECHAZA;
        this.changeStage(providenciaResponseDTO, evento);
    }

    /**
     * Método que recibe una devolución.
     *
     * @param providenciaResponseDTO
     */
    @Transactional
    public void goBackwards(ProvidenciaResponseDTO providenciaResponseDTO) {
//        this.changeStage(providenciaResponseDTO, (byte)0);
    }

    /*
     Método que permite determinar el nuevo estado de la providencia y transmite la información necesaria a la clase
     Derivación y MovimientoProvidencia.
     */
    private void changeStage(ProvidenciaResponseDTO providenciaResponseDTO, AccionesProvidencia evento) {
        Optional<Providencia> providenciaOptional = this.providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId());
        Providencia providencia = null;
        ProvidenciaDTO providenciaDTO = null;
        EstadoProvidencia requisito = null;
        EstadoProvidencia subEtapa = null;
        EstadoProvidencia etapa = null;
        if (providenciaOptional.isPresent()) {
            providencia = providenciaOptional.get();
            providencia.setRequisito(this.newState(providencia, evento));
            requisito = providencia.getRequisito();
            providencia.setSubEtapa(this.determinaSubEtapa(requisito));
            subEtapa = providencia.getSubEtapa();
//            providencia.setEtapa(this.determinaEtapa(subEtapa));
            etapa = providencia.getEtapa();

            providencia.setEstadoActual(this.concatenarEstado(requisito, subEtapa, etapa));

            providenciaDTO = this.update(this.providenciaMapper.toDto(providencia), providenciaResponseDTO.getAdjuntosDTOs());
            Grupo groupAnswer = this.determineGroupAnswer(providencia);

            Derivacion derivacion = null;

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
    }
    /*
     Estos métodos devuelven el nuevo estado de providencia. El parámetro de acción tiene dos valores posibles: 0 y 1.
     Ninguno de los otros valores tiene efecto sobre el parámetro de acción.
     */

    private EstadoProvidencia newState(Providencia providencia, AccionesProvidencia accion) {

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
    private Grupo determineGroupAnswer(Providencia providencia) {
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
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;

            case IJ_PROVIDENCIA_REABRIR:
            case IJ_PROVIDENCIA_SANCIONAR:
            case IJ_PROVIDENCIA_SOBRECEDER:
            case IJ_PROVIDENCIA_ABSOLVER:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;

            case PROVIDENCIA_SANCION_APELO:
            case PROVIDENCIA_SANCION_NO_APELO:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
        }
        return groupAnswer;
    }

    /**
     * Método que permite saber qué acciones están permitidas en función del Requisito de la providencia. Los permisos
     * también están limitados por el perfil y grupo. Todas estás acciones se ven reflejadas solo en el detalle de la
     * providencia. El perfil 2 es de jefe y el de secretaria es 3.
     *
     * @param providenciaDTO
     * @return
     */
    @Transactional(readOnly = true)
    public HashMap<String, Boolean> getActionsPermitted(ProvidenciaDTO providenciaDTO) {
        EstadoProvidencia requisito = providenciaDTO.getRequisito();
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
        actionsPermitted.put("goBackwards", false);
        actionsPermitted.put("watchTabRespuesta", true);
        actionsPermitted.put("asignarFiscal", false);
        actionsPermitted.put("relacionarProvidencia", false);
        actionsPermitted.put("numerarReferencia", false);
        actionsPermitted.put("tipoSolicitud", false);
        actionsPermitted.put("aceptar", false);
        actionsPermitted.put("rechazar", false);

        switch (requisito) {   // Falta un switch anidado para los casos especificos de que salte (muestre un boton o accion diferente) a otro requisito si es alguna etapa especifica

            case NUEVA_PROVIDENCIA:
            case PROVIDENCIA_CREADA:
            case SUB_DIRECCION_RECIBE_PROVIDENCIA:
            case GESTOR_DOCUMENTAL_ASIGNA_NUMERO:
            case SUB_DIRECCION_DEBE_ASIGNAR:
            case UPD_REDACTA_RESOLUCION_Y_MEMO:
            case ENVIAR_A_SUB_DIRRECION_JURIDICA:
            case ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION:
            case ENVIAR_A_DGD:
            case ENVIAR_A_DN:
            case ESPERANDO_FIRMA_DEL_DN:
            case DGDP_ASIGNA_TIPO_SOLICITUD:
            case DGDP_ASIGNANDO_NUMERO:
            case RECEPCION_DGD:
            case UPD_ELABORA_NOTIFICACION_VISTA_FISCAL:
            case UPD_ELABORO_NOTIFICACION_VISTA_FISCAL:
            case ENVIADO_A_SUBDIRECCION_JURIDICA:
            case UPD_NOTIFICA_FISCAL:
            case FISCAL_ACEPTO:
            case FISCAL_REDACTA_MEMO:
            case UPD_DA_INICIO:
            case FISCAL_COMIENZA_INVESTIGACION:
            case FISCAL_ENVIA_A_UPD_MEMO_CIERRE:
            case UPD_REGISTRA_CIERRE:
            case FISCAL_NOTIFICA_A_INCULPADO:
            case INCULPADO_ENVIA_MEMO:
            case FISCAL_FORMULA_CARGOS:
            case TERMINO_PROBATORIO:
            case FISCAL_REMITE_EXPEDIENTE:
            case DN_RECIBE_SUMARIO_COMPLETO:
            case SUB_DIRECCION_ASIGNA_ABOGADO:
            case ABOGADO_ELABORA_INFORME:
                if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("asignarFiscal", true);
                    actionsPermitted.put("relacionarProvidencia", true);
                    actionsPermitted.put("numerarReferencia", true);
                    actionsPermitted.put("tipoSolicitud", true);
                } else {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case FISCAL_NOTIFICADO:
                if ((grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3) || (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 1)) {
                    actionsPermitted.put("aceptar", true);
                    actionsPermitted.put("rechazar", true);
                } else {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
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
    public List<PlantillaDTO> getPlantillasEnabled(ProvidenciaDTO providenciaDTO) {
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
            case FISCAL_ENVIA_A_UPD_MEMO_CIERRE:
                plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                    if (p.getTipo().equals(TipoPlantilla.NOTIFICACION)) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                break;
            case PROVIDENCIA_CREADA:
                plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                    if (p.getTipo().equals(TipoPlantilla.MEMORANDUM)) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                break;
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
    public Set<ProvidenciaDTO> findAllByRunOrEntidadImplicada(String runImplicado, Long entidadImplicadaId, Long providenciaId) {
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
    public ProvidenciaDTO updateNombreFiscalAsignado(ProvidenciaDTO providenciaDTO) {
        Providencia providencia = this.providenciaRepository.save(this.providenciaMapper.toEntity(providenciaDTO));
        return this.providenciaMapper.toDto(providencia);
    }

    @Transactional
    public Set<ProvidenciaDTO> findAllWithoutPagination() {
        return this.providenciaRepository.findAll().stream().map(this.providenciaMapper::toDto).collect(Collectors.toSet());
    }

    @Transactional
    public ProvidenciaDTO updateProvidenciaMadre(ProvidenciaUpdateMadreDTO providenciaUpdateMadreDTO) {
        Providencia providencia = null;

        if (providenciaUpdateMadreDTO != null && providenciaUpdateMadreDTO.getProvidenciaId() != null
            && providenciaUpdateMadreDTO.getProvidenciaId() > 0 && providenciaUpdateMadreDTO.getProvidenciaMadreId()
            != null && providenciaUpdateMadreDTO.getProvidenciaMadreId() > 0) {
            this.providenciaRepository.updateProvidenciaMadre(providenciaUpdateMadreDTO.getProvidenciaMadreId(),
                providenciaUpdateMadreDTO.getProvidenciaId());

            providencia = this.providenciaRepository.getOne(providenciaUpdateMadreDTO.getProvidenciaId());
        }

        return this.providenciaMapper.toDto(providencia);
    }

    @Transactional
    public ProvidenciaDTO updateNumeroReferencia(ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {
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
    public ProvidenciaDTO updateTipoSolicitud(ProvidenciaUpdateTipoSolicitudDTO providenciaUpdateTipoSolicitudDTO) {
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
    public Providencia getProvidenciaNumeroReferencia(Long numeroReferencia, String tipoProvidencia) {
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

    @Transactional(readOnly = true)
    public Providencia getProvidenciaMadreid(Long idMadre, String tipoProvidencia) {
        log.debug("Request to get Providencia : {}", idMadre);
        List<Providencia> providencias = null;

        if (tipoProvidencia == "EL ESTADO DE DONDE NACE LA PRORROGA?") {
            providencias = (List<Providencia>) providenciaRepository.findOneforProrroga(idMadre);
        } else if (tipoProvidencia == "PRORROGA") {
            providencias = (List<Providencia>) providenciaRepository.findOneforProrroga(idMadre);
        }
        return providencias.get(0);
    }

    // crear prrorroga
    public ProvidenciaDTO createdProvidenciProrroga(ProvidenciaDTO providenciaDTO, Providencia providenciaMadre,
                                                    Prorroga tipoProrroga) {


            Providencia provi = new Providencia();
//        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
            Prorroga ProrrogaSeleccionado = tipoProrroga;
            EstadoProvidencia etapa = null;

            if (ProrrogaSeleccionado == null) {
                etapa = this.determinaEtapa(providenciaMadre.getRequisito());
            } else {
                etapa = this.determinaEtapaProrroga(tipoProrroga);
            }
            EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
            EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito);
            String estadoProviCompleto = this.concatenarEstado(requisito, subEtapa, etapa);
            String estadoInicial = this.determinaEstadoInicial(etapa);

            provi.setFechaCreacion(Instant.now());
            provi.setEtapa(etapa);
            provi.setSubEtapa(subEtapa);
            provi.setRequisito(requisito);
            provi.setEstadoActual(estadoProviCompleto);
            provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());

            if (providenciaMadre.getEtapa() == EstadoProvidencia.NUEVA_PROVIDENCIA) {
                provi.setProvidencia_madre_id(providenciaMadre.getId());
            } else {
                provi.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
            }

            insertaTipoProvidencia(provi);
            provi = providenciaRepository.save(provi);
            verificaAdjuntosYGuarda(providenciaDTO, provi);
            //Settear el ID de la providencia creada en los adjuntos
            List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
            ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);

            if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
                providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
            }

            Grupo grupoAnswer = this.determineGroupAnswer(provi);
            Derivacion derivacion = this.registerDerivation("desde el director nacional", provi, null, grupoAnswer);

            this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
                null, proviDto.getAdjuntos(), "Derivado");

            return proviDto;
        }



        // Metodo que crea una Providencia de tipo SELECCIONFISCAL u ORDENJURIDICO

        public ProvidenciaDTO createdProvidenciaForType (ProvidenciaDTO providenciaDTO, Providencia providenciaMadre,
            OrdenJuridico ordenJuridicoSeleccionado){

            Providencia provi = new Providencia();
//        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
            OrdenJuridico oJuridicoSeleccionado = ordenJuridicoSeleccionado;
            EstadoProvidencia etapa = null;

            if (oJuridicoSeleccionado == null) {
                etapa = this.determinaEtapa(providenciaMadre.getRequisito());
            } else {
                etapa = this.determinaEtapaOrdenJuridico(ordenJuridicoSeleccionado);
            }
            EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
            EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito);
            String estadoProviCompleto = this.concatenarEstado(requisito, subEtapa, etapa);
            String estadoInicial = this.determinaEstadoInicial(etapa);

            provi.setFechaCreacion(Instant.now());
            provi.setEtapa(etapa);
            provi.setSubEtapa(subEtapa);
            provi.setRequisito(requisito);
            provi.setEstadoActual(estadoProviCompleto);
            provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());

            if (providenciaMadre.getEtapa() == EstadoProvidencia.NUEVA_PROVIDENCIA) {
                provi.setProvidencia_madre_id(providenciaMadre.getId());
            } else {
                provi.setProvidencia_madre_id(providenciaMadre.getProvidencia_madre_id());
            }

            insertaTipoProvidencia(provi);
            provi = providenciaRepository.save(provi);
            verificaAdjuntosYGuarda(providenciaDTO, provi);
            //Settear el ID de la providencia creada en los adjuntos
            List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
            ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);

            if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
                providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
            }

            Grupo grupoAnswer = this.determineGroupAnswer(provi);
            Derivacion derivacion = this.registerDerivation("desde el director nacional", provi, null, grupoAnswer);

            this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
                null, proviDto.getAdjuntos(), "Derivado");

            return proviDto;
        }

        // Metodo que crea una tipo de providencia Sancion APELO o NO APELO
        public ProvidenciaDTO createdProvidenciaSancion (ProvidenciaDTO providenciaDTO,
            Providencia providenciaMadre, Apelacion tipoApelacion) {

            Providencia provi = new Providencia();
//        Providencia provi = providenciaMapper.toEntity(providenciaDTO);
            Apelacion tipoApleacion = tipoApelacion;
            EstadoProvidencia etapa = null;

            if (tipoApleacion == Apelacion.APELO) {
                etapa = EstadoProvidencia.PROVIDENCIA_SANCION_APELO;
            } else {
                etapa = EstadoProvidencia.PROVIDENCIA_SANCION_NO_APELO;
            }
            EstadoProvidencia requisito = this.newState(provi, AccionesProvidencia.CREAR_PROVIDENCIA);
            EstadoProvidencia subEtapa = this.determinaSubEtapa(requisito);
            String estadoProviCompleto = this.concatenarEstado(requisito, subEtapa, etapa);
            String estadoInicial = this.determinaEstadoInicial(etapa);

            provi.setFechaCreacion(Instant.now());
            provi.setEtapa(etapa);
            provi.setSubEtapa(subEtapa);
            provi.setRequisito(requisito);
            provi.setEstadoActual(estadoProviCompleto);
            provi.setNumeroReferencia(providenciaMadre.getNumeroReferencia());
            provi.setProvidencia_madre_id(providenciaMadre.getId());

            insertaTipoProvidencia(provi);
            provi = providenciaRepository.save(provi);
            verificaAdjuntosYGuarda(providenciaDTO, provi);
            //Settear el ID de la providencia creada en los adjuntos
            List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(provi, providenciaDTO.getAdjuntos());
            ProvidenciaDTO proviDto = providenciaMapper.toDto(provi);

            if (adjuntoDTOs != null && adjuntoDTOs.size() > 0) {
                providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
            }

            Grupo grupoAnswer = this.determineGroupAnswer(provi);
            Derivacion derivacion = this.registerDerivation("desde el director nacional", provi, null, grupoAnswer);

            this.movimientoProvidenciaService.save(estadoInicial, estadoProviCompleto, provi.getId(), derivacion.getObservacion(),
                null, proviDto.getAdjuntos(), "Derivado");

            return proviDto;
        }

        // Metodo para Determinar la Etapa en base a la seleccion del OrdenJuridico hecho por el usuario
        private EstadoProvidencia determinaEtapaOrdenJuridico (OrdenJuridico ordenJuridicoSeleccionado){

            EstadoProvidencia etapaOrdenJuridico = null;

            switch (ordenJuridicoSeleccionado) {
                case REABRIR:
                    etapaOrdenJuridico = EstadoProvidencia.IJ_PROVIDENCIA_REABRIR;
                    break;
                case SANCIONAR:
                    etapaOrdenJuridico = EstadoProvidencia.IJ_PROVIDENCIA_SANCIONAR;
                    break;
                case SOBRECEDER:
                    etapaOrdenJuridico = EstadoProvidencia.IJ_PROVIDENCIA_SOBRECEDER;
                    break;
                case ABSOLVER:
                    etapaOrdenJuridico = EstadoProvidencia.IJ_PROVIDENCIA_ABSOLVER;
                    break;
            }
            return etapaOrdenJuridico;
        }


        //determinar la etapa para prorroga
    private EstadoProvidencia determinaEtapaProrroga (Prorroga tipoProrroga){

        EstadoProvidencia etapaProrroga = null;

        switch (tipoProrroga) {
            case DONDENACEPRORROGA:
                etapaProrroga = EstadoProvidencia.PRORROGA;
                break;
            case PRORROGA:
                etapaProrroga = EstadoProvidencia.PRORROGA2;
                break;

        }
        return etapaProrroga;
    }




}
