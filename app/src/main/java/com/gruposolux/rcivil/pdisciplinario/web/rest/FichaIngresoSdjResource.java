package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.FichaIngresoSdjService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FichaIngresoSdjDTO;
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
 * REST controller for managing FichaIngresoSdj.
 */
@RestController
@RequestMapping("/api")
public class FichaIngresoSdjResource {

    private final Logger log = LoggerFactory.getLogger(FichaIngresoSdjResource.class);

    private static final String ENTITY_NAME = "fichaIngresoSdj";

    private final FichaIngresoSdjService fichaIngresoSdjService;

    public FichaIngresoSdjResource(FichaIngresoSdjService fichaIngresoSdjService) {
        this.fichaIngresoSdjService = fichaIngresoSdjService;
    }

    /**
     * POST  /ficha-ingreso-sdjs : Create a new fichaIngresoSdj.
     *
     * @param fichaIngresoSdjDTO the fichaIngresoSdjDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fichaIngresoSdjDTO, or with status 400 (Bad Request) if the fichaIngresoSdj has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ficha-ingreso-sdjs")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_FICHA_INGRESO_PRIVILAGE + "\")")
    public ResponseEntity<FichaIngresoSdjDTO> createFichaIngresoSdj(@RequestBody FichaIngresoSdjDTO fichaIngresoSdjDTO) throws URISyntaxException {
        log.debug("REST request to save FichaIngresoSdj : {}", fichaIngresoSdjDTO);
        if (fichaIngresoSdjDTO.getId() != null) {
            throw new BadRequestAlertException("A new fichaIngresoSdj cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FichaIngresoSdjDTO result = fichaIngresoSdjService.save(fichaIngresoSdjDTO);
        return ResponseEntity.created(new URI("/api/ficha-ingreso-sdjs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ficha-ingreso-sdjs : Updates an existing fichaIngresoSdj.
     *
     * @param fichaIngresoSdjDTO the fichaIngresoSdjDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fichaIngresoSdjDTO,
     * or with status 400 (Bad Request) if the fichaIngresoSdjDTO is not valid,
     * or with status 500 (Internal Server Error) if the fichaIngresoSdjDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ficha-ingreso-sdjs")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<FichaIngresoSdjDTO> updateFichaIngresoSdj(@RequestBody FichaIngresoSdjDTO fichaIngresoSdjDTO) throws URISyntaxException {
        log.debug("REST request to update FichaIngresoSdj : {}", fichaIngresoSdjDTO);
        if (fichaIngresoSdjDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FichaIngresoSdjDTO result = fichaIngresoSdjService.save(fichaIngresoSdjDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fichaIngresoSdjDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ficha-ingreso-sdjs : get all the fichaIngresoSdjs.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of fichaIngresoSdjs in body
     */
    @GetMapping("/ficha-ingreso-sdjs")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_FICHA_INGRESO_PRIVILEGE + "\")")
    public ResponseEntity<List<FichaIngresoSdjDTO>> getAllFichaIngresoSdjs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("providencia-is-null".equals(filter)) {
            log.debug("REST request to get all FichaIngresoSdjs where providencia is null");
            return new ResponseEntity<>(fichaIngresoSdjService.findAllWhereProvidenciaIsNull(),
                HttpStatus.OK);
        }
        log.debug("REST request to get a page of FichaIngresoSdjs");
        Page<FichaIngresoSdjDTO> page = fichaIngresoSdjService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ficha-ingreso-sdjs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ficha-ingreso-sdjs/:id : get the "id" fichaIngresoSdj.
     *
     * @param id the id of the fichaIngresoSdjDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fichaIngresoSdjDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ficha-ingreso-sdjs/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_FICHA_INGRESO_PRIVILEGE + "\")")
    public ResponseEntity<FichaIngresoSdjDTO> getFichaIngresoSdj(@PathVariable Long id) {
        log.debug("REST request to get FichaIngresoSdj : {}", id);
        Optional<FichaIngresoSdjDTO> fichaIngresoSdjDTO = fichaIngresoSdjService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fichaIngresoSdjDTO);
    }

    /**
     * DELETE  /ficha-ingreso-sdjs/:id : delete the "id" fichaIngresoSdj.
     *
     * @param id the id of the fichaIngresoSdjDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ficha-ingreso-sdjs/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteFichaIngresoSdj(@PathVariable Long id) {
        log.debug("REST request to delete FichaIngresoSdj : {}", id);
        fichaIngresoSdjService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
