package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.repository.SumarioAdministrativoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.SumarioAdministrativoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.SumarioAdministrativoMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SumarioAdministrativo.
 */
@Service
@Transactional
public class SumarioAdministrativoService {

    private final Logger log = LoggerFactory.getLogger(SumarioAdministrativoService.class);

    private final SumarioAdministrativoRepository sumarioAdministrativoRepository;

    private final SumarioAdministrativoMapper sumarioAdministrativoMapper;

    public SumarioAdministrativoService(SumarioAdministrativoRepository sumarioAdministrativoRepository, SumarioAdministrativoMapper sumarioAdministrativoMapper) {
        this.sumarioAdministrativoRepository = sumarioAdministrativoRepository;
        this.sumarioAdministrativoMapper = sumarioAdministrativoMapper;
    }

    /**
     * Save a sumarioAdministrativo.
     *
     * @param sumarioAdministrativoDTO the entity to save
     * @return the persisted entity
     */
    public SumarioAdministrativoDTO save(SumarioAdministrativoDTO sumarioAdministrativoDTO) {
        log.debug("Request to save SumarioAdministrativo : {}", sumarioAdministrativoDTO);
        SumarioAdministrativo sumarioAdministrativo = sumarioAdministrativoMapper.toEntity(sumarioAdministrativoDTO);
        sumarioAdministrativo = sumarioAdministrativoRepository.save(sumarioAdministrativo);
        return sumarioAdministrativoMapper.toDto(sumarioAdministrativo);
    }

    /**
     * Get all the sumarioAdministrativos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SumarioAdministrativoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SumarioAdministrativos");
        return sumarioAdministrativoRepository.findAll(pageable)
            .map(sumarioAdministrativoMapper::toDto);
    }


    /**
     * Get one sumarioAdministrativo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SumarioAdministrativoDTO> findOne(Long id) {
        log.debug("Request to get SumarioAdministrativo : {}", id);
        return sumarioAdministrativoRepository.findById(id)
            .map(sumarioAdministrativoMapper::toDto);
    }

    /**
     * Delete the sumarioAdministrativo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SumarioAdministrativo : {}", id);
        sumarioAdministrativoRepository.deleteById(id);
    }
}
