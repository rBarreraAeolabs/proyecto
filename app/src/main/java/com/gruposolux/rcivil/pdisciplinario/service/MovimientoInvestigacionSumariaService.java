package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoInvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.repository.MovimientoInvestigacionSumariaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoInvestigacionSumariaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.MovimientoInvestigacionSumariaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MovimientoInvestigacionSumaria.
 */
@Service
@Transactional
public class MovimientoInvestigacionSumariaService {

    private final Logger log = LoggerFactory.getLogger(MovimientoInvestigacionSumariaService.class);

    private final MovimientoInvestigacionSumariaRepository movimientoInvestigacionSumariaRepository;

    private final MovimientoInvestigacionSumariaMapper movimientoInvestigacionSumariaMapper;

    public MovimientoInvestigacionSumariaService(MovimientoInvestigacionSumariaRepository movimientoInvestigacionSumariaRepository, MovimientoInvestigacionSumariaMapper movimientoInvestigacionSumariaMapper) {
        this.movimientoInvestigacionSumariaRepository = movimientoInvestigacionSumariaRepository;
        this.movimientoInvestigacionSumariaMapper = movimientoInvestigacionSumariaMapper;
    }

    /**
     * Save a movimientoInvestigacionSumaria.
     *
     * @param movimientoInvestigacionSumariaDTO the entity to save
     * @return the persisted entity
     */
    public MovimientoInvestigacionSumariaDTO save(MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO) {
        log.debug("Request to save MovimientoInvestigacionSumaria : {}", movimientoInvestigacionSumariaDTO);
        MovimientoInvestigacionSumaria movimientoInvestigacionSumaria = movimientoInvestigacionSumariaMapper.toEntity(movimientoInvestigacionSumariaDTO);
        movimientoInvestigacionSumaria = movimientoInvestigacionSumariaRepository.save(movimientoInvestigacionSumaria);
        return movimientoInvestigacionSumariaMapper.toDto(movimientoInvestigacionSumaria);
    }

    /**
     * Get all the movimientoInvestigacionSumarias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MovimientoInvestigacionSumariaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MovimientoInvestigacionSumarias");
        return movimientoInvestigacionSumariaRepository.findAll(pageable)
            .map(movimientoInvestigacionSumariaMapper::toDto);
    }


    /**
     * Get one movimientoInvestigacionSumaria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MovimientoInvestigacionSumariaDTO> findOne(Long id) {
        log.debug("Request to get MovimientoInvestigacionSumaria : {}", id);
        return movimientoInvestigacionSumariaRepository.findById(id)
            .map(movimientoInvestigacionSumariaMapper::toDto);
    }

    /**
     * Delete the movimientoInvestigacionSumaria by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MovimientoInvestigacionSumaria : {}", id);
        movimientoInvestigacionSumariaRepository.deleteById(id);
    }
}
