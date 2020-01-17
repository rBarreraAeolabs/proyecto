package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.GrupoDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-16T09:49:06-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class GrupoMapperImpl implements GrupoMapper {

    @Override
    public GrupoDTO toDto(Grupo entity) {
        if ( entity == null ) {
            return null;
        }

        GrupoDTO grupoDTO = new GrupoDTO();

        grupoDTO.setId( entity.getId() );
        grupoDTO.setNombre( entity.getNombre() );
        Set<User> set = entity.getUsuariosEnGrupo();
        if ( set != null ) {
            grupoDTO.setUsuariosEnGrupo( new HashSet<User>( set ) );
        }
        else {
            grupoDTO.setUsuariosEnGrupo( null );
        }

        return grupoDTO;
    }

    @Override
    public List<Grupo> toEntity(List<GrupoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Grupo> list = new ArrayList<Grupo>( dtoList.size() );
        for ( GrupoDTO grupoDTO : dtoList ) {
            list.add( toEntity( grupoDTO ) );
        }

        return list;
    }

    @Override
    public List<GrupoDTO> toDto(List<Grupo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GrupoDTO> list = new ArrayList<GrupoDTO>( entityList.size() );
        for ( Grupo grupo : entityList ) {
            list.add( toDto( grupo ) );
        }

        return list;
    }

    @Override
    public Grupo toEntity(GrupoDTO grupoDTO) {
        if ( grupoDTO == null ) {
            return null;
        }

        Grupo grupo = new Grupo();

        grupo.setId( grupoDTO.getId() );
        grupo.setNombre( grupoDTO.getNombre() );
        Set<User> set = grupoDTO.getUsuariosEnGrupo();
        if ( set != null ) {
            grupo.setUsuariosEnGrupo( new HashSet<User>( set ) );
        }
        else {
            grupo.setUsuariosEnGrupo( null );
        }

        return grupo;
    }
}
