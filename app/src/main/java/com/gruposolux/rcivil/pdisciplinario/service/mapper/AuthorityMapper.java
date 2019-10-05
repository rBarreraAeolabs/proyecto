package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AuthorityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapper extends EntityMapper <AuthorityDTO, Authority> {

    AuthorityDTO toDto(Authority authority);

    List<AuthorityDTO> toDto(List<Authority> authorities);

    Authority toEntity(AuthorityDTO authorityDTO);

    List<Authority> toEntity(List<AuthorityDTO> authorityDTOS);

    default Authority fromName(String name) {
        if (name == null){
            return null;
        }

        Authority authority = new Authority();
        authority.setName(name);
        return authority;
    }
}
