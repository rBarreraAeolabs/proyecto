package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoSumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoSumarioAdministrativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MovimientoSumarioAdministrativo and its DTO MovimientoSumarioAdministrativoDTO.
 */
@Mapper(componentModel = "spring", uses = {SumarioAdministrativoMapper.class})
public interface MovimientoSumarioAdministrativoMapper extends EntityMapper<MovimientoSumarioAdministrativoDTO, MovimientoSumarioAdministrativo> {

    @Mapping(source = "sumarioAdministrativo.id", target = "sumarioAdministrativoId")
    MovimientoSumarioAdministrativoDTO toDto(MovimientoSumarioAdministrativo movimientoSumarioAdministrativo);

    @Mapping(source = "sumarioAdministrativoId", target = "sumarioAdministrativo")
    MovimientoSumarioAdministrativo toEntity(MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO);

    default MovimientoSumarioAdministrativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        MovimientoSumarioAdministrativo movimientoSumarioAdministrativo = new MovimientoSumarioAdministrativo();
        movimientoSumarioAdministrativo.setId(id);
        return movimientoSumarioAdministrativo;
    }
}
