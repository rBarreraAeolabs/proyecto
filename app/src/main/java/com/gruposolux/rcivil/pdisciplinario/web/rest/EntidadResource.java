package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.EntidadService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
@RestController
@RequestMapping("/api")
public class EntidadResource
{
    private final Logger log = LoggerFactory.getLogger(EntidadResource.class);
    private static final String ENTITY_NAME = "entidad";
    private final EntidadService entidadService;

    public EntidadResource(EntidadService entidadService) {
        this.entidadService = entidadService;
    }

    @PostMapping("/entidades")
    @Timed
    public ResponseEntity<EntidadDTO> create(@RequestBody EntidadDTO entidadDTO) throws URISyntaxException
    {
        entidadDTO = this.entidadService.save(entidadDTO);
        return ResponseEntity.created(new URI("/api/entidades/" + entidadDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, entidadDTO.getId().toString()))
            .body(entidadDTO);
    }

    @PutMapping("/entidades")
    @Timed
    public ResponseEntity<EntidadDTO> update(@RequestBody EntidadDTO entidadDTO) throws URISyntaxException
    {
        entidadDTO = this.entidadService.save(entidadDTO);
        return ResponseEntity.created(new URI("/api/entidades/" + entidadDTO.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entidadDTO.getId().toString()))
            .body(entidadDTO);
    }

    @GetMapping("/entidades/{id}")
    @Timed
    public ResponseEntity<EntidadDTO> findOne(@PathVariable Long id)
    {
        Optional<EntidadDTO> entidadOptional = this.entidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entidadOptional);
    }

    @GetMapping("/entidades")
    @Timed
    public ResponseEntity<List<EntidadDTO>> page(Pageable pageable)
    {
        Page<EntidadDTO> page = this.entidadService.page(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entidades");

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/entidades/{id}")
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        this.entidadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/entidades/all")
    @Timed
    public ResponseEntity<List<EntidadDTO>> getAll()
    {
        List<EntidadDTO> entidadDTOs = this.entidadService.getAll();
        return new ResponseEntity<>(entidadDTOs, HttpStatus.OK);
    }
}
