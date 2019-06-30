package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.MovimientoInvestigacionSumariaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoInvestigacionSumariaDTO;
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
 * REST controller for managing MovimientoInvestigacionSumaria.
 */
@RestController
@RequestMapping("/api")
public class MovimientoInvestigacionSumariaResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoInvestigacionSumariaResource.class);

    private static final String ENTITY_NAME = "movimientoInvestigacionSumaria";

    private final MovimientoInvestigacionSumariaService movimientoInvestigacionSumariaService;

    public MovimientoInvestigacionSumariaResource(MovimientoInvestigacionSumariaService movimientoInvestigacionSumariaService) {
        this.movimientoInvestigacionSumariaService = movimientoInvestigacionSumariaService;
    }

    /**
     * POST  /movimiento-investigacion-sumarias : Create a new movimientoInvestigacionSumaria.
     *
     * @param movimientoInvestigacionSumariaDTO the movimientoInvestigacionSumariaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movimientoInvestigacionSumariaDTO, or with status 400 (Bad Request) if the movimientoInvestigacionSumaria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movimiento-investigacion-sumarias")
    @Timed
    public ResponseEntity<MovimientoInvestigacionSumariaDTO> createMovimientoInvestigacionSumaria(@RequestBody MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO) throws URISyntaxException {
        log.debug("REST request to save MovimientoInvestigacionSumaria : {}", movimientoInvestigacionSumariaDTO);
        if (movimientoInvestigacionSumariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new movimientoInvestigacionSumaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimientoInvestigacionSumariaDTO result = movimientoInvestigacionSumariaService.save(movimientoInvestigacionSumariaDTO);
        return ResponseEntity.created(new URI("/api/movimiento-investigacion-sumarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movimiento-investigacion-sumarias : Updates an existing movimientoInvestigacionSumaria.
     *
     * @param movimientoInvestigacionSumariaDTO the movimientoInvestigacionSumariaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movimientoInvestigacionSumariaDTO,
     * or with status 400 (Bad Request) if the movimientoInvestigacionSumariaDTO is not valid,
     * or with status 500 (Internal Server Error) if the movimientoInvestigacionSumariaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movimiento-investigacion-sumarias")
    @Timed
    public ResponseEntity<MovimientoInvestigacionSumariaDTO> updateMovimientoInvestigacionSumaria(@RequestBody MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO) throws URISyntaxException {
        log.debug("REST request to update MovimientoInvestigacionSumaria : {}", movimientoInvestigacionSumariaDTO);
        if (movimientoInvestigacionSumariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovimientoInvestigacionSumariaDTO result = movimientoInvestigacionSumariaService.save(movimientoInvestigacionSumariaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movimientoInvestigacionSumariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movimiento-investigacion-sumarias : get all the movimientoInvestigacionSumarias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of movimientoInvestigacionSumarias in body
     */
    @GetMapping("/movimiento-investigacion-sumarias")
    @Timed
    public ResponseEntity<List<MovimientoInvestigacionSumariaDTO>> getAllMovimientoInvestigacionSumarias(Pageable pageable) {
        log.debug("REST request to get a page of MovimientoInvestigacionSumarias");
        Page<MovimientoInvestigacionSumariaDTO> page = movimientoInvestigacionSumariaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/movimiento-investigacion-sumarias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /movimiento-investigacion-sumarias/:id : get the "id" movimientoInvestigacionSumaria.
     *
     * @param id the id of the movimientoInvestigacionSumariaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movimientoInvestigacionSumariaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/movimiento-investigacion-sumarias/{id}")
    @Timed
    public ResponseEntity<MovimientoInvestigacionSumariaDTO> getMovimientoInvestigacionSumaria(@PathVariable Long id) {
        log.debug("REST request to get MovimientoInvestigacionSumaria : {}", id);
        Optional<MovimientoInvestigacionSumariaDTO> movimientoInvestigacionSumariaDTO = movimientoInvestigacionSumariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movimientoInvestigacionSumariaDTO);
    }

    /**
     * DELETE  /movimiento-investigacion-sumarias/:id : delete the "id" movimientoInvestigacionSumaria.
     *
     * @param id the id of the movimientoInvestigacionSumariaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movimiento-investigacion-sumarias/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovimientoInvestigacionSumaria(@PathVariable Long id) {
        log.debug("REST request to delete MovimientoInvestigacionSumaria : {}", id);
        movimientoInvestigacionSumariaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
