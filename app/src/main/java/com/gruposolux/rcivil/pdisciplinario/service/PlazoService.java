package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.repository.PlazoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlazoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.PlazoMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Plazo.
 */
@Service
@Transactional
public class PlazoService {

    private final Logger log = LoggerFactory.getLogger(PlazoService.class);

    private final PlazoRepository plazoRepository;

    private final PlazoMapper plazoMapper;

    public PlazoService(PlazoRepository plazoRepository, PlazoMapper plazoMapper) {
        this.plazoRepository = plazoRepository;
        this.plazoMapper = plazoMapper;
    }

    /**
     * Save a plazo.
     *
     * @param plazoDTO the entity to save
     * @return the persisted entity
     */
    public PlazoDTO save(PlazoDTO plazoDTO) {
        log.debug("Request to save Plazo : {}", plazoDTO);
        Plazo plazo = plazoMapper.toEntity(plazoDTO);
        plazo = plazoRepository.save(plazo);
        return plazoMapper.toDto(plazo);
    }

    /**
     * Get all the plazos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PlazoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Plazos");
        return plazoRepository.findAll(pageable)
            .map(plazoMapper::toDto);
    }


    /**
     * Get one plazo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PlazoDTO> findOne(Long id) {
        log.debug("Request to get Plazo : {}", id);
        return plazoRepository.findById(id)
            .map(plazoMapper::toDto);
    }

    /**
     * Delete the plazo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Plazo : {}", id);
        plazoRepository.deleteById(id);
    }

    @Transactional
    public List<PlazoDTO> getAllPlazos()
    {
        List<Plazo> plazos = this.plazoRepository.findAll();
        List<PlazoDTO> plazoDTOs = new ArrayList<>();

        for(Plazo plazo: plazos)
        {
            PlazoDTO plazoDTO = this.plazoMapper.toDto(plazo);
            plazoDTOs.add(plazoDTO);
        }

        return plazoDTOs;
    }
}
