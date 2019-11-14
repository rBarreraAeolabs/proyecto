package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntidadMapper extends EntityMapper<EntidadDTO, Entidad>
{
    EntidadDTO toDto(Entidad entidad);

    Entidad toEntity(EntidadDTO entidadDTO);

    List<EntidadDTO> toDto(List<Entidad> entidades);

    List<Entidad> toEntity(List<EntidadDTO> entidadDTOs);

    default Entidad fromId(Long id)
    {
        if (id == null)
        {
            return null;
        }

        Entidad entidad = new Entidad();
        entidad.setId(id);

        return entidad;
    }
}
