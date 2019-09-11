package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.service.dto.RespuestaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by sneiraillanes on 22-04-2019.
 */
@Mapper(componentModel = "spring", uses = {AdjuntoMapper.class, DocumentoMapper.class, UserMapper.class})
public interface RespuestaMapper extends EntityMapper<RespuestaDTO, Respuesta> {
    Respuesta toEntity(RespuestaDTO respuestaDTO);

    @Mapping(source = "user.firstName", target = "userName")
    @Mapping(source = "user.lastName", target = "userLastname")
    @Mapping(source = "user.id", target = "userId")
    RespuestaDTO toDto(Respuesta respuesta);

    default Respuesta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Respuesta respuesta = new Respuesta();
        respuesta.setId(id);
        return respuesta;
    }
}
