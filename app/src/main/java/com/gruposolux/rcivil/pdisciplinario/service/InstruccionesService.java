package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Instrucciones;
import com.gruposolux.rcivil.pdisciplinario.repository.InstruccionesRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InstruccionesDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.InstruccionesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstruccionesService
{
    private final Logger log = LoggerFactory.getLogger(InstruccionesService.class);
    private final InstruccionesMapper instruccionesMapper;
    private final InstruccionesRepository instruccionesRepository;

    public InstruccionesService(InstruccionesMapper instruccionesMapper, InstruccionesRepository instruccionesRepository)
    {
        this.instruccionesMapper = instruccionesMapper;
        this.instruccionesRepository = instruccionesRepository;
    }

    public InstruccionesDTO save(InstruccionesDTO instruccionesDTO)
    {
        Instrucciones instrucciones = this.instruccionesMapper.toEntity(instruccionesDTO);
        instrucciones = this.instruccionesRepository.save(instrucciones);
        return this.instruccionesMapper.toDto(instrucciones);
    }

    @Transactional
    public Optional<InstruccionesDTO> findOne(Long id)
    {
        return this.instruccionesRepository.findById(id).map(this.instruccionesMapper::toDto);
    }

    @Transactional
    public Page<InstruccionesDTO> page(Pageable pageable)
    {
        return this.instruccionesRepository.findAll(pageable).map(this.instruccionesMapper::toDto);
    }

    public void delete(Long id)
    {
        this.instruccionesRepository.deleteById(id);
    }

    @Transactional
    public List<InstruccionesDTO> getAll()
    {
        return this.instruccionesRepository.findAll().stream().map(this.instruccionesMapper::toDto).collect(Collectors.toList());
    }
}
