package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;
import com.gruposolux.rcivil.pdisciplinario.repository.DocumentoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.DocumentoMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
import com.gruposolux.rcivil.pdisciplinario.service.util.PdfConverter;
import com.gruposolux.rcivil.pdisciplinario.storage.FileSystemStorageService;
import com.gruposolux.rcivil.pdisciplinario.storage.StorageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;


/**
 * Service Implementation for managing Documento.
 */
@Service
@Transactional
public class DocumentoService {

    private final Logger log = LoggerFactory.getLogger(DocumentoService.class);
    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;
    private final StorageServiceInterface storageServiceInterface;
    private final ProvidenciaMapper providenciaMapper;
    private final FileSystemStorageService fileSystemStorageService;

    public DocumentoService(
        DocumentoRepository documentoRepository,
        DocumentoMapper documentoMapper,
        StorageServiceInterface storageServiceInterface,
        ProvidenciaMapper providenciaMapper,
        FileSystemStorageService fileSystemStorageService
    ) {
        this.documentoRepository = documentoRepository;
        this.documentoMapper = documentoMapper;
        this.storageServiceInterface = storageServiceInterface;
        this.providenciaMapper = providenciaMapper;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    /**
     * Save a documento.
     *
     * @param documentoDTO the entity to save
     * @return the persisted entity
     */
    public DocumentoDTO save(DocumentoDTO documentoDTO) {
        log.debug("Request to save Documento : {}", documentoDTO);
        Documento documento = documentoMapper.toEntity(documentoDTO);

        try {
            String filename = "";
            if (documento.getArchivoNombre() != null) {
//                filename = PdfConverter.generatePDFFromHTML(documento.getArchivoNombre(), documento.getContenido());
                filename = this.fileSystemStorageService.storePdf(documento.getArchivoNombre(), documento.getContenido());
            } else {
                documento.setArchivoNombre("respuesta-" + this.documentoRepository.getCountTotal());
//                filename = PdfConverter.generatePDFFromHTML(documento.getArchivoNombre(), documento.getContenido());
                filename = this.fileSystemStorageService.storePdf(documento.getArchivoNombre(), documento.getContenido());
            }

            documento.setArchivoNombre(filename);
//            documento.setLocalPath("./upload/" + filename.trim());
            documento.setLocalPath(this.fileSystemStorageService.getAdjuntosPath() + "/" + filename.trim());
            documento.setArchivoMimeType(MediaType.APPLICATION_PDF.toString());
            documento.setFechaCreado(ZonedDateTime.now().toLocalDate());

            documento = this.storageServiceInterface.store(documento);

            // Ask if exist other resolucion file with the same name and the same providencia to change version
            Set<Documento> docs = null;

            if (documento.getTipoPlantilla() != null) {
                if (documento.getTipoPlantilla().equals(TipoPlantilla.RESOLUCION)) {
                    docs = this.documentoRepository.findResolucionByNombreArchivo(documento.getArchivoNombre().toLowerCase(),
                        documento.getProvidencia());
                } else {
                    docs = this.documentoRepository.findByNombreArchivo(documento.getArchivoNombre().toLowerCase(),
                        documento.getProvidencia());
                }
            }

            if (docs != null && docs.size() > 0) {
                documento.setVersion(docs.stream().findFirst().get().getVersion() + 1);
            } else {
                documento.setVersion((int) 1);
            }

            documento = documentoRepository.save(documento);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return documentoMapper.toDto(documento);
    }

    @Transactional
    public DocumentoDTO update(DocumentoDTO documentoDTO) {
        Documento documento = this.documentoMapper.toEntity(documentoDTO);
        documento = this.documentoRepository.save(documento);
        return this.documentoMapper.toDto(documento);
    }

    /**
     * Get all the documentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DocumentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Documentos");
        return documentoRepository.findAll(pageable)
            .map(documentoMapper::toDto);
    }


    /**
     * Get one documento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DocumentoDTO> findOne(Long id) {
        log.debug("Request to get Documento : {}", id);
        return documentoRepository.findById(id)
            .map(documentoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public DocumentoDTO findByHash(String hash) {
        Documento documento = this.documentoRepository.findByHash(hash);
        if (documento != null) {
            return this.documentoMapper.toDto(documento);
        }

        return null;
    }

    /**
     * Delete the documento by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Documento : {}", id);
        documentoRepository.deleteById(id);
    }

    @Transactional
    public Resource getByHashToDownload(String hash) {
        return this.storageServiceInterface.loadFromAdjuntoFile(hash);
    }

    public void updateDocumentos(Set<DocumentoDTO> documentoDTOs) {
        for (Iterator<DocumentoDTO> it = documentoDTOs.iterator(); it.hasNext(); ) {
            this.documentoRepository.save(this.documentoMapper.toEntity(it.next()));
        }
    }

    @Transactional
    public Optional<DocumentoDTO> findByProvidencia(ProvidenciaDTO providenciaDTO) {
        Set<Documento> documentos = this.documentoRepository.findByProvidencia(this.providenciaMapper.toEntity(providenciaDTO));

        return documentos.stream().findFirst().map(this.documentoMapper::toDto);
    }
}
