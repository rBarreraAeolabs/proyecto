package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DerivacionDTO;

import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity Derivacion and its DTO DerivacionDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvidenciaMapper.class, UserMapper.class})
public interface DerivacionMapper extends EntityMapper<DerivacionDTO, Derivacion> {

    @Mapping(source = "providencia.id", target = "providenciaId")
    @Mapping(source = "derivadoAUsuario.id", target = "derivadoAUsuarioId")
    @Mapping(source = "derivadoPorUsuario.id", target = "derivadoPorUsuarioId")
    @Mapping(source = "derivadoAGrupo.id", target = "derivadoAGrupoId")
    @Mapping(source = "derivadoPorGrupo.id", target = "derivadoPorGrupoId")
    DerivacionDTO toDto(Derivacion derivacion);

    @Mapping(source = "providenciaId", target = "providencia.id")
    @Mapping(source = "derivadoAUsuarioId", target = "derivadoAUsuario.id")
    @Mapping(source = "derivadoPorUsuarioId", target = "derivadoPorUsuario.id")
    @Mapping(source = "derivadoAGrupoId", target = "derivadoAGrupo.id")
    @Mapping(source = "derivadoPorGrupoId", target = "derivadoPorGrupo.id")
    Derivacion toEntity(DerivacionDTO derivacionDTO);

    default Derivacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Derivacion derivacion = new Derivacion();
        derivacion.setId(id);
        return derivacion;
    }
}
