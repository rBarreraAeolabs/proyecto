package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Instrucciones;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InstruccionesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface InstruccionesMapper extends EntityMapper <InstruccionesDTO, Instrucciones> {

    InstruccionesDTO toDto(Instrucciones instrucciones);

    Instrucciones toEntity(InstruccionesDTO instruccionesDTO);

    List<InstruccionesDTO> toDto(List<Instrucciones> instrucciones);

    List<Instrucciones> toEntity(List<InstruccionesDTO> instruccionesDTOs);

    default Instrucciones fromId(Long id)
    {
        if (id == null)
        {
            return null;
        }

        Instrucciones instrucciones = new Instrucciones();
        instrucciones.setId(id);

        return instrucciones;
    }

}
