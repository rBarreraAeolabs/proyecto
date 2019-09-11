package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.repository.EntidadRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.EntidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
@Service
@Transactional
public class EntidadService {
    private final Logger log = LoggerFactory.getLogger(EntidadService.class);
    private final EntidadMapper entidadMapper;
    private final EntidadRepository entidadRepository;

    public EntidadService(EntidadMapper entidadMapper, EntidadRepository entidadRepository) {
        this.entidadMapper = entidadMapper;
        this.entidadRepository = entidadRepository;
    }

    public EntidadDTO save(EntidadDTO entidadDTO) {
        Entidad entidad = this.entidadMapper.toEntity(entidadDTO);
        entidad = this.entidadRepository.save(entidad);
        return this.entidadMapper.toDto(entidad);
    }

    @Transactional
    public Optional<EntidadDTO> findOne(Long id) {
        return this.entidadRepository.findById(id).map(this.entidadMapper::toDto);
    }

    @Transactional
    public Page<EntidadDTO> page(Pageable pageable) {
        return this.entidadRepository.findAll(pageable).map(this.entidadMapper::toDto);
    }

    public void delete(Long id) {
        this.entidadRepository.deleteById(id);
    }

    @Transactional
    public List<EntidadDTO> getAll() {
        return this.entidadRepository.findAll().stream().map(this.entidadMapper::toDto).collect(Collectors.toList());
    }
}
