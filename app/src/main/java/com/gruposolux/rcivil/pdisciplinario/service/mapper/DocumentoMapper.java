package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;

import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity Documento and its DTO DocumentoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvidenciaMapper.class, DerivacionMapper.class, MovimientoProvidenciaMapper.class, RespuestaMapper.class})
public interface DocumentoMapper extends EntityMapper<DocumentoDTO, Documento> {

    @Mapping(source = "providencia.id", target = "providenciaId")
    @Mapping(source = "derivacion.id", target = "derivacionId")
    @Mapping(source = "movimientoProvidencia.id", target = "movimientoProvidenciaId")
    @Mapping(source = "respuesta.id", target = "respuestaId")
    DocumentoDTO toDto(Documento documento);

    @Mapping(source = "providenciaId", target = "providencia")
    @Mapping(source = "derivacionId", target = "derivacion")
    @Mapping(source = "movimientoProvidenciaId", target = "movimientoProvidencia")
    @Mapping(source = "respuestaId", target = "respuesta")
    Documento toEntity(DocumentoDTO documentoDTO);

    List<DocumentoDTO> toDto(List<Documento> documento);

    List<Documento> toEntity(List<DocumentoDTO> documentoDTO);

    default Documento fromId(Long id) {
        if (id == null) {
            return null;
        }
        Documento documento = new Documento();
        documento.setId(id);
        return documento;
    }
}
