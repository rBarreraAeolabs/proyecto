package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;
import com.gruposolux.rcivil.pdisciplinario.repository.PlantillaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlantillaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.PlantillaMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Plantilla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Plantilla.
 */
@Service
@Transactional
public class PlantillaService {

    private final Logger log = LoggerFactory.getLogger(PlantillaService.class);

    private final PlantillaRepository plantillaRepository;

    private final PlantillaMapper plantillaMapper;

    public PlantillaService(PlantillaRepository plantillaRepository, PlantillaMapper plantillaMapper) {
        this.plantillaRepository = plantillaRepository;
        this.plantillaMapper = plantillaMapper;
    }

    /**
     * Save a plantilla.
     *
     * @param plantillaDTO the entity to save
     * @return the persisted entity
     */
    public PlantillaDTO save(PlantillaDTO plantillaDTO) {
        log.debug("Request to save Plantilla : {}", plantillaDTO);
        Plantilla plantilla = plantillaMapper.toEntity(plantillaDTO);
        plantilla = plantillaRepository.save(plantilla);
        return plantillaMapper.toDto(plantilla);
    }

    /**
     * Get all the plantillas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PlantillaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Plantillas");
        return plantillaRepository.findAll(pageable).map(plantillaMapper::toDto);
    }


    /**
     * Get one plantilla by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PlantillaDTO> findOne(Long id) {
        log.debug("Request to get Plantilla : {}", id);
        return plantillaRepository.findById(id)
            .map(plantillaMapper::toDto);
    }

    /**
     * Delete the plantilla by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Plantilla : {}", id);
        plantillaRepository.deleteById(id);
    }

    @Transactional
    public Set<PlantillaDTO> getAll()
    {
        List<Plantilla> plantillas = this.plantillaRepository.findAll();
        return plantillas.stream().map(this.plantillaMapper::toDto).collect(Collectors.toSet());
    }
}
