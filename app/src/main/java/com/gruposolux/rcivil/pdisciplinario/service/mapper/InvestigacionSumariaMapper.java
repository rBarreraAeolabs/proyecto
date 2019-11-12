package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InvestigacionSumariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InvestigacionSumaria and its DTO InvestigacionSumariaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvestigacionSumariaMapper extends EntityMapper<InvestigacionSumariaDTO, InvestigacionSumaria> {


    @Mapping(target = "providencias", ignore = true)
    @Mapping(target = "movimientoInvestSumarias", ignore = true)
    InvestigacionSumaria toEntity(InvestigacionSumariaDTO investigacionSumariaDTO);

    default InvestigacionSumaria fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();
        investigacionSumaria.setId(id);
        return investigacionSumaria;
    }
}
