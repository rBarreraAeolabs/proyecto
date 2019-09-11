package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.config.ApplicationProperties;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.DocumentoService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.storage.FileSystemStorageService;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Documento.
 */
@RestController
@RequestMapping("/api")
public class DocumentoResource {

    private final Logger log = LoggerFactory.getLogger(DocumentoResource.class);
    private static final String ENTITY_NAME = "documento";
    private final DocumentoService documentoService;


    public DocumentoResource(
        DocumentoService documentoService,
        StorageServiceInterface storageServiceInterface,
        FileSystemStorageService fileSystemStorageService,
        ApplicationProperties applicationProperties
    ) {
        this.documentoService = documentoService;


    }

    /**
     * POST  /documentos : Create a new documento.
     *
     * @param documentoDTO the documentoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentoDTO, or with status 400 (Bad Request) if the documento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documentos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ADJUNTAR_DOCUMENTO + "\")")
    public ResponseEntity<DocumentoDTO> createDocumento(@RequestBody DocumentoDTO documentoDTO) throws URISyntaxException {
        log.debug("REST request to save Documento : {}", documentoDTO);
        if (documentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new documento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentoDTO result = documentoService.save(documentoDTO);
        return ResponseEntity.created(new URI("/api/documentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /documentos : Updates an existing documento.
     *
     * @param documentoDTO the documentoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentoDTO,
     * or with status 400 (Bad Request) if the documentoDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documentos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_DOCUMENTO + "\")")
    public ResponseEntity<DocumentoDTO> updateDocumento(@RequestBody DocumentoDTO documentoDTO) throws URISyntaxException {
        log.debug("REST request to update Documento : {}", documentoDTO);
        if (documentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentoDTO result = documentoService.save(documentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documentos : get all the documentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentos in body
     */
    @GetMapping("/documentos")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_DOCUMENTO + "\")")
    public ResponseEntity<List<DocumentoDTO>> getAllDocumentos(Pageable pageable) {
        log.debug("REST request to get a page of Documentos");
        Page<DocumentoDTO> page = documentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/documentos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /documentos/:id : get the "id" documento.
     *
     * @param id the id of the documentoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/documentos/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_DOCUMENTO + "\")")
    public ResponseEntity<DocumentoDTO> getDocumento(@PathVariable Long id) {
        log.debug("REST request to get Documento : {}", id);
        Optional<DocumentoDTO> documentoDTO = documentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentoDTO);
    }

    /**
     * DELETE  /documentos/:id : delete the "id" documento.
     *
     * @param id the id of the documentoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documentos/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.ELIMINAR_DOCUMENTO + "\")")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        log.debug("REST request to delete Documento : {}", id);
        documentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/documentos/{hash}/download")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DESCARGAR_DOCUMENTO + "\")")
    public ResponseEntity<Resource> downloadByHash(@PathVariable String hash) {
        if (hash == null || hash.trim().length() == 0) {
            return null;
        }

        Resource resource = this.documentoService.getByHashToDownload(hash);

        String nombreArchivo = this.documentoService.findByHash(hash).getArchivoNombre();

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
            .body(resource);
    }

    @PostMapping("/documentos/providencia")
    @Timed
    public ResponseEntity<DocumentoDTO> findDocumentoByProvidencia(@RequestBody ProvidenciaDTO providenciaDTO) {
        Optional<DocumentoDTO> documentoOptional = this.documentoService.findByProvidencia(providenciaDTO);
        return ResponseUtil.wrapOrNotFound(documentoOptional);
    }


    @GetMapping("/documentos/{hash}/view")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.DESCARGAR_DOCUMENTO + "\")")
    public ResponseEntity<Resource> visualizarDocumento(@PathVariable String hash) {
        if (hash == null || hash.trim().length() == 0) {
            return null;
        }

        Resource resource = this.documentoService.getByHashToDownload(hash);

        String nombreArchivo = this.documentoService.findByHash(hash).getArchivoNombre();


        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + nombreArchivo + "\"")
            .body(resource);
    }


}

