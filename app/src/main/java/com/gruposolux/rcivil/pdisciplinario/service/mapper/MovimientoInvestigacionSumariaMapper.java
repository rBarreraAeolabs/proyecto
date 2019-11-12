package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoInvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoInvestigacionSumariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MovimientoInvestigacionSumaria and its DTO MovimientoInvestigacionSumariaDTO.
 */
@Mapper(componentModel = "spring", uses = {InvestigacionSumariaMapper.class})
public interface MovimientoInvestigacionSumariaMapper extends EntityMapper<MovimientoInvestigacionSumariaDTO, MovimientoInvestigacionSumaria> {

    @Mapping(source = "investigacionSumaria.id", target = "investigacionSumariaId")
    MovimientoInvestigacionSumariaDTO toDto(MovimientoInvestigacionSumaria movimientoInvestigacionSumaria);

    @Mapping(source = "investigacionSumariaId", target = "investigacionSumaria")
    MovimientoInvestigacionSumaria toEntity(MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO);

    default MovimientoInvestigacionSumaria fromId(Long id) {
        if (id == null) {
            return null;
        }
        MovimientoInvestigacionSumaria movimientoInvestigacionSumaria = new MovimientoInvestigacionSumaria();
        movimientoInvestigacionSumaria.setId(id);
        return movimientoInvestigacionSumaria;
    }
}
