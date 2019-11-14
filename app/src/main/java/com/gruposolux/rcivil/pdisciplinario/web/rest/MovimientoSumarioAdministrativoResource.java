package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.MovimientoSumarioAdministrativoService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoSumarioAdministrativoDTO;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MovimientoSumarioAdministrativo.
 */
@RestController
@RequestMapping("/api")
public class MovimientoSumarioAdministrativoResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoSumarioAdministrativoResource.class);

    private static final String ENTITY_NAME = "movimientoSumarioAdministrativo";

    private final MovimientoSumarioAdministrativoService movimientoSumarioAdministrativoService;

    public MovimientoSumarioAdministrativoResource(MovimientoSumarioAdministrativoService movimientoSumarioAdministrativoService) {
        this.movimientoSumarioAdministrativoService = movimientoSumarioAdministrativoService;
    }

    /**
     * POST  /movimiento-sumario-administrativos : Create a new movimientoSumarioAdministrativo.
     *
     * @param movimientoSumarioAdministrativoDTO the movimientoSumarioAdministrativoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movimientoSumarioAdministrativoDTO, or with status 400 (Bad Request) if the movimientoSumarioAdministrativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movimiento-sumario-administrativos")
    @Timed
    public ResponseEntity<MovimientoSumarioAdministrativoDTO> createMovimientoSumarioAdministrativo(@RequestBody MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO) throws URISyntaxException {
        log.debug("REST request to save MovimientoSumarioAdministrativo : {}", movimientoSumarioAdministrativoDTO);
        if (movimientoSumarioAdministrativoDTO.getId() != null) {
            throw new BadRequestAlertException("A new movimientoSumarioAdministrativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimientoSumarioAdministrativoDTO result = movimientoSumarioAdministrativoService.save(movimientoSumarioAdministrativoDTO);
        return ResponseEntity.created(new URI("/api/movimiento-sumario-administrativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movimiento-sumario-administrativos : Updates an existing movimientoSumarioAdministrativo.
     *
     * @param movimientoSumarioAdministrativoDTO the movimientoSumarioAdministrativoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movimientoSumarioAdministrativoDTO,
     * or with status 400 (Bad Request) if the movimientoSumarioAdministrativoDTO is not valid,
     * or with status 500 (Internal Server Error) if the movimientoSumarioAdministrativoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movimiento-sumario-administrativos")
    @Timed
    public ResponseEntity<MovimientoSumarioAdministrativoDTO> updateMovimientoSumarioAdministrativo(@RequestBody MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO) throws URISyntaxException {
        log.debug("REST request to update MovimientoSumarioAdministrativo : {}", movimientoSumarioAdministrativoDTO);
        if (movimientoSumarioAdministrativoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovimientoSumarioAdministrativoDTO result = movimientoSumarioAdministrativoService.save(movimientoSumarioAdministrativoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movimientoSumarioAdministrativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movimiento-sumario-administrativos : get all the movimientoSumarioAdministrativos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of movimientoSumarioAdministrativos in body
     */
    @GetMapping("/movimiento-sumario-administrativos")
    @Timed
    public ResponseEntity<List<MovimientoSumarioAdministrativoDTO>> getAllMovimientoSumarioAdministrativos(Pageable pageable) {
        log.debug("REST request to get a page of MovimientoSumarioAdministrativos");
        Page<MovimientoSumarioAdministrativoDTO> page = movimientoSumarioAdministrativoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/movimiento-sumario-administrativos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /movimiento-sumario-administrativos/:id : get the "id" movimientoSumarioAdministrativo.
     *
     * @param id the id of the movimientoSumarioAdministrativoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movimientoSumarioAdministrativoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/movimiento-sumario-administrativos/{id}")
    @Timed
    public ResponseEntity<MovimientoSumarioAdministrativoDTO> getMovimientoSumarioAdministrativo(@PathVariable Long id) {
        log.debug("REST request to get MovimientoSumarioAdministrativo : {}", id);
        Optional<MovimientoSumarioAdministrativoDTO> movimientoSumarioAdministrativoDTO = movimientoSumarioAdministrativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movimientoSumarioAdministrativoDTO);
    }

    /**
     * DELETE  /movimiento-sumario-administrativos/:id : delete the "id" movimientoSumarioAdministrativo.
     *
     * @param id the id of the movimientoSumarioAdministrativoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movimiento-sumario-administrativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovimientoSumarioAdministrativo(@PathVariable Long id) {
        log.debug("REST request to delete MovimientoSumarioAdministrativo : {}", id);
        movimientoSumarioAdministrativoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
