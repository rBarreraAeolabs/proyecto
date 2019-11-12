package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.SumarioAdministrativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SumarioAdministrativo and its DTO SumarioAdministrativoDTO.
 */
@Mapper(componentModel = "spring", uses = {InvestigacionSumariaMapper.class})
public interface SumarioAdministrativoMapper extends EntityMapper<SumarioAdministrativoDTO, SumarioAdministrativo> {

    @Mapping(source = "investigacionSumaria.id", target = "investigacionSumariaId")
    SumarioAdministrativoDTO toDto(SumarioAdministrativo sumarioAdministrativo);

    @Mapping(source = "investigacionSumariaId", target = "investigacionSumaria")
    @Mapping(target = "providencias", ignore = true)
    @Mapping(target = "movimientosSumarioAdmins", ignore = true)
    SumarioAdministrativo toEntity(SumarioAdministrativoDTO sumarioAdministrativoDTO);

    default SumarioAdministrativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();
        sumarioAdministrativo.setId(id);
        return sumarioAdministrativo;
    }
}
