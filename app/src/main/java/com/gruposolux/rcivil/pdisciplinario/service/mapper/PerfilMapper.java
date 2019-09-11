package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Authority;
import com.gruposolux.rcivil.pdisciplinario.domain.Perfil;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PerfilDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity Perfil and its DTO PerfilDTO.
 */
@Mapper(componentModel = "spring", uses = {GrupoMapper.class})
public interface PerfilMapper extends EntityMapper<PerfilDTO, Perfil> {

    PerfilDTO toDto(Perfil perfil);

    List<PerfilDTO> toDto(List<Perfil> perfils);

    List<Perfil> toEntity(List<PerfilDTO> perfils);

    Perfil toEntity(PerfilDTO perfilDTO);


    default Perfil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Perfil perfil = new Perfil();
        perfil.setId(id);
        return perfil;
    }

    default Set<String> stringsFromAuthorities(Set<Authority> authorities) {
        return authorities.stream().map(Authority::getName)
            .collect(Collectors.toSet());
    }

    default Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }
}
