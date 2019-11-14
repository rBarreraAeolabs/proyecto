package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.repository.InvestigacionSumariaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InvestigacionSumariaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.InvestigacionSumariaMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing InvestigacionSumaria.
 */
@Service
@Transactional
public class InvestigacionSumariaService {

    private final Logger log = LoggerFactory.getLogger(InvestigacionSumariaService.class);

    private final InvestigacionSumariaRepository investigacionSumariaRepository;

    private final InvestigacionSumariaMapper investigacionSumariaMapper;

    public InvestigacionSumariaService(InvestigacionSumariaRepository investigacionSumariaRepository, InvestigacionSumariaMapper investigacionSumariaMapper) {
        this.investigacionSumariaRepository = investigacionSumariaRepository;
        this.investigacionSumariaMapper = investigacionSumariaMapper;
    }

    /**
     * Save a investigacionSumaria.
     *
     * @param investigacionSumariaDTO the entity to save
     * @return the persisted entity
     */
    public InvestigacionSumariaDTO save(InvestigacionSumariaDTO investigacionSumariaDTO) {
        log.debug("Request to save InvestigacionSumaria : {}", investigacionSumariaDTO);
        InvestigacionSumaria investigacionSumaria = investigacionSumariaMapper.toEntity(investigacionSumariaDTO);
        investigacionSumaria = investigacionSumariaRepository.save(investigacionSumaria);
        return investigacionSumariaMapper.toDto(investigacionSumaria);
    }

    /**
     * Get all the investigacionSumarias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<InvestigacionSumariaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvestigacionSumarias");
        return investigacionSumariaRepository.findAll(pageable)
            .map(investigacionSumariaMapper::toDto);
    }


    /**
     * Get one investigacionSumaria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<InvestigacionSumariaDTO> findOne(Long id) {
        log.debug("Request to get InvestigacionSumaria : {}", id);
        return investigacionSumariaRepository.findById(id)
            .map(investigacionSumariaMapper::toDto);
    }

    /**
     * Delete the investigacionSumaria by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InvestigacionSumaria : {}", id);
        investigacionSumariaRepository.deleteById(id);
    }
}
