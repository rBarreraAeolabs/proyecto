package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.DerivacionService;
import com.gruposolux.rcivil.pdisciplinario.service.ProvidenciaService;
import com.gruposolux.rcivil.pdisciplinario.service.UserService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.service.events.PostCrearProvidenciaEvent;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.DerivacionMapper;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
import com.gruposolux.rcivil.pdisciplinario.storage.AlfrescoStorageService;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import java.util.stream.Collectors;

/**
 * REST controller for managing Derivacion.
 */
@RestController
@RequestMapping("/api")
public class DerivacionResource {

    private final Logger log = LoggerFactory.getLogger(DerivacionResource.class);

    private static final String ENTITY_NAME = "derivacion";

    private final DerivacionService derivacionService;

    private final ProvidenciaService providenciaService;

    private final UserService userService;

    private final ProvidenciaMapper providenciaMapper;

    private final ApplicationEventPublisher publisher;

    private final DerivacionMapper derivacionMapper;

    @Autowired
    private final AlfrescoStorageService alfrescoStorageService;

    public DerivacionResource(DerivacionService derivacionService, ProvidenciaService providenciaService, UserService userService, ProvidenciaMapper providenciaMapper, ApplicationEventPublisher publisher, DerivacionMapper derivacionMapper, AlfrescoStorageService alfrescoStorageService) {
        this.derivacionService = derivacionService;
        this.providenciaService = providenciaService;
        this.userService = userService;
        this.providenciaMapper = providenciaMapper;
        this.publisher = publisher;
        this.derivacionMapper = derivacionMapper;
        this.alfrescoStorageService = alfrescoStorageService;
    }

    /**
     * POST  /derivacions : Create a new derivacion.
     *
     * @param derivacionDTO the derivacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new derivacionDTO, or with status 400 (Bad Request) if the derivacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/derivacions")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA + "\")")
    public ResponseEntity<DerivacionDTO> createDerivacion(@RequestBody DerivacionDTO derivacionDTO) throws URISyntaxException {
        log.debug("REST request to save Derivacion : {}", derivacionDTO);
        if (derivacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new derivacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DerivacionDTO result = derivacionService.save(derivacionDTO);
        return ResponseEntity.created(new URI("/api/derivacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /derivacions : Updates an existing derivacion.
     *
     * @param providenciaDerivacionDTO the derivacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated derivacionDTO,
     * or with status 400 (Bad Request) if the derivacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the derivacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/derivacions")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDerivacionDTO> updateDerivacion(@RequestBody ProvidenciaDerivacionDTO providenciaDerivacionDTO) throws URISyntaxException {
        log.debug("REST request to update Derivacion : {}", providenciaDerivacionDTO);
        ProvidenciaDTO providenciaDTO = providenciaDerivacionDTO.getProvidenciaDTO();
        DerivacionDTO derivacionDTO = providenciaDerivacionDTO.getDerivacionDTO();
        User currentUser = userService.getCurrentUser();

        this.publisher.publishEvent(new PostCrearProvidenciaEvent(providenciaDTO, currentUser, providenciaDTO.getAdjuntos(), derivacionDTO));

        providenciaDerivacionDTO.setDerivacionDTO(providenciaDerivacionDTO.getDerivacionDTO());

//        if (providenciaDerivacionDTO.getProvidenciaDTO().getAdjuntos().size() > 0){
//            this.alfrescoStorageService.moveToArchivosFolder(providenciaDerivacionDTO.getProvidenciaDTO().getAdjuntos()
//                .stream().map(), providenciaDTO);
//        }

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDerivacionDTO.toString()))
            .body(providenciaDerivacionDTO);
    }

    /**
     * GET  /derivacions : get all the derivacions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of derivacions in body
     */
    @GetMapping("/derivacions/paged")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA + "\")")
    public ResponseEntity<List<DerivacionDTO>> getAllDerivacions(Pageable pageable) {
        log.debug("REST request to get a page of Derivacions");
        Page<DerivacionDTO> page = derivacionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/derivacions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /derivacions/:id : get the "id" derivacion.
     *
     * @param id the id of the derivacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the derivacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/derivacions/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA + "\")")
    public ResponseEntity<DerivacionDTO> getDerivacion(@PathVariable Long id) {
        log.debug("REST request to get Derivacion : {}", id);
        Optional<DerivacionDTO> derivacionDTO = derivacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(derivacionDTO);
    }

    /**
     * DELETE  /derivacions/:id : delete the "id" derivacion.
     *
     * @param id the id of the derivacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/derivacions/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DERIVAR_PROVIDENCIA + "\")")
    public ResponseEntity<Void> deleteDerivacion(@PathVariable Long id) {
        log.debug("REST request to delete Derivacion : {}", id);
        derivacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/derivacions/list/{idProvidencia}")
    @Timed
    public ResponseEntity<Set<DerivacionDTO>> getByProvidencia(@PathVariable Long idProvidencia)
    {
        if (idProvidencia != null)
        {
            return new ResponseEntity<>(this.derivacionService.getByProvidencia(idProvidencia), HttpStatus.OK);
        }
        return null;
    }
}
