package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.domain.Perfil;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PerfilDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T15:58:09-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class PerfilMapperImpl implements PerfilMapper {

    @Override
    public PerfilDTO toDto(Perfil perfil) {
        if ( perfil == null ) {
            return null;
        }

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setId( perfil.getId() );
        perfilDTO.setNombre( perfil.getNombre() );
        Set<Authority> set = perfil.getAuthorities();
        if ( set != null ) {
            perfilDTO.setAuthorities( new HashSet<Authority>( set ) );
        }
        else {
            perfilDTO.setAuthorities( null );
        }

        return perfilDTO;
    }

    @Override
    public List<PerfilDTO> toDto(List<Perfil> perfils) {
        if ( perfils == null ) {
            return null;
        }

        List<PerfilDTO> list = new ArrayList<PerfilDTO>( perfils.size() );
        for ( Perfil perfil : perfils ) {
            list.add( toDto( perfil ) );
        }

        return list;
    }

    @Override
    public List<Perfil> toEntity(List<PerfilDTO> perfils) {
        if ( perfils == null ) {
            return null;
        }

        List<Perfil> list = new ArrayList<Perfil>( perfils.size() );
        for ( PerfilDTO perfilDTO : perfils ) {
            list.add( toEntity( perfilDTO ) );
        }

        return list;
    }

    @Override
    public Perfil toEntity(PerfilDTO perfilDTO) {
        if ( perfilDTO == null ) {
            return null;
        }

        Perfil perfil = new Perfil();

        perfil.setId( perfilDTO.getId() );
        perfil.setNombre( perfilDTO.getNombre() );
        Set<Authority> set = perfilDTO.getAuthorities();
        if ( set != null ) {
            perfil.setAuthorities( new HashSet<Authority>( set ) );
        }
        else {
            perfil.setAuthorities( null );
        }

        return perfil;
    }
}
