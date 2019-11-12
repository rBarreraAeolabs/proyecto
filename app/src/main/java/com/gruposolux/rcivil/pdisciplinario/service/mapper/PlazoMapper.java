package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import com.gruposolux.rcivil.pdisciplinario.domain.*;

import com.gruposolux.rcivil.pdisciplinario.service.dto.PlazoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Plazo and its DTO PlazoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlazoMapper extends EntityMapper<PlazoDTO, Plazo> {


    @Mapping(target = "movimientosProvidencis", ignore = true)
    Plazo toEntity(PlazoDTO plazoDTO);

    default Plazo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Plazo plazo = new Plazo();
        plazo.setId(id);
        return plazo;
    }
}
