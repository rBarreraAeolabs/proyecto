package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.repository.RespuestaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.RespuestaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by sneiraillanes on 22-04-2019.
 */
@Service
@Transactional
public class RespuestaService
{
    private final Logger log = LoggerFactory.getLogger(RespuestaService.class);
    private final RespuestaMapper respuestaMapper;
    private final RespuestaRepository respuestaRepository;
    private final AdjuntoMapper adjuntoMapper;
    private final AdjuntoService adjuntoService;
    private final DocumentoMapper documentoMapper;
    private final DocumentoService documentoService;
    private final ProvidenciaMapper providenciaMapper;
    private final UserService userService;

    public RespuestaService(
        RespuestaMapper respuestaMapper,
        RespuestaRepository respuestaRepository,
        AdjuntoMapper adjuntoMapper,
        AdjuntoService adjuntoService,
        DocumentoMapper documentoMapper,
        DocumentoService documentoService,
        ProvidenciaMapper providenciaMapper,
        UserService userService
    ) {
        this.respuestaMapper = respuestaMapper;
        this.respuestaRepository = respuestaRepository;
        this.adjuntoMapper = adjuntoMapper;
        this.adjuntoService = adjuntoService;
        this.documentoMapper = documentoMapper;
        this.documentoService = documentoService;
        this.providenciaMapper = providenciaMapper;
        this.userService = userService;
    }

    public RespuestaDTO save(RespuestaDTO respuestaDTO)
    {
        Respuesta respuesta = this.respuestaMapper.toEntity(respuestaDTO);
        User user = this.userService.getCurrentUser();
        respuesta.setUser(user);
        respuesta = this.respuestaRepository.save(respuesta);
        if (respuestaDTO.getAdjuntos() != null && respuestaDTO.getAdjuntos().size() > 0)
        {
            this.setIdRespuestaOnAdjuntos(respuestaDTO.getAdjuntos(), respuesta.getId(), respuesta.getProvidencia().getId());
        }
        if (respuestaDTO.getDocumentos() != null && respuestaDTO.getDocumentos().size() > 0)
        {
            this.setIdRespuestaOnDocumentos(respuestaDTO.getDocumentos(), respuesta.getId(), respuesta.getProvidencia().getId());
        }
        return this.respuestaMapper.toDto(respuesta);
    }

    @Transactional(readOnly = true)
    public List<RespuestaDTO> getAll()
    {
        List<Respuesta> respuestas = this.respuestaRepository.findAll();
        return respuestas.stream().map(this.respuestaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Optional<RespuestaDTO> findOne(Long id)
    {
        return this.respuestaRepository.findById(id).map(this.respuestaMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Respuesta : {}", id);
        this.respuestaRepository.deleteById(id);
    }

    @Transactional
    public Optional<RespuestaDTO> findOneByProvidencia(ProvidenciaDTO providenciaDTO)
    {
        return this.respuestaRepository.findByProvidencia(this.providenciaMapper.toEntity(providenciaDTO),
            this.userService.getCurrentUser(), providenciaDTO.getEstadoActual()).map(this.respuestaMapper::toDto);
    }

    public void updateMovimientoProvidencia(Long movimientoProvideniaId, Long providenciaId, String estadoProvidencia)
    {
        this.respuestaRepository.updateMovimientoProvidencia(movimientoProvideniaId, providenciaId,
            this.userService.getCurrentUser().getId(), estadoProvidencia);
    }

    @Transactional
    public RespuestaDTO findByMovimientoProvidencia(Long movimientoProvidenciaId)
    {
        Long id = this.respuestaRepository.findByMovimientoPrividencia(movimientoProvidenciaId);

        if (id != null)
        {
            return this.respuestaMapper.toDto(this.respuestaRepository.getOne(id));
        }

        return new RespuestaDTO();
    }

    private void setIdRespuestaOnAdjuntos(Set<AdjuntoDTO> adjuntoDTOs, Long respuestaId, Long providenciaId)
    {
        for(Iterator<AdjuntoDTO> it = adjuntoDTOs.iterator(); it.hasNext();)
        {
            AdjuntoDTO adjuntoDto = it.next();
            adjuntoDto.setRespuestaId(respuestaId);
            adjuntoDto.setProvidenciaId(providenciaId);
        }
        this.adjuntoService.updateAdjuntos(adjuntoDTOs);
    }

    private void setIdRespuestaOnDocumentos(Set<DocumentoDTO> documentoDTOs, Long respuestaId, Long providenciaId)
    {
        for(Iterator<DocumentoDTO> it = documentoDTOs.iterator(); it.hasNext();)
        {
            DocumentoDTO documentoDTO = it.next();
            documentoDTO.setRespuestaId(respuestaId);
            documentoDTO.setProvidenciaId(providenciaId);
        }
        this.documentoService.updateDocumentos(documentoDTOs);
    }
}
