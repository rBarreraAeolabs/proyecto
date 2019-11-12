package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Plantilla;
import com.gruposolux.rcivil.pdisciplinario.domain.*;

import com.gruposolux.rcivil.pdisciplinario.service.dto.PlantillaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Plantilla and its DTO PlantillaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlantillaMapper extends EntityMapper<PlantillaDTO, Plantilla> {



    default Plantilla fromId(Long id) {
        if (id == null) {
            return null;
        }
        Plantilla plantilla = new Plantilla();
        plantilla.setId(id);
        return plantilla;
    }
}
