package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AuthorityDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-22T10:23:56-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public AuthorityDTO toDto(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityDTO authorityDTO = new AuthorityDTO();

        authorityDTO.setDescripcion( authority.getDescripcion() );
        authorityDTO.setName( authority.getName() );

        return authorityDTO;
    }

    @Override
    public List<AuthorityDTO> toDto(List<Authority> authorities) {
        if ( authorities == null ) {
            return null;
        }

        List<AuthorityDTO> list = new ArrayList<AuthorityDTO>( authorities.size() );
        for ( Authority authority : authorities ) {
            list.add( toDto( authority ) );
        }

        return list;
    }

    @Override
    public Authority toEntity(AuthorityDTO authorityDTO) {
        if ( authorityDTO == null ) {
            return null;
        }

        Authority authority = new Authority();

        authority.setName( authorityDTO.getName() );
        authority.setDescripcion( authorityDTO.getDescripcion() );

        return authority;
    }

    @Override
    public List<Authority> toEntity(List<AuthorityDTO> authorityDTOS) {
        if ( authorityDTOS == null ) {
            return null;
        }

        List<Authority> list = new ArrayList<Authority>( authorityDTOS.size() );
        for ( AuthorityDTO authorityDTO : authorityDTOS ) {
            list.add( toEntity( authorityDTO ) );
        }

        return list;
    }
}
