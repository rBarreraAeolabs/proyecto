package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.repository.AdjuntoRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.AdjuntoMapper;
import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.storage.StorageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing Adjunto.
 */
@Service
@Transactional
public class AdjuntoService {

    private final Logger log = LoggerFactory.getLogger(AdjuntoService.class);

    private final AdjuntoRepository adjuntoRepository;
    private final AdjuntoMapper adjuntoMapper;
    private StorageServiceInterface storageServiceInterface;

    public AdjuntoService(AdjuntoRepository adjuntoRepository, AdjuntoMapper adjuntoMapper, StorageServiceInterface storageServiceInterface) {
        this.adjuntoRepository = adjuntoRepository;
        this.adjuntoMapper = adjuntoMapper;
        this.storageServiceInterface = storageServiceInterface;
    }

    /**
     * Save a adjunto.
     *
     * @param adjuntoDTO the entity to save
     * @return the persisted entity
     */
    public AdjuntoDTO save(AdjuntoDTO adjuntoDTO) {
        log.debug("Request to save Adjunto : {}", adjuntoDTO);
        Adjunto adjunto = adjuntoMapper.toEntity(adjuntoDTO);
        adjunto = adjuntoRepository.save(adjunto);
        return adjuntoMapper.toDto(adjunto);
    }

    /**
     * Get all the adjuntos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AdjuntoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Adjuntos");
        return adjuntoRepository.findAll(pageable)
            .map(adjuntoMapper::toDto);
    }


    /**
     * Get one adjunto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AdjuntoDTO> findOne(Long id) {
        log.debug("Request to get Adjunto : {}", id);
        return adjuntoRepository.findById(id)
            .map(adjuntoMapper::toDto);
    }

    /**
     * Delete the adjunto by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Adjunto : {}", id);
        adjuntoRepository.deleteById(id);
    }

    public void updateAdjuntos(Set<AdjuntoDTO> adjuntos)
    {
        for(Iterator<AdjuntoDTO> it = adjuntos.iterator(); it.hasNext();)
        {
            this.save(it.next());
        }
    }

    @Transactional
    public Resource getByHashToDownload(String hash)
    {
        return this.storageServiceInterface.loadFromAdjuntoFile(hash.trim());
    }

    @Transactional
    public List<Adjunto> getByProvidencia(Providencia providencia)
    {
        if (providencia != null)
        {
            return this.adjuntoRepository.findByProvidencia(providencia);
        }
        return null;
    }

    @Transactional
    public void updateMovimiento(MovimientoProvidencia movimientoProvidencia, Providencia providencia, Long idAdjunto)
    {
        this.adjuntoRepository.updateMovimiento(movimientoProvidencia, providencia, idAdjunto);
    }

    @Transactional
    public AdjuntoDTO findByHash(String hash)
    {
        return this.adjuntoMapper.toDto(this.adjuntoRepository.findByHash(hash));
    }
}
