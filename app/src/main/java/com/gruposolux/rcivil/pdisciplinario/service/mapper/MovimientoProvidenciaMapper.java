package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoProvidenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MovimientoProvidencia and its DTO MovimientoProvidenciaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvidenciaMapper.class, PlazoMapper.class, DocumentoMapper.class,
    AdjuntoMapper.class, UserMapper.class})
public interface MovimientoProvidenciaMapper extends EntityMapper<MovimientoProvidenciaDTO, MovimientoProvidencia> {

    @Mapping(source = "providencia.id", target = "providenciaId")
    @Mapping(source = "plazo.id", target = "plazoId")
    @Mapping(source = "plazo.dias", target = "plazoDias")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    MovimientoProvidenciaDTO toDto(MovimientoProvidencia movimientoProvidencia);

    @Mapping(source = "providenciaId", target = "providencia")
    @Mapping(source = "plazoId", target = "plazo.id")
    @Mapping(source = "plazoDias", target = "plazo.dias")
    @Mapping(source = "userId", target = "user.id")
    MovimientoProvidencia toEntity(MovimientoProvidenciaDTO movimientoProvidenciaDTO);

    default MovimientoProvidencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        MovimientoProvidencia movimientoProvidencia = new MovimientoProvidencia();
        movimientoProvidencia.setId(id);
        return movimientoProvidencia;
    }
}
