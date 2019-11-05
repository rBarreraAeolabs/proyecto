package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.InvestigacionSumariaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InvestigacionSumariaDTO;
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
 * REST controller for managing InvestigacionSumaria.
 */
@RestController
@RequestMapping("/api")
public class InvestigacionSumariaResource {

    private final Logger log = LoggerFactory.getLogger(InvestigacionSumariaResource.class);

    private static final String ENTITY_NAME = "investigacionSumaria";

    private final InvestigacionSumariaService investigacionSumariaService;

    public InvestigacionSumariaResource(InvestigacionSumariaService investigacionSumariaService) {
        this.investigacionSumariaService = investigacionSumariaService;
    }

    /**
     * POST  /investigacion-sumarias : Create a new investigacionSumaria.
     *
     * @param investigacionSumariaDTO the investigacionSumariaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investigacionSumariaDTO, or with status 400 (Bad Request) if the investigacionSumaria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/investigacion-sumarias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ASIGNAR_TIPO_A_PROVIDENCIA + "\")")
    public ResponseEntity<InvestigacionSumariaDTO> createInvestigacionSumaria(@RequestBody InvestigacionSumariaDTO investigacionSumariaDTO) throws URISyntaxException {
        log.debug("REST request to save InvestigacionSumaria : {}", investigacionSumariaDTO);
        if (investigacionSumariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new investigacionSumaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestigacionSumariaDTO result = investigacionSumariaService.save(investigacionSumariaDTO);
        return ResponseEntity.created(new URI("/api/investigacion-sumarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /investigacion-sumarias : Updates an existing investigacionSumaria.
     *
     * @param investigacionSumariaDTO the investigacionSumariaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investigacionSumariaDTO,
     * or with status 400 (Bad Request) if the investigacionSumariaDTO is not valid,
     * or with status 500 (Internal Server Error) if the investigacionSumariaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/investigacion-sumarias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_INVESTIGACION + "\")")
    public ResponseEntity<InvestigacionSumariaDTO> updateInvestigacionSumaria(@RequestBody InvestigacionSumariaDTO investigacionSumariaDTO) throws URISyntaxException {
        log.debug("REST request to update InvestigacionSumaria : {}", investigacionSumariaDTO);
        if (investigacionSumariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestigacionSumariaDTO result = investigacionSumariaService.save(investigacionSumariaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investigacionSumariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /investigacion-sumarias : get all the investigacionSumarias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of investigacionSumarias in body
     */
    @GetMapping("/investigacion-sumarias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_INVESTIGACION + "\")")
    public ResponseEntity<List<InvestigacionSumariaDTO>> getAllInvestigacionSumarias(Pageable pageable) {
        log.debug("REST request to get a page of InvestigacionSumarias");
        Page<InvestigacionSumariaDTO> page = investigacionSumariaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/investigacion-sumarias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /investigacion-sumarias/:id : get the "id" investigacionSumaria.
     *
     * @param id the id of the investigacionSumariaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investigacionSumariaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/investigacion-sumarias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_INVESTIGACION + "\")")
    public ResponseEntity<InvestigacionSumariaDTO> getInvestigacionSumaria(@PathVariable Long id) {
        log.debug("REST request to get InvestigacionSumaria : {}", id);
        Optional<InvestigacionSumariaDTO> investigacionSumariaDTO = investigacionSumariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investigacionSumariaDTO);
    }

    /**
     * DELETE  /investigacion-sumarias/:id : delete the "id" investigacionSumaria.
     *
     * @param id the id of the investigacionSumariaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/investigacion-sumarias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteInvestigacionSumaria(@PathVariable Long id) {
        log.debug("REST request to delete InvestigacionSumaria : {}", id);
        investigacionSumariaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
