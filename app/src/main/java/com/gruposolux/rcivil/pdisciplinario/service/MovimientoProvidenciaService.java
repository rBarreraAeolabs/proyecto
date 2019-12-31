package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.repository.MovimientoProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.MovimientoProvidenciaMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.PlazoMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MovimientoProvidencia.
 */
@Service
@Transactional
public class MovimientoProvidenciaService {

    private final Logger log = LoggerFactory.getLogger(MovimientoProvidenciaService.class);
    private final MovimientoProvidenciaRepository movimientoProvidenciaRepository;
    private final MovimientoProvidenciaMapper movimientoProvidenciaMapper;
    private final ProvidenciaMapper providenciaMapper;
    private final DocumentoService documentoService;
    private final AdjuntoService adjuntoService;
    private final ProvidenciaRepository providenciaRepository;
    private final PlazoService plazoService;
    private final PlazoMapper plazoMapper;
    private final UserService userService;
    private final RespuestaService respuestaService;

    public MovimientoProvidenciaService(
        MovimientoProvidenciaRepository movimientoProvidenciaRepository,
        MovimientoProvidenciaMapper movimientoProvidenciaMapper,
        ProvidenciaMapper providenciaMapper,
        DocumentoService documentoService,
        AdjuntoService adjuntoService,
        ProvidenciaRepository providenciaRepository,
        PlazoService plazoService,
        PlazoMapper plazoMapper,
        UserService userService,
        RespuestaService respuestaService
    ) {
        this.movimientoProvidenciaRepository = movimientoProvidenciaRepository;
        this.movimientoProvidenciaMapper = movimientoProvidenciaMapper;
        this.providenciaMapper = providenciaMapper;
        this.documentoService = documentoService;
        this.adjuntoService = adjuntoService;
        this.providenciaRepository = providenciaRepository;
        this.plazoService = plazoService;
        this.plazoMapper = plazoMapper;
        this.userService = userService;
        this.respuestaService = respuestaService;
    }


    public MovimientoProvidenciaDTO save(MovimientoProvidenciaDTO movimientoProvidenciaDTO)
    {
        MovimientoProvidencia movimientoProvidencia = this.movimientoProvidenciaMapper.toEntity(movimientoProvidenciaDTO);
        movimientoProvidencia = this.movimientoProvidenciaRepository.save(movimientoProvidencia);
        return this.movimientoProvidenciaMapper.toDto(movimientoProvidencia);
    }

    /**
     * Save a movimientoProvidencia.
     *
     * @return the persisted entity
     */
    public MovimientoProvidenciaDTO save(String estadoAnterior, String estadoNuevo,
                                         Long providenciaId, String comentario, Set<DocumentoDTO> documentoDTOs,
                                         Set<AdjuntoDTO> adjuntoDTOs, String accion, Long numero_dgd, Long numero_dgdp) {
        log.debug("Request to save MovimientoProvidencia : {}");

        MovimientoProvidencia movimientoProvidencia = new MovimientoProvidencia();
        movimientoProvidencia.setFecha(Instant.now());
        movimientoProvidencia.setUser(this.userService.getCurrentUser());
        movimientoProvidencia.setEstadoNuevo(estadoNuevo);
        movimientoProvidencia.setComentario(comentario);
        movimientoProvidencia.setAccion(accion);
        movimientoProvidencia.setNumero_dgd(numero_dgd);
        movimientoProvidencia.setNumero_dgdp(numero_dgdp);
        movimientoProvidencia.setProvidencia(this.providenciaRepository.findById(providenciaId).get());

        Plazo plazo = this.determinePlazo(estadoAnterior, estadoNuevo);

        if (plazo != null)
        {
            movimientoProvidencia.setPlazo(plazo);
        }

        if (estadoAnterior != null)
        {
            movimientoProvidencia.setEstadoAnterior(estadoAnterior);
        }

        movimientoProvidencia = this.movimientoProvidenciaRepository.save(movimientoProvidencia);

        if (documentoDTOs != null && documentoDTOs.size() > 0)
        {
            for (Iterator<DocumentoDTO> it = documentoDTOs.iterator(); it.hasNext();)
            {
                DocumentoDTO documentoDTO = it.next();
                documentoDTO.setMovimientoProvidenciaId(movimientoProvidencia.getId());
                this.documentoService.update(documentoDTO);
            }
        }

        if (adjuntoDTOs != null && adjuntoDTOs.size() > 0)
        {
            for (Iterator<AdjuntoDTO> it = adjuntoDTOs.iterator(); it.hasNext();)
            {
                AdjuntoDTO adjuntoDTO = it.next();
                adjuntoDTO.setMovimientoProvidenciaId(movimientoProvidencia.getId());
                this.adjuntoService.save(adjuntoDTO);
            }
        }
        movimientoProvidencia = movimientoProvidenciaRepository.save(movimientoProvidencia);
        this.respuestaService.updateMovimientoProvidencia(movimientoProvidencia.getId(),
            movimientoProvidencia.getProvidencia().getId(), estadoAnterior);
        return movimientoProvidenciaMapper.toDto(movimientoProvidencia);
    }

    private Plazo determinePlazo(String estadoAntiguo, String estadoNuevo)
    {
        Optional<PlazoDTO> optionalPlazo = Optional.empty();
        Plazo plazo = null;

        if (estadoAntiguo != null && estadoAntiguo.equals(EstadoProvidencia.FISCAL_REMITE_EXPEDIENTE) && estadoNuevo.equals(EstadoProvidencia.PROVIDENCIA_CREADA))
        {
            optionalPlazo = this.plazoService.findOne(1L);
            if (optionalPlazo.isPresent())
            {
                plazo = this.plazoMapper.toEntity(optionalPlazo.get());
            }
        }
        return plazo;
    }

    /**
     * Get all the movimientoProvidencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MovimientoProvidenciaDTO> findAll(Pageable pageable, FiltroMovimientoProvidenciaDTO filtro) {
        log.debug("Request to get all MovimientoProvidencias");

        if (filtro != null)
        {
            if (filtro.getAccion() != null)
            {
                List<MovimientoProvidenciaDTO> list = movimientoProvidenciaRepository.findAll(pageable)
                    .filter(mov -> mov.getAccion().equalsIgnoreCase(filtro.getAccion())).stream()
                    .map(this.movimientoProvidenciaMapper::toDto)
                    .collect(Collectors.toList());

                return new PageImpl<MovimientoProvidenciaDTO>(list);
            }

            if (filtro.getTipoAdjunto() != null)
            {
                List<MovimientoProvidenciaDTO> list = movimientoProvidenciaRepository.findAll(pageable)
                    .filter(mov -> mov.getEstadoNuevo().equalsIgnoreCase(filtro.getEstadoProvidencia().name()))
                    .stream().map(this.movimientoProvidenciaMapper::toDto).collect(Collectors.toList());

                return new PageImpl<MovimientoProvidenciaDTO>(list);
            }

//            if (filtro.getTipoAdjunto() != null)
//            {
//                List<MovimientoProvidenciaDTO> list = movimientoProvidenciaRepository.findAll(pageable)
//                    .filter(mov -> mov.get.name().equalsIgnoreCase(filtro.getEstadoProvidencia().name()))
//                    .stream().map(this.movimientoProvidenciaMapper::toDto).collect(Collectors.toList());
//
//                return new PageImpl<MovimientoProvidenciaDTO>(list);
//            }
        }

        return movimientoProvidenciaRepository.findAll(pageable)
            .map(movimientoProvidenciaMapper::toDto);
    }


    /**
     * Get one movimientoProvidencia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MovimientoProvidenciaDTO> findOne(Long id) {
        log.debug("Request to get MovimientoProvidencia : {}", id);
        return movimientoProvidenciaRepository.findById(id)
            .map(movimientoProvidenciaMapper::toDto);
    }

    /**
     * Delete the movimientoProvidencia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MovimientoProvidencia : {}", id);
        movimientoProvidenciaRepository.deleteById(id);
    }

    @Transactional
    public Set<MovimientoProvidenciaDTO> getAllByIdProvidencia(ProvidenciaDTO providenciaDTO)
    {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);
        Set<MovimientoProvidencia> movimientos = this.movimientoProvidenciaRepository.findByProvidencia(providencia);

        Set<MovimientoProvidenciaDTO> movimientoProvidenciaDTOs = new TreeSet<>();
        for (Iterator<MovimientoProvidencia> it = movimientos.iterator(); it.hasNext();)
        {
            MovimientoProvidencia movimientoProvidencia = it.next();

            movimientoProvidenciaDTOs.add(this.movimientoProvidenciaMapper.toDto(movimientoProvidencia));
        }

        return movimientoProvidenciaDTOs;
    }


    @Transactional
    public MovimientoProvidenciaDTO buscarUltimo (Long id) {


        MovimientoProvidencia movimientos = this.movimientoProvidenciaRepository.traerMovimientos(id);
        MovimientoProvidenciaDTO ultimovimiento = this.movimientoProvidenciaMapper.toDto(movimientos);
        return ultimovimiento;
    }
//    @Transactional
//    public Set<MovimientoProvidenciaDTO> traerMovimiento (Long id )
//    {        return movimientoProvidenciaRepository.traerMovimientos(id)
//            .stream()
//            .map(this.movimientoProvidenciaMapper::toDto).collect(Collectors.toSet())
//            ;
//    }


    @Transactional
    public Set<MovimientoProvidenciaDTO> getAllByIdProvidenciaWithFilters(ProvidenciaDTO providenciaDTO, FiltroMovimientoProvidenciaDTO filtro)
    {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);
        Set<MovimientoProvidencia> movimientos = this.movimientoProvidenciaRepository.findByProvidencia(providencia);
        Set<MovimientoProvidenciaDTO> movimientoProvidenciaDTOs = new TreeSet<>();

        for (Iterator<MovimientoProvidencia> it = movimientos.iterator(); it.hasNext();)
        {
            MovimientoProvidencia movimientoProvidencia = it.next();

            if(filtro != null)
            {
                if(filtro.getAccion() != null)
                {
                    if(movimientoProvidencia.getAccion() != null && movimientoProvidencia.getAccion()
                        .equalsIgnoreCase(filtro.getAccion()))
                    {
                        movimientoProvidenciaDTOs.add(this.movimientoProvidenciaMapper.toDto(movimientoProvidencia));
                    }
                }

                if(filtro.getEstadoProvidencia() != null)
                {
                    if (movimientoProvidencia.getEstadoAnterior().equals(filtro.getEstadoProvidencia()))
                    {
                        movimientoProvidenciaDTOs.add(this.movimientoProvidenciaMapper.toDto(movimientoProvidencia));
                    }
                }

                if(filtro.getTipoAdjunto() != null)
                {
//                    if (movimientoProvidencia)
                }
            }
            else
            {

                movimientoProvidenciaDTOs.add(this.movimientoProvidenciaMapper.toDto(movimientoProvidencia));
            }
        }

        return movimientoProvidenciaDTOs;
    }

    @Transactional
    public Set<MovimientoProvidenciaDTO> getAllWithoutPagination() {
        List<MovimientoProvidencia> movimientosList = this.movimientoProvidenciaRepository.findAll();
        Set<MovimientoProvidenciaDTO> movimientosSet = new TreeSet<>();
        movimientosSet = movimientosList.stream().map(this.movimientoProvidenciaMapper::toDto)
            .collect(Collectors.toSet());

        return movimientosSet;
    }

    @Transactional
    public MovimientoProvidencia buscarPorId(Long id)
    {
        MovimientoProvidencia  movimientoProvidencia = movimientoProvidenciaRepository.traerMovimientos(id);
        return  movimientoProvidencia;
    }

    @Transactional
    public Set<MovimientoProvidenciaDTO> movimientosDeLaProvidencia(Long id)
    {



    return
        movimientoProvidenciaRepository.traerMovimientosDeLaProvidencia(id)
            .stream().map(this.movimientoProvidenciaMapper::toDto).collect(Collectors.toSet());
    }

}
