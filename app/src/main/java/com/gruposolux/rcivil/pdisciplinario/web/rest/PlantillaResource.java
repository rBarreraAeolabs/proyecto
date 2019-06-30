package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.PlantillaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlantillaDTO;
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
import java.util.Set;

/**
 * REST controller for managing Plantilla.
 */
@RestController
@RequestMapping("/api")
public class PlantillaResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaResource.class);

    private static final String ENTITY_NAME = "plantilla";

    private final PlantillaService plantillaService;

    public PlantillaResource(PlantillaService plantillaService) {
        this.plantillaService = plantillaService;
    }

    /**
     * POST  /plantillas : Create a new plantilla.
     *
     * @param plantillaDTO the plantillaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaDTO, or with status 400 (Bad Request) if the plantilla has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantillas")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PLANTILLA_PRIVILEGE + "\")")
    public ResponseEntity<PlantillaDTO> createPlantilla(@RequestBody PlantillaDTO plantillaDTO) throws URISyntaxException {
        log.debug("REST request to save Plantilla : {}", plantillaDTO);
        if (plantillaDTO.getId() != null) {
            throw new BadRequestAlertException("A new plantilla cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaDTO result = plantillaService.save(plantillaDTO);
        return ResponseEntity.created(new URI("/api/plantillas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantillas : Updates an existing plantilla.
     *
     * @param plantillaDTO the plantillaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaDTO,
     * or with status 400 (Bad Request) if the plantillaDTO is not valid,
     * or with status 500 (Internal Server Error) if the plantillaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantillas")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_PLANTILLA_PRIVILEGE + "\")")
    public ResponseEntity<PlantillaDTO> updatePlantilla(@RequestBody PlantillaDTO plantillaDTO) throws URISyntaxException {
        log.debug("REST request to update Plantilla : {}", plantillaDTO);
        if (plantillaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaDTO result = plantillaService.save(plantillaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantillas : get all the plantillas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillas in body
     */
    @GetMapping("/plantillas")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PLANTILLA_PRIVILEGE + "\")")
    public ResponseEntity<List<PlantillaDTO>> getAllPlantillas(Pageable pageable) {
        log.debug("REST request to get a page of Plantillas");
        Page<PlantillaDTO> page = plantillaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantillas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /plantillas/:id : get the "id" plantilla.
     *
     * @param id the id of the plantillaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plantillas/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PLANTILLA_PRIVILEGE + "\")")
    public ResponseEntity<PlantillaDTO> getPlantilla(@PathVariable Long id) {
        log.debug("REST request to get Plantilla : {}", id);
        Optional<PlantillaDTO> plantillaDTO = plantillaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plantillaDTO);
    }

    /**
     * DELETE  /plantillas/:id : delete the "id" plantilla.
     *
     * @param id the id of the plantillaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantillas/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ELIMINAR_PLANTILLA_PRIVILEGE + "\")")
    public ResponseEntity<Void> deletePlantilla(@PathVariable Long id) {
        log.debug("REST request to delete Plantilla : {}", id);
        plantillaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/plantillas/list")
    @Timed
    public ResponseEntity<Set<PlantillaDTO>> getAll()
    {
        return new ResponseEntity<>(this.plantillaService.getAll(), HttpStatus.OK);
    }
}
