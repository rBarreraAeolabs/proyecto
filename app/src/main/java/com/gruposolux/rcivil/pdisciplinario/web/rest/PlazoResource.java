package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.PlazoService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlazoDTO;
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
 * REST controller for managing Plazo.
 */
@RestController
@RequestMapping("/api")
public class PlazoResource {

    private final Logger log = LoggerFactory.getLogger(PlazoResource.class);

    private static final String ENTITY_NAME = "plazo";

    private final PlazoService plazoService;

    public PlazoResource(PlazoService plazoService) {
        this.plazoService = plazoService;
    }

    /**
     * POST  /plazos : Create a new plazo.
     *
     * @param plazoDTO the plazoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plazoDTO, or with status 400 (Bad Request) if the plazo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plazos")
    @Timed
    public ResponseEntity<PlazoDTO> createPlazo(@RequestBody PlazoDTO plazoDTO) throws URISyntaxException {
        log.debug("REST request to save Plazo : {}", plazoDTO);
        if (plazoDTO.getId() != null) {
            throw new BadRequestAlertException("A new plazo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlazoDTO result = plazoService.save(plazoDTO);
        return ResponseEntity.created(new URI("/api/plazos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plazos : Updates an existing plazo.
     *
     * @param plazoDTO the plazoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plazoDTO,
     * or with status 400 (Bad Request) if the plazoDTO is not valid,
     * or with status 500 (Internal Server Error) if the plazoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plazos")
    @Timed
    public ResponseEntity<PlazoDTO> updatePlazo(@RequestBody PlazoDTO plazoDTO) throws URISyntaxException {
        log.debug("REST request to update Plazo : {}", plazoDTO);
        if (plazoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlazoDTO result = plazoService.save(plazoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plazoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plazos : get all the plazos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plazos in body
     */
    @GetMapping("/plazos")
    @Timed
    public ResponseEntity<List<PlazoDTO>> getAllPlazos(Pageable pageable) {
        log.debug("REST request to get a page of Plazos");
        Page<PlazoDTO> page = plazoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plazos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /plazos/:id : get the "id" plazo.
     *
     * @param id the id of the plazoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plazoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plazos/{id}")
    @Timed
    public ResponseEntity<PlazoDTO> getPlazo(@PathVariable Long id) {
        log.debug("REST request to get Plazo : {}", id);
        Optional<PlazoDTO> plazoDTO = plazoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plazoDTO);
    }

    /**
     * DELETE  /plazos/:id : delete the "id" plazo.
     *
     * @param id the id of the plazoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plazos/{id}")
    @Timed
    public ResponseEntity<Void> deletePlazo(@PathVariable Long id) {
        log.debug("REST request to delete Plazo : {}", id);
        plazoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/plazos/all")
    @Timed
    public ResponseEntity<List<PlazoDTO>> getAllPlazos() {
        List<PlazoDTO> plazosDtos = this.plazoService.getAllPlazos();
        return new ResponseEntity<List<PlazoDTO>>(plazosDtos, HttpStatus.OK);
    }
}
