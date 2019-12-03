package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.InstruccionesService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InstruccionesDTO;
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

@RestController
@RequestMapping("/api")
public class InstruccionesResource
{
    private final Logger log = LoggerFactory.getLogger(InstruccionesResource.class);
    private static final String ENTITY_NAME = "instrucciones";
    private final InstruccionesService instruccionesService;

    public InstruccionesResource(InstruccionesService instruccionesService) {
        this.instruccionesService = instruccionesService;
    }

    @PostMapping("/instrucciones")
    @Timed
    public ResponseEntity<InstruccionesDTO> create(@RequestBody InstruccionesDTO instruccionesDTO) throws URISyntaxException
    {
        instruccionesDTO = this.instruccionesService.save(instruccionesDTO);
        return ResponseEntity.created(new URI("/api/instrucciones/" + instruccionesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, instruccionesDTO.getId().toString()))
            .body(instruccionesDTO);
    }

    @PutMapping("/instrucciones")
    @Timed
    public ResponseEntity<InstruccionesDTO> update(@RequestBody InstruccionesDTO instruccionesDTO) throws URISyntaxException
    {
        instruccionesDTO = this.instruccionesService.save(instruccionesDTO);
        return ResponseEntity.created(new URI("/api/instrucciones" + instruccionesDTO.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instruccionesDTO.getId().toString()))
            .body(instruccionesDTO);
    }

    @GetMapping("/instrucciones/{id}")
    @Timed
    public ResponseEntity<InstruccionesDTO> findOne(@PathVariable Long id)
    {
        Optional<InstruccionesDTO> instruccionesOptional = this.instruccionesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instruccionesOptional);
    }

    @GetMapping("/instrucciones")
    @Timed
    public ResponseEntity<List<InstruccionesDTO>> page(Pageable pageable)
    {
        Page<InstruccionesDTO> page = this.instruccionesService.page(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instrucciones");

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/instrucciones/{id}")
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        this.instruccionesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/instrucciones/all")
    @Timed
    public ResponseEntity<List<InstruccionesDTO>> getAll()
    {
        List<InstruccionesDTO> instruccionesDTOs = this.instruccionesService.getAll();
        return new ResponseEntity<>(instruccionesDTOs, HttpStatus.OK);
    }
}
