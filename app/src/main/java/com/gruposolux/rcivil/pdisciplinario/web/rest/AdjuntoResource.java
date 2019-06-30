package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.AdjuntoService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FileUploadResponseDTO;
import com.gruposolux.rcivil.pdisciplinario.storage.StorageServiceInterface;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Adjunto.
 */
@RestController
@RequestMapping("/api")
public class AdjuntoResource {

    private final Logger log = LoggerFactory.getLogger(AdjuntoResource.class);
    private static final String ENTITY_NAME = "adjunto";
    private final AdjuntoService adjuntoService;
    private final StorageServiceInterface storageServiceInterface;

    public AdjuntoResource(AdjuntoService adjuntoService, StorageServiceInterface storageServiceInterface) {
        this.adjuntoService = adjuntoService;
        this.storageServiceInterface = storageServiceInterface;
    }

    /**
     * POST  /adjuntos : Create a new adjunto.
     *
     * @param adjuntoDTO the adjuntoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adjuntoDTO, or with status 400 (Bad Request) if the adjunto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adjuntos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ADJUNTAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<AdjuntoDTO> createAdjunto(@RequestBody AdjuntoDTO adjuntoDTO) throws URISyntaxException {
        log.debug("REST request to save Adjunto : {}", adjuntoDTO);
        if (adjuntoDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjunto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjuntoDTO result = adjuntoService.save(adjuntoDTO);
        return ResponseEntity.created(new URI("/api/adjuntos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adjuntos : Updates an existing adjunto.
     *
     * @param adjuntoDTO the adjuntoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adjuntoDTO,
     * or with status 400 (Bad Request) if the adjuntoDTO is not valid,
     * or with status 500 (Internal Server Error) if the adjuntoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adjuntos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<AdjuntoDTO> updateAdjunto(@RequestBody AdjuntoDTO adjuntoDTO) throws URISyntaxException {
        log.debug("REST request to update Adjunto : {}", adjuntoDTO);
        if (adjuntoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjuntoDTO result = adjuntoService.save(adjuntoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adjuntoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adjuntos : get all the adjuntos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of adjuntos in body
     */
    @GetMapping("/adjuntos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<List<AdjuntoDTO>> getAllAdjuntos(Pageable pageable) {
        log.debug("REST request to get a page of Adjuntos");
        Page<AdjuntoDTO> page = adjuntoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adjuntos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /adjuntos/:id : get the "id" adjunto.
     *
     * @param id the id of the adjuntoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adjuntoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adjuntos/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<AdjuntoDTO> getAdjunto(@PathVariable Long id) {
        log.debug("REST request to get Adjunto : {}", id);
        Optional<AdjuntoDTO> adjuntoDTO = adjuntoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjuntoDTO);
    }

    /**
     * DELETE  /adjuntos/:id : delete the "id" adjunto.
     *
     * @param id the id of the adjuntoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adjuntos/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ELIMINAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<Void> deleteAdjunto(@PathVariable Long id) {
        log.debug("REST request to delete Adjunto : {}", id);
        this.adjuntoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * Sube archivos a carpeta local
     *
     * @param file
     * @return
     */
    @PostMapping("/adjuntos/_upload")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ADJUNTAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<FileUploadResponseDTO> handleFileUpload(@RequestParam("file") MultipartFile file) {

        FileUploadResponseDTO information = this.storageServiceInterface.store(file);

        ResponseEntity.BodyBuilder response = null;

        if (information.hasError())
        {
            response = ResponseEntity.badRequest();
        }
        else
        {
            AdjuntoDTO adjuntoDTO = this.adjuntoService.save(information.getFileinfo());
            information.setFileinfo(adjuntoDTO);
            response = ResponseEntity.ok();
        }

        return response.body(information);
    }

    @GetMapping("/adjuntos/{hash}/download")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DESCARGAR_ADJUNTO_PRIVILEGE + "\")")
    public ResponseEntity<Resource> downloadByHash(@PathVariable String hash) throws IOException
    {
        if (hash == null || hash.trim().length() == 0)
        {
            return null;
        }

        Resource resource = this.adjuntoService.getByHashToDownload(hash);

        String nombreArchivo = this.adjuntoService.findByHash(hash).getArchivoNombre();

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(resource.contentLength())
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
            .body(resource);
    }
}
