package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.SumarioAdministrativoService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.SumarioAdministrativoDTO;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SumarioAdministrativo.
 */
@RestController
@RequestMapping("/api")
public class SumarioAdministrativoResource {

    private final Logger log = LoggerFactory.getLogger(SumarioAdministrativoResource.class);

    private static final String ENTITY_NAME = "sumarioAdministrativo";

    private final SumarioAdministrativoService sumarioAdministrativoService;

    public SumarioAdministrativoResource(SumarioAdministrativoService sumarioAdministrativoService) {
        this.sumarioAdministrativoService = sumarioAdministrativoService;
    }

    /**
     * POST  /sumario-administrativos : Create a new sumarioAdministrativo.
     *
     * @param sumarioAdministrativoDTO the sumarioAdministrativoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sumarioAdministrativoDTO, or with status 400 (Bad Request) if the sumarioAdministrativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sumario-administrativos")
    @Timed
    public ResponseEntity<SumarioAdministrativoDTO> createSumarioAdministrativo(@RequestBody SumarioAdministrativoDTO sumarioAdministrativoDTO) throws URISyntaxException {
        log.debug("REST request to save SumarioAdministrativo : {}", sumarioAdministrativoDTO);
        if (sumarioAdministrativoDTO.getId() != null) {
            throw new BadRequestAlertException("A new sumarioAdministrativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SumarioAdministrativoDTO result = sumarioAdministrativoService.save(sumarioAdministrativoDTO);
        return ResponseEntity.created(new URI("/api/sumario-administrativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sumario-administrativos : Updates an existing sumarioAdministrativo.
     *
     * @param sumarioAdministrativoDTO the sumarioAdministrativoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sumarioAdministrativoDTO,
     * or with status 400 (Bad Request) if the sumarioAdministrativoDTO is not valid,
     * or with status 500 (Internal Server Error) if the sumarioAdministrativoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sumario-administrativos")
    @Timed
    public ResponseEntity<SumarioAdministrativoDTO> updateSumarioAdministrativo(@RequestBody SumarioAdministrativoDTO sumarioAdministrativoDTO) throws URISyntaxException {
        log.debug("REST request to update SumarioAdministrativo : {}", sumarioAdministrativoDTO);
        if (sumarioAdministrativoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SumarioAdministrativoDTO result = sumarioAdministrativoService.save(sumarioAdministrativoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sumarioAdministrativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sumario-administrativos : get all the sumarioAdministrativos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sumarioAdministrativos in body
     */
    @GetMapping("/sumario-administrativos")
    @Timed
    public ResponseEntity<List<SumarioAdministrativoDTO>> getAllSumarioAdministrativos(Pageable pageable) {
        log.debug("REST request to get a page of SumarioAdministrativos");
        Page<SumarioAdministrativoDTO> page = sumarioAdministrativoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sumario-administrativos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sumario-administrativos/:id : get the "id" sumarioAdministrativo.
     *
     * @param id the id of the sumarioAdministrativoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sumarioAdministrativoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sumario-administrativos/{id}")
    @Timed
    public ResponseEntity<SumarioAdministrativoDTO> getSumarioAdministrativo(@PathVariable Long id) {
        log.debug("REST request to get SumarioAdministrativo : {}", id);
        Optional<SumarioAdministrativoDTO> sumarioAdministrativoDTO = sumarioAdministrativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sumarioAdministrativoDTO);
    }

    /**
     * DELETE  /sumario-administrativos/:id : delete the "id" sumarioAdministrativo.
     *
     * @param id the id of the sumarioAdministrativoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sumario-administrativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSumarioAdministrativo(@PathVariable Long id) {
        log.debug("REST request to delete SumarioAdministrativo : {}", id);
        sumarioAdministrativoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
