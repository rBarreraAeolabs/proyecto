package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.GrupoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Grupo and its DTO GrupoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrupoMapper extends EntityMapper<GrupoDTO, Grupo> {

    Grupo toEntity(GrupoDTO grupoDTO);

    default Grupo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Grupo grupo = new Grupo();
        grupo.setId(id);
        return grupo;
    }
}
