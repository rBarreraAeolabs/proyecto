package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.repository.AuthorityRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.PerfilRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FiltroPerfilDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PerfilDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.PerfilMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Perfil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Perfil.
 */
@Service
@Transactional
public class PerfilService {

    private final Logger log = LoggerFactory.getLogger(PerfilService.class);

    private final PerfilRepository perfilRepository;

    private final PerfilMapper perfilMapper;

    private final AuthorityRepository authorityRepository;


    public PerfilService(PerfilRepository perfilRepository, PerfilMapper perfilMapper, AuthorityRepository authorityRepository) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
        this.authorityRepository = authorityRepository;
    }

    /**
     * Save a perfil.
     *
     * @param perfilDTO the entity to save
     * @return the persisted entity
     */
    public PerfilDTO save(PerfilDTO perfilDTO) {
        log.debug("Request to save Perfil : {}", perfilDTO);
        System.out.println("perfilDTO");
        System.out.println(perfilDTO);
        Perfil perfil = perfilMapper.toEntity(perfilDTO);
        perfil = perfilRepository.save(perfil);
        return perfilMapper.toDto(perfil);
    }

    /**
     * Get all the perfils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PerfilDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Perfils");
        return perfilRepository.findAll(pageable)
            .map(perfilMapper::toDto);
    }

    /**
     * Get all the grupos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Perfil> findAllPerfil(Pageable pageable, FiltroPerfilDTO filtro) {
        log.debug("Get all Perfiles");

        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty())
        {
            pageable = new PageImpl<Perfil>(new ArrayList<Perfil>()).getPageable();
            return this.perfilRepository.FindOneByPerfilName(pageable, filtro.getNombre());
        }
        return perfilRepository.findAll(pageable);
    }

    /**
     * Get all the Perfil with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PerfilDTO> findAllWithEagerRelationships(Pageable pageable) {
        return perfilRepository.findAllWithEagerRelationships(pageable).map(perfilMapper::toDto);
    }


    /**
     * Get one perfil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PerfilDTO> findOne(Long id) {
        log.debug("Request to get Perfil : {}", id);
        return perfilRepository.findOneWithEagerRelationships(id)
            .map(perfilMapper::toDto);
    }

    /**
     * Delete the perfil by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Perfil : {}", id);
        perfilRepository.deleteById(id);
    }


    /**
     *
     * @return list de todas las autoridades de jhipster y roles
     */
    public List<String> getAuthorities(){
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }
}
