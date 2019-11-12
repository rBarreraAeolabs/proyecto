package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.domain.*;

import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity Adjunto and its DTO AdjuntoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvidenciaMapper.class, MovimientoProvidenciaMapper.class, RespuestaMapper.class})
public interface AdjuntoMapper extends EntityMapper<AdjuntoDTO, Adjunto> {

    @Mapping(source = "providencia.id", target = "providenciaId")
    @Mapping(source = "movimientoProvidencia.id", target = "movimientoProvidenciaId")
    @Mapping(source = "respuesta.id", target = "respuestaId")
    AdjuntoDTO toDto(Adjunto adjunto);

    @Mapping(source = "providenciaId", target = "providencia")
    @Mapping(source = "movimientoProvidenciaId", target = "movimientoProvidencia")
    @Mapping(source = "respuestaId", target = "respuesta")
    Adjunto toEntity(AdjuntoDTO adjuntoDTO);

    List<AdjuntoDTO> toDto(List<Adjunto> adjunto);

    List<Adjunto> toEntity(List<AdjuntoDTO> adjuntoDTO);

    default Adjunto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Adjunto adjunto = new Adjunto();
        adjunto.setId(id);
        return adjunto;
    }
}
