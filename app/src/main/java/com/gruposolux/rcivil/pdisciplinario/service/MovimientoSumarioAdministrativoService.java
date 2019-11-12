package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoSumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.repository.MovimientoSumarioAdministrativoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoSumarioAdministrativoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.MovimientoSumarioAdministrativoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MovimientoSumarioAdministrativo.
 */
@Service
@Transactional
public class MovimientoSumarioAdministrativoService {

    private final Logger log = LoggerFactory.getLogger(MovimientoSumarioAdministrativoService.class);

    private final MovimientoSumarioAdministrativoRepository movimientoSumarioAdministrativoRepository;

    private final MovimientoSumarioAdministrativoMapper movimientoSumarioAdministrativoMapper;

    public MovimientoSumarioAdministrativoService(MovimientoSumarioAdministrativoRepository movimientoSumarioAdministrativoRepository, MovimientoSumarioAdministrativoMapper movimientoSumarioAdministrativoMapper) {
        this.movimientoSumarioAdministrativoRepository = movimientoSumarioAdministrativoRepository;
        this.movimientoSumarioAdministrativoMapper = movimientoSumarioAdministrativoMapper;
    }

    /**
     * Save a movimientoSumarioAdministrativo.
     *
     * @param movimientoSumarioAdministrativoDTO the entity to save
     * @return the persisted entity
     */
    public MovimientoSumarioAdministrativoDTO save(MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO) {
        log.debug("Request to save MovimientoSumarioAdministrativo : {}", movimientoSumarioAdministrativoDTO);
        MovimientoSumarioAdministrativo movimientoSumarioAdministrativo = movimientoSumarioAdministrativoMapper.toEntity(movimientoSumarioAdministrativoDTO);
        movimientoSumarioAdministrativo = movimientoSumarioAdministrativoRepository.save(movimientoSumarioAdministrativo);
        return movimientoSumarioAdministrativoMapper.toDto(movimientoSumarioAdministrativo);
    }

    /**
     * Get all the movimientoSumarioAdministrativos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MovimientoSumarioAdministrativoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MovimientoSumarioAdministrativos");
        return movimientoSumarioAdministrativoRepository.findAll(pageable)
            .map(movimientoSumarioAdministrativoMapper::toDto);
    }


    /**
     * Get one movimientoSumarioAdministrativo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MovimientoSumarioAdministrativoDTO> findOne(Long id) {
        log.debug("Request to get MovimientoSumarioAdministrativo : {}", id);
        return movimientoSumarioAdministrativoRepository.findById(id)
            .map(movimientoSumarioAdministrativoMapper::toDto);
    }

    /**
     * Delete the movimientoSumarioAdministrativo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MovimientoSumarioAdministrativo : {}", id);
        movimientoSumarioAdministrativoRepository.deleteById(id);
    }
}
