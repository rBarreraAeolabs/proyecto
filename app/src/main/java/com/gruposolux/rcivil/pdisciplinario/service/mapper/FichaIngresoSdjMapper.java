package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.FichaIngresoSdj;
import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FichaIngresoSdjDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FichaIngresoSdj and its DTO FichaIngresoSdjDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FichaIngresoSdjMapper extends EntityMapper<FichaIngresoSdjDTO, FichaIngresoSdj> {

    FichaIngresoSdj toEntity(FichaIngresoSdjDTO fichaIngresoSdjDTO);

    default FichaIngresoSdj fromId(Long id) {
        if (id == null) {
            return null;
        }
        FichaIngresoSdj fichaIngresoSdj = new FichaIngresoSdj();
        fichaIngresoSdj.setId(id);
        return fichaIngresoSdj;
    }
}
