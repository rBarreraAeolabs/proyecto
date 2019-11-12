package com.gruposolux.rcivil.pdisciplinario.service.mapper;


import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;

import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.NotificacionInBrowserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * creado por ruben barrera
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GrupoMapper.class})
public interface NotificacionInBrowserMapper extends EntityMapper<NotificacionInBrowserDTO, NotificacionInBrowser> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "grupo.id", target = "grupoId")
        NotificacionInBrowserDTO toDto(NotificacionInBrowser notificacionInBrowser);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "grupoId", target = "grupo")
        NotificacionInBrowser toEntity(NotificacionInBrowserDTO notificacionInBrowserDTO);

        default NotificacionInBrowser fromId(Long id) {
            if (id == null) {
                return null;
            }
            NotificacionInBrowser notificacionInBrowser = new NotificacionInBrowser();
            notificacionInBrowser.setId(id);
            return notificacionInBrowser;
        }
}
