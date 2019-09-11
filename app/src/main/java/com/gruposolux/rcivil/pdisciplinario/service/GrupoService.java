package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.repository.AuthorityRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.GrupoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.GrupoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.GrupoMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Grupo.
 */
@Service
@Transactional
public class GrupoService {

    private final Logger log = LoggerFactory.getLogger(GrupoService.class);

    private final GrupoRepository grupoRepository;

    private final GrupoMapper grupoMapper;

    public GrupoService(GrupoRepository grupoRepository, GrupoMapper grupoMapper) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
    }

    /**
     * Save a grupo.
     *
     * @param grupoDTO the entity to save
     * @return the persisted entity
     */
    public GrupoDTO save(GrupoDTO grupoDTO) {
        log.debug("Request to save Grupo : {}", grupoDTO);
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        grupo = grupoRepository.save(grupo);
        return grupoMapper.toDto(grupo);
    }

    /**
     * Get all the grupos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<GrupoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Grupos");
        return grupoRepository.findAll(pageable)
            .map(grupoMapper::toDto);
    }

    /**
     * Get one grupo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<GrupoDTO> findOne(Long id) {
        log.debug("Request to get Grupo : {}", id);
        return grupoRepository.findById(id)
            .map(grupoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Grupo findOneByName(String nombre) {
        log.debug("Request to get Grupo by nombre : {}", nombre);
        return grupoRepository.FindOneByName(nombre);
    }

    /**
     * Delete the grupo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Grupo : {}", id);
        grupoRepository.deleteById(id);
    }

    @Transactional
    public List<GrupoDTO> getAllAsList() {
        return this.grupoRepository.findAll().stream().map(grupo -> this.grupoMapper.toDto(grupo)).collect(Collectors.toList());
    }
}
