package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.*;
import com.gruposolux.rcivil.pdisciplinario.repository.DerivacionRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.*;
import com.gruposolux.rcivil.pdisciplinario.storage.AlfrescoStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

        AlfrescoStorageService alfrescoStorageService) {
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
    }

    /**
     * Save a providencia.
     *
     * @param providenciaDTO the entity to save
     * @return the persisted entity
     */
    public ProvidenciaDTO save(ProvidenciaDTO providenciaDTO) {
        log.debug("Request to save Providencia : {}", providenciaDTO);
        Providencia providencia = providenciaMapper.toEntity(providenciaDTO);
        providencia.setFechaCreacion(Instant.now());
        EstadoProvidencia newState = this.determineNewState(EstadoProvidencia.CREADO, (byte)3);
        providencia.setEstadoActual(newState);

        // si la providencia tiene un tipo, se debe; instanciar ese tipo y relacionarlo con la providencia
        if (providencia.getTipo() != null)
        {
            if(providencia.getTipo().equals(TipoProvidencia.INVESTIGACION_SUMARIA)){
                InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();
                InvestigacionSumariaDTO investigacionSumariaDTO = this.investigacionSumariaService
                    .save(this.investigacionSumariaMapper.toDto(investigacionSumaria));

                investigacionSumaria = this.investigacionSumariaMapper.toEntity(investigacionSumariaDTO);

                providencia.setInvestigacionSumaria(investigacionSumaria);
            }
            else if(providencia.getTipo().equals(TipoProvidencia.SUMARIO_ADMINISTRATIVO)){
                SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();
                SumarioAdministrativoDTO sumarioAdministrativoDTO = this.sumarioAdministrativoService
                    .save(this.sumarioAdministrativoMapper.toDto(sumarioAdministrativo));

                sumarioAdministrativo = this.sumarioAdministrativoMapper.toEntity(sumarioAdministrativoDTO);
                providencia.setSumarioAdministrativo(sumarioAdministrativo);

            }
        }

        providencia = providenciaRepository.save(providencia);

        if(providenciaDTO.getAdjuntos().size() > 0)
        {
            providencia.setAdjuntos(providenciaDTO.getAdjuntos().stream().map(this.adjuntoMapper::toEntity)
                .collect(Collectors.toSet()));

            this.alfrescoStorageService.moveToArchivosFolder(providenciaDTO.getAdjuntos().
                stream().map(AdjuntoDTO::getHash).collect(Collectors.toList()), providencia);
        }

        //Settear el ID de la providencia creada en los adjuntos debido a que providencia manda en la relación.
        List<AdjuntoDTO> adjuntoDTOs = this.setIdProvidenciaOnAdjuntos(providencia, providenciaDTO.getAdjuntos());

        providenciaDTO = this.providenciaMapper.toDto(providencia);

        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0)
        {
            providenciaDTO.setAdjuntos(adjuntoDTOs.stream().collect(Collectors.toSet()));
        }

        Grupo groupAnswer = this.determineGroupAnswer(providencia.getEstadoActual());

        Derivacion derivacion = this.registerDerivation("desde el director nacional", providencia, null, groupAnswer);

        this.movimientoProvidenciaService.save(EstadoProvidencia.CREADO, newState, providencia.getId(), derivacion.getObservacion(),
            null, providenciaDTO.getAdjuntos(), "Derivado");

        return providenciaDTO;
    }

    @Transactional
    public ProvidenciaDTO update(ProvidenciaDTO providenciaDTO, Set<AdjuntoDTO> adjuntoDTOs)
    {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);

        // si la providencia tene un tipo, se debe; instanciar ese tipo y relacionarlo con la providencia
        if (providencia.getTipo() != null)
        {
            if(providencia.getTipo().equals(TipoProvidencia.INVESTIGACION_SUMARIA) && providencia.getInvestigacionSumaria()== null ){
                InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();
                InvestigacionSumariaDTO investigacionSumariaDTO = this.investigacionSumariaService
                    .save(this.investigacionSumariaMapper.toDto(investigacionSumaria));

                investigacionSumaria = this.investigacionSumariaMapper.toEntity(investigacionSumariaDTO);

                providencia.setInvestigacionSumaria(investigacionSumaria);
            }
            else if(providencia.getTipo().equals(TipoProvidencia.SUMARIO_ADMINISTRATIVO)&& providencia.getSumarioAdministrativo() == null ){
                SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();
                SumarioAdministrativoDTO sumarioAdministrativoDTO = this.sumarioAdministrativoService
                    .save(this.sumarioAdministrativoMapper.toDto(sumarioAdministrativo));

                sumarioAdministrativo = this.sumarioAdministrativoMapper.toEntity(sumarioAdministrativoDTO);
                providencia.setSumarioAdministrativo(sumarioAdministrativo);

            }
        }

        this.providenciaRepository.save(providencia);

        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0)
        {
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
            String nombreGrupo = this.determineGroupAnswer(providencia.getEstadoActual()).getNombre();

            if (mpBeforeLast != null)
            {
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

    /**
     * Delete the providencia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Providencia : {}", id);
        providenciaRepository.deleteById(id);
    }

    private List<AdjuntoDTO> setIdProvidenciaOnAdjuntos(Providencia providencia, Set<AdjuntoDTO> adjuntosDTOs)
    {
        if (adjuntosDTOs != null && adjuntosDTOs.size() > 0)
        {
            for (Iterator<AdjuntoDTO> it = adjuntosDTOs.iterator(); it.hasNext(); ) {
                AdjuntoDTO adjuntoDTO = it.next();
                if (adjuntoDTO.getProvidenciaId() == null)
                {
                    adjuntoDTO.setProvidenciaId(providencia.getId());
                    this.adjuntoService.save(adjuntoDTO);
                }
            }

            return this.adjuntoService.getByProvidencia(providencia).stream().map(this.adjuntoMapper::toDto)
                .collect(Collectors.toList());
        }

        return null;
    }

    private Derivacion registerDerivation(String observacion, Providencia providencia, User derivadoAUsuario, Grupo derivadoAGrupo)
    {
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

    @Transactional
    public void reply(ProvidenciaResponseDTO providenciaResponseDTO)
    {
        Optional<Providencia> providenciaOptional = this.providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId());
        Providencia providencia = null;
        ProvidenciaDTO providenciaDTO = null;
        if (providenciaOptional.isPresent())
        {
            providencia = providenciaOptional.get();
            providencia.setEstadoActual(this.determineNewState(providencia.getEstadoActual(), (byte)1));
            providenciaDTO = this.update(this.providenciaMapper.toDto(providencia), providenciaResponseDTO.getAdjuntosDTOs());
            Grupo groupAnswer = this.determineGroupAnswer(providenciaDTO.getEstadoActual());

            Derivacion derivacion = null;

            if (providencia.getNombreFiscalAsignado() != null)
            {
                Optional<User> userOptional = this.userService.findByFullName(providencia.getNombreFiscalAsignado());

                if (userOptional.isPresent())
                {
                    derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                        this.providenciaMapper.toEntity(providenciaDTO), userOptional.get(), groupAnswer);
                }
            }

            derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                this.providenciaMapper.toEntity(providenciaDTO), null, groupAnswer);

            String accion = "";

            if (this.userService.getCurrentUser().getGrupo().getId() == 1 || this.userService.getCurrentUser().getGrupo().getId() == 2)
            {
                accion = "Derivar";
            }
            else
            {
                accion = "Enviar";
            }

            this.movimientoProvidenciaService.save(providenciaResponseDTO.getEstadoActual(), providenciaDTO.getEstadoActual(),
                providencia.getId(), derivacion.getObservacion(), providenciaResponseDTO.getDocumentosDTOs(),
                providenciaResponseDTO.getAdjuntosDTOs(), accion);
        }
    }

    @Transactional
    public void goBackwards(ProvidenciaResponseDTO providenciaResponseDTO)
    {
        Optional<Providencia> providenciaOptional = this.providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId());
        Providencia providencia = null;
        ProvidenciaDTO providenciaDTO = null;
        if (providenciaOptional.isPresent())
        {
            providencia = providenciaOptional.get();
            providencia.setEstadoActual(this.determineNewState(providencia.getEstadoActual(), (byte)0));
            providenciaDTO = this.update(this.providenciaMapper.toDto(providencia), providenciaResponseDTO.getAdjuntosDTOs());
            Grupo groupAnswer = this.determineGroupAnswer(providenciaDTO.getEstadoActual());

            Derivacion derivacion = null;

            if (providencia.getNombreFiscalAsignado() != null)
            {
                Optional<User> userOptional = this.userService.findByFullName(providencia.getNombreFiscalAsignado());

                if (userOptional.isPresent())
                {
                    derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                        this.providenciaMapper.toEntity(providenciaDTO), userOptional.get(), groupAnswer);
                }
            }

            derivacion = this.registerDerivation(providenciaResponseDTO.getObservacion(),
                this.providenciaMapper.toEntity(providenciaDTO), null, groupAnswer);

            String accion = "";

            if ((this.userService.getCurrentUser().getGrupo().getId() == 1 || this.userService.getCurrentUser().getGrupo().getId() == 2) &&
                this.userService.getCurrentUser().getPerfil().getId() != 2)
            {
                accion = "Derivar";
            }
            else
            {
                accion = "Enviar";
            }

            this.movimientoProvidenciaService.save(providenciaResponseDTO.getEstadoActual(), providenciaDTO.getEstadoActual(),
                providencia.getId(), derivacion.getObservacion(), null, providenciaResponseDTO.getAdjuntosDTOs(),
                accion);
        }
    }

    /*
     This methods return providencia's new state. The action parameter has two posible values 0 and 1.
     The 0 value means that the new state is the previous state and the 1 value is the next state.
     Any of the other values have no effect for the action parameter.
     */
    private EstadoProvidencia determineNewState(EstadoProvidencia estadoActual, byte action)
    {
        boolean secretaryValidation = true;

        EstadoProvidencia newState = null;

        switch (estadoActual)
        {
            case CREADO:
                // Validación de la secretaria
                if (secretaryValidation)
                {
                    newState = EstadoProvidencia.ESTADO_2;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_3;
                }
                break;
            case ESTADO_2:
                if (action == 0)
                {
                    newState = EstadoProvidencia.CREADO;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_3;
                }
                break;
            case ESTADO_3: // Departamento de Gestión Documental
                if (secretaryValidation)
                {
                    newState = EstadoProvidencia.ESTADO_4;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_5;
                }
                break;
            case ESTADO_4:
                if (action == 0)
                {
                    newState = EstadoProvidencia.CREADO;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_5;
                }
                break;
            case ESTADO_5:
                if (action == 0)
                {
                    newState = EstadoProvidencia.CREADO;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_6;
                }
                break;
            case ESTADO_6:
                if (secretaryValidation)
                {
                    newState = EstadoProvidencia.ESTADO_7;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_8;
                }
                break;
            case ESTADO_7:
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_6;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_8;
                }
                break;
            case ESTADO_8:
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_6;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_9;
                }
                break;
            case ESTADO_9: // Departamento de Gestión Documental
                if (secretaryValidation)
                {
                    newState = EstadoProvidencia.ESTADO_10;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_11;
                }
                break;
            case ESTADO_10: // Validación de la secretaria del Director Nacional
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_4;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_11;
                }
                break;
            case ESTADO_11: // Validación del Director Nacional y firma de la resolución
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_4;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_12;
                }
                break;
            case ESTADO_12: // DGDP da número a la resolución
                newState = EstadoProvidencia.ESTADO_13;
                break;
            case ESTADO_13: // Departamento de Gestión Documental
                newState = EstadoProvidencia.ESTADO_14;
                break;
            case ESTADO_14: // Elabora notificación al fiscal
                if (secretaryValidation)
                {
                    newState = EstadoProvidencia.ESTADO_15;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_16;
                }
                break;
            case ESTADO_15: // Secretaria de la subdirección recibe la notificación al fiscal
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_14;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_16;
                }
                break;
            case ESTADO_16: // Subdirectora recibe la notificación al fiscal
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_14;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_17;
                }
                break;
            case ESTADO_17: // UPD recibe y notifica la resolución y antecedentes
                newState = EstadoProvidencia.ESTADO_18;
                break;
            case ESTADO_18: // Departamento de Gestión Documental
                newState = EstadoProvidencia.ESTADO_19;
                break;
            case ESTADO_19: // Fiscal recibe los antecedentes y la resolución
                if (action == 0)
                {
                    newState = EstadoProvidencia.ESTADO_20;
                }
                else
                {
                    newState = EstadoProvidencia.ESTADO_21;
                }
                break;
            case ESTADO_20: // El Fiscal rechazó la causa
                newState = EstadoProvidencia.ESTADO_21;
                break;
            case ESTADO_21: // El Fiscal rechazó la causa
                newState = EstadoProvidencia.ESTADO_22;
                break;

        }

        return newState;
    }


    private Grupo determineGroupAnswer(EstadoProvidencia newState)
    {
        Grupo groupAnswer = null;
        Optional<Grupo> optionalGroup = null;

        switch (newState)
        {
            case CREADO:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_2:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_3:
                optionalGroup = this.grupoService.findOne(5L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_4:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_5:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_6:
                optionalGroup = this.grupoService.findOne(3L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_7:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_8:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_9:
                optionalGroup = this.grupoService.findOne(5L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_10:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_11:
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_12:
                optionalGroup = this.grupoService.findOne(4L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_13:
                optionalGroup = this.grupoService.findOne(5L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_14:
                optionalGroup = this.grupoService.findOne(3L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_15:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_16:
                optionalGroup = this.grupoService.findOne(2L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_17:
                optionalGroup = this.grupoService.findOne(3L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_18:
                optionalGroup = this.grupoService.findOne(5L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_19:
                optionalGroup = this.grupoService.findOne(7L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_20: // El Fiscal rechazó la causa
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
            case ESTADO_21: // El Fiscal rechazó la causa
                optionalGroup = this.grupoService.findOne(1L).map(this.grupoMapper::toEntity);
                if (optionalGroup.isPresent()) groupAnswer = optionalGroup.get();
                break;
        }

        return groupAnswer;
    }

    @Transactional(readOnly = true)
    public HashMap<String, Boolean> getActionsPermitted(ProvidenciaDTO providenciaDTO)
    {
        if (providenciaDTO.getEstadoActual() == null)
        {
            return null;
        }

        Grupo grupoCurrentUser = this.userService.getCurrentUser().getGrupo();
        Perfil perfilUser = this.userService.getCurrentUser().getPerfil();

        if (grupoCurrentUser == null)
        {
            return null;
        }

        HashMap<String, Boolean> actionsPermitted = new HashMap<>();
        // Se agregan las acciones con un valor false de inicio
        actionsPermitted.put("reply", false);
        actionsPermitted.put("goBackwards", false);
        actionsPermitted.put("watchTabRespuesta", true);
        actionsPermitted.put("asignarFiscal",false);
        actionsPermitted.put("relacionarProvidencia",false);

        switch (providenciaDTO.getEstadoActual())
        {
            case CREADO:
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_2:
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_3:
                if (grupoCurrentUser.getId() == 5 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("relacionarProvidencia",true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_4:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_5:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_6:
                if (grupoCurrentUser.getId() == 3 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("relacionarProvidencia",true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_7:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_8:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_9:
                if (grupoCurrentUser.getId() == 5 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_10:
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_11:
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("asignarFiscal",true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_12:
                if (grupoCurrentUser.getId() == 4 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_13:
                if (grupoCurrentUser.getId() == 5 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_14:
                if (grupoCurrentUser.getId() == 3 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_15:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_16:
                if (grupoCurrentUser.getId() == 2 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_17:
                if (grupoCurrentUser.getId() == 3 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_18:
                if (grupoCurrentUser.getId() == 5 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_19:
                if (grupoCurrentUser.getId() == 7 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_20: // El Fiscal rechazó la causa
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 2)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
            case ESTADO_21: // El Fiscal rechazó la causa
                if (grupoCurrentUser.getId() == 1 && perfilUser.getId() == 3)
                {
                    actionsPermitted.put("reply", true);
                    actionsPermitted.put("goBackwards", true);
                }
                else
                {
                    actionsPermitted.put("watchTabRespuesta", false);
                }
                break;
        }

        return actionsPermitted;
    }

    @Transactional(readOnly = true)
    public List<PlantillaDTO> getPlantillasEnabled(ProvidenciaDTO providenciaDTO)
    {
        List<PlantillaDTO> plantillasEnabled = null;

        switch (providenciaDTO.getEstadoActual())
        {
            case ESTADO_6:
                plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                    if (p.getTipo().equals(TipoPlantilla.MEMORANDUM) || p.getTipo().equals(TipoPlantilla.RESOLUCION))
                    {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                break;
            case ESTADO_14:
                plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                    if (p.getTipo().equals(TipoPlantilla.NOTIFICACION))
                    {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                break;
            case ESTADO_19:
                plantillasEnabled = new ArrayList<>(this.plantillaService.getAll()).stream().filter(p -> {
                    if (p.getTipo().equals(TipoPlantilla.MEMORANDUM))
                    {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                break;
        }

        return plantillasEnabled;
    }

    @Transactional
    public Set<ProvidenciaDTO> findAllByRunOrEntidadImplicada(String runImplicado, Long entidadImplicadaId, Long providenciaId)
    {
        if (runImplicado != null && entidadImplicadaId != 0)
        {
            return this.providenciaRepository.findAllByRunOrEntidadImplicada(runImplicado,
                this.entidadMapper.toEntity(this.entidadService.findOne(entidadImplicadaId).get()), providenciaId).stream()
                .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
        }
        else if (runImplicado == null && entidadImplicadaId != 0)
        {
            return this.providenciaRepository.findAllByRunOrEntidadImplicada(null,
                this.entidadMapper.toEntity(this.entidadService.findOne(entidadImplicadaId).get()), providenciaId).stream()
                .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
        }
        else if (runImplicado != null && entidadImplicadaId == 0)
        {
            return this.providenciaRepository.findAllByRunOrEntidadImplicada(runImplicado, null, providenciaId).stream()
                .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
        }

        return new HashSet<>();
    }

    @Transactional
    public ProvidenciaDTO updateNombreFiscalAsignado(ProvidenciaDTO providenciaDTO)
    {
        Providencia providencia = this.providenciaRepository.save(this.providenciaMapper.toEntity(providenciaDTO));
        return this.providenciaMapper.toDto(providencia);
    }

    @Transactional
    public Set<ProvidenciaDTO> findAllWithoutPagination()
    {
        return this.providenciaRepository.findAll().stream().map(this.providenciaMapper::toDto).collect(Collectors.toSet());
    }

    @Transactional
    public Set<ProvidenciaDTO> findAllByProvidenciaMadre(Long providenciaMadreId)
    {
        return this.providenciaRepository.findAll().stream()
            .filter(providencia ->
                providencia.getProvidenciaMadre().getId().longValue() == providenciaMadreId.longValue()
                    && providencia.getId().longValue() != providenciaMadreId.longValue())
            .map(this.providenciaMapper::toDto).collect(Collectors.toSet());
    }

    @Transactional
    public ProvidenciaDTO updateProvidenciaMadre(ProvidenciaUpdateMadreDTO providenciaUpdateMadreDTO)
    {
        Providencia providencia = null;

        if (providenciaUpdateMadreDTO != null && providenciaUpdateMadreDTO.getProvidenciaId() != null
            && providenciaUpdateMadreDTO.getProvidenciaId() > 0 && providenciaUpdateMadreDTO.getProvidenciaMadreId()
            != null && providenciaUpdateMadreDTO.getProvidenciaMadreId() > 0)
        {
            this.providenciaRepository.updateProvidenciaMadre(providenciaUpdateMadreDTO.getProvidenciaMadreId(),
                providenciaUpdateMadreDTO.getProvidenciaId());

            providencia = this.providenciaRepository.getOne(providenciaUpdateMadreDTO.getProvidenciaId());
        }

        return this.providenciaMapper.toDto(providencia);
    }

    @Transactional
    public ProvidenciaDTO updateNumeroReferencia(ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO)
    {
        Providencia providencia = null;

        if (providenciaUpdateNumeroReferenciaDTO.getProvidenciaId() != null && providenciaUpdateNumeroReferenciaDTO
            .getProvidenciaId() > 0 && providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia() != null &&
            providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia() > 0)
        {
            this.providenciaRepository.updateNumeroReferencia(providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia(),
                providenciaUpdateNumeroReferenciaDTO.getProvidenciaId());

            providencia = this.providenciaRepository.getOne(providenciaUpdateNumeroReferenciaDTO.getProvidenciaId());
        }

        return this.providenciaMapper.toDto(providencia);
    }

}
