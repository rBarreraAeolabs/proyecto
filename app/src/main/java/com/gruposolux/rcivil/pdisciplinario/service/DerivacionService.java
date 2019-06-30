package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.repository.DerivacionRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DerivacionDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.DerivacionMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Derivacion.
 */
@Service
@Transactional
public class DerivacionService {

    private final Logger log = LoggerFactory.getLogger(DerivacionService.class);
    private final DerivacionRepository derivacionRepository;
    private final DerivacionMapper derivacionMapper;
    private final ProvidenciaService providenciaService;
    private final ProvidenciaMapper providenciaMapper;

    public DerivacionService(
        DerivacionRepository derivacionRepository,
        DerivacionMapper derivacionMapper,
        ApplicationEventPublisher publisher,
        ProvidenciaService providenciaService,
        ProvidenciaMapper providenciaMapper) {
        this.derivacionRepository = derivacionRepository;
        this.derivacionMapper = derivacionMapper;
        this.providenciaService = providenciaService;
        this.providenciaMapper = providenciaMapper;
    }

    /**
     * Save a derivacion.
     *
     * @param derivacionDTO the entity to save
     * @return the persisted entity
     */
    public DerivacionDTO save(DerivacionDTO derivacionDTO) {
        log.debug("Request to save Derivacion : {}", derivacionDTO);
        Derivacion derivacion = derivacionMapper.toEntity(derivacionDTO);
        derivacion = derivacionRepository.save(derivacion);
        return derivacionMapper.toDto(derivacion);
    }

    /**
     * Get all the derivacions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DerivacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Derivacions");
        return derivacionRepository.findAll(pageable)
            .map(derivacionMapper::toDto);
    }


    /**
     * Get one derivacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DerivacionDTO> findOne(Long id) {
        log.debug("Request to get Derivacion : {}", id);
        return derivacionRepository.findById(id)
            .map(derivacionMapper::toDto);
    }

    /**
     * Delete the derivacion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Derivacion : {}", id);
        derivacionRepository.deleteById(id);
    }

    @Transactional
    public Set<DerivacionDTO> getByProvidencia(Long idProvidencia)
    {
        Optional<ProvidenciaDTO> optionalProvidencia = this.providenciaService.findOne(idProvidencia);
        if (optionalProvidencia.isPresent())
        {
            Providencia providencia = optionalProvidencia.map(this.providenciaMapper::toEntity).get();
            return this.derivacionRepository.findLastByProvidencia(providencia).stream()
                .map(this.derivacionMapper::toDto).collect(Collectors.toSet());
        }
        return null;
    }

    @Transactional
    public List<DerivacionDTO> findLastByProvidencia(ProvidenciaDTO providenciaDTO)
    {
        Providencia providencia = this.providenciaMapper.toEntity(providenciaDTO);
        return this.derivacionRepository.findLastByProvidencia(providencia).stream().map(this.derivacionMapper::toDto)
            .collect(Collectors.toList());
    }
}
