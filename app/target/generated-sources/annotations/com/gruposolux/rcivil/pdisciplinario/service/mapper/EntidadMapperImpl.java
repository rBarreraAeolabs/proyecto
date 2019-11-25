package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-25T15:30:52-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class EntidadMapperImpl implements EntidadMapper {

    @Override
    public EntidadDTO toDto(Entidad entidad) {
        if ( entidad == null ) {
            return null;
        }

        EntidadDTO entidadDTO = new EntidadDTO();

        entidadDTO.setId( entidad.getId() );
        entidadDTO.setNombre( entidad.getNombre() );

        return entidadDTO;
    }

    @Override
    public Entidad toEntity(EntidadDTO entidadDTO) {
        if ( entidadDTO == null ) {
            return null;
        }

        Entidad entidad = new Entidad();

        entidad.setId( entidadDTO.getId() );
        entidad.setNombre( entidadDTO.getNombre() );

        return entidad;
    }

    @Override
    public List<EntidadDTO> toDto(List<Entidad> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<EntidadDTO> list = new ArrayList<EntidadDTO>( entidades.size() );
        for ( Entidad entidad : entidades ) {
            list.add( toDto( entidad ) );
        }

        return list;
    }

    @Override
    public List<Entidad> toEntity(List<EntidadDTO> entidadDTOs) {
        if ( entidadDTOs == null ) {
            return null;
        }

        List<Entidad> list = new ArrayList<Entidad>( entidadDTOs.size() );
        for ( EntidadDTO entidadDTO : entidadDTOs ) {
            list.add( toEntity( entidadDTO ) );
        }

        return list;
    }
}
