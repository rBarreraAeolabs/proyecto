package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.repository.FichaIngresoSdjRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FichaIngresoSdjDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.FichaIngresoSdjMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.FichaIngresoSdj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing FichaIngresoSdj.
 */
@Service
@Transactional
public class FichaIngresoSdjService {

    private final Logger log = LoggerFactory.getLogger(FichaIngresoSdjService.class);

    private final FichaIngresoSdjRepository fichaIngresoSdjRepository;

    private final FichaIngresoSdjMapper fichaIngresoSdjMapper;

    public FichaIngresoSdjService(FichaIngresoSdjRepository fichaIngresoSdjRepository, FichaIngresoSdjMapper fichaIngresoSdjMapper) {
        this.fichaIngresoSdjRepository = fichaIngresoSdjRepository;
        this.fichaIngresoSdjMapper = fichaIngresoSdjMapper;
    }

    /**
     * Save a fichaIngresoSdj.
     *
     * @param fichaIngresoSdjDTO the entity to save
     * @return the persisted entity
     */
    public FichaIngresoSdjDTO save(FichaIngresoSdjDTO fichaIngresoSdjDTO) {
        log.debug("Request to save FichaIngresoSdj : {}", fichaIngresoSdjDTO);
        FichaIngresoSdj fichaIngresoSdj = fichaIngresoSdjMapper.toEntity(fichaIngresoSdjDTO);
        fichaIngresoSdj = fichaIngresoSdjRepository.save(fichaIngresoSdj);
        return fichaIngresoSdjMapper.toDto(fichaIngresoSdj);
    }

    /**
     * Get all the fichaIngresoSdjs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FichaIngresoSdjDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FichaIngresoSdjs");
        return fichaIngresoSdjRepository.findAll(pageable)
            .map(fichaIngresoSdjMapper::toDto);
    }


    /**
     * get all the fichaIngresoSdjs where Providencia is null.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FichaIngresoSdjDTO> findAllWhereProvidenciaIsNull() {
        log.debug("Request to get all fichaIngresoSdjs where Providencia is null");
        return StreamSupport
            .stream(fichaIngresoSdjRepository.findAll().spliterator(), false)
            .map(fichaIngresoSdjMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fichaIngresoSdj by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FichaIngresoSdjDTO> findOne(Long id) {
        log.debug("Request to get FichaIngresoSdj : {}", id);
        return fichaIngresoSdjRepository.findById(id)
            .map(fichaIngresoSdjMapper::toDto);
    }

    /**
     * Delete the fichaIngresoSdj by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FichaIngresoSdj : {}", id);
        fichaIngresoSdjRepository.deleteById(id);
    }
}
