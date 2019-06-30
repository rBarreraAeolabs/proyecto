package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;

import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Providencia and its DTO ProvidenciaDTO.
 */
@Mapper(componentModel = "spring",
    uses = {SumarioAdministrativoMapper.class, InvestigacionSumariaMapper.class, AdjuntoMapper.class, DocumentoMapper.class, MovimientoProvidenciaMapper.class})
public interface ProvidenciaMapper extends EntityMapper<ProvidenciaDTO, Providencia> {

    @Mapping(source = "sumarioAdministrativo.id", target = "sumarioAdministrativoId")
    @Mapping(source = "investigacionSumaria.id", target = "investigacionSumariaId")
    @Mapping(source = "providenciaMadre.id", target = "providenciaMadreId")
    @Mapping(source = "providenciaMadre.numero", target = "numeroReferenciaPorvidenciaMadre")
    ProvidenciaDTO toDto(Providencia providencia);

    @Mapping(source = "sumarioAdministrativoId", target = "sumarioAdministrativo")
    @Mapping(source = "investigacionSumariaId", target = "investigacionSumaria")
    Providencia toEntity(ProvidenciaDTO providenciaDTO);

    default Providencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Providencia providencia = new Providencia();
        providencia.setId(id);
        return providencia;
    }
}
