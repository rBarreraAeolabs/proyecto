package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.MovimientoProvidenciaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FiltroMovProDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FiltroMovimientoProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
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
 * REST controller for managing MovimientoProvidencia.
 */
@RestController
@RequestMapping("/api")
public class MovimientoProvidenciaResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoProvidenciaResource.class);

    private static final String ENTITY_NAME = "movimientoProvidencia";

    private final MovimientoProvidenciaService movimientoProvidenciaService;

    private final ProvidenciaMapper providenciaMapper;

    public MovimientoProvidenciaResource(MovimientoProvidenciaService movimientoProvidenciaService, ProvidenciaMapper providenciaMapper) {
        this.movimientoProvidenciaService = movimientoProvidenciaService;
        this.providenciaMapper = providenciaMapper;
    }

    /**
     * POST  /movimiento-providencias : Create a new movimientoProvidencia.
     *
     * @param movimientoProvidenciaDTO the movimientoProvidenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movimientoProvidenciaDTO, or with status 400 (Bad Request) if the movimientoProvidencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movimiento-providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA_PRIVILEGE + "\") " +
        "or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<MovimientoProvidenciaDTO> createMovimientoProvidencia(@RequestBody MovimientoProvidenciaDTO movimientoProvidenciaDTO) throws URISyntaxException {
        log.debug("REST request to save MovimientoProvidencia : {}", movimientoProvidenciaDTO);
        if (movimientoProvidenciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new movimientoProvidencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimientoProvidenciaDTO result = movimientoProvidenciaService.save(movimientoProvidenciaDTO);
        return ResponseEntity.created(new URI("/api/movimiento-providencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movimiento-providencias : Updates an existing movimientoProvidencia.
     *
     * @param movimientoProvidenciaDTO the movimientoProvidenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movimientoProvidenciaDTO,
     * or with status 400 (Bad Request) if the movimientoProvidenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the movimientoProvidenciaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movimiento-providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_PROVIDENCIA_PRIVILEGE + "\") " +
        "or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<MovimientoProvidenciaDTO> updateMovimientoProvidencia(@RequestBody MovimientoProvidenciaDTO movimientoProvidenciaDTO) throws URISyntaxException {
        log.debug("REST request to update MovimientoProvidencia : {}", movimientoProvidenciaDTO);
        if (movimientoProvidenciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovimientoProvidenciaDTO result = movimientoProvidenciaService.save(movimientoProvidenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movimientoProvidenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movimiento-providencias : get all the movimientoProvidencias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of movimientoProvidencias in body
     */
    @GetMapping("/movimiento-providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<List<MovimientoProvidenciaDTO>> getAllMovimientoProvidencias(Pageable pageable, FiltroMovimientoProvidenciaDTO filtro) {
        log.debug("REST request to get a page of MovimientoProvidencias");
        Page<MovimientoProvidenciaDTO> page = movimientoProvidenciaService.findAll(pageable, filtro);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/movimiento-providencias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /movimiento-providencias/:id : get the "id" movimientoProvidencia.
     *
     * @param id the id of the movimientoProvidenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movimientoProvidenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/movimiento-providencias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<MovimientoProvidenciaDTO> getMovimientoProvidencia(@PathVariable Long id) {
        log.debug("REST request to get MovimientoProvidencia : {}", id);
        Optional<MovimientoProvidenciaDTO> movimientoProvidenciaDTO = movimientoProvidenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movimientoProvidenciaDTO);
    }

    /**
     * DELETE  /movimiento-providencias/:id : delete the "id" movimientoProvidencia.
     *
     * @param id the id of the movimientoProvidenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movimiento-providencias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteMovimientoProvidencia(@PathVariable Long id) {
        log.debug("REST request to delete MovimientoProvidencia : {}", id);
        movimientoProvidenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/movimiento-providencias/providencia")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<Set<MovimientoProvidenciaDTO>> getAllByProvidencia(@RequestBody FiltroMovProDTO filtroMovPro) throws URISyntaxException {

        if (filtroMovPro.getProvidencia() != null)
        {
            return new ResponseEntity<>(this.movimientoProvidenciaService.getAllByIdProvidenciaWithFilters(filtroMovPro
                .getProvidencia(), filtroMovPro.getFiltroMovimientoProvidencia()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/movimiento-providencias/list")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA_PRIVILEGE + "\")")
    public ResponseEntity<Set<MovimientoProvidenciaDTO>> getAllMovimientosWithoutPagination()
    {
        return new ResponseEntity<Set<MovimientoProvidenciaDTO>>(this.movimientoProvidenciaService.getAllWithoutPagination(),
            HttpStatus.OK);
    }
}
