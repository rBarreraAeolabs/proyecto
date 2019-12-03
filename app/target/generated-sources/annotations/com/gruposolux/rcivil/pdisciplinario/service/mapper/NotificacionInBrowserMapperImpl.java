package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.NotificacionInBrowserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T08:22:54-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class NotificacionInBrowserMapperImpl implements NotificacionInBrowserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GrupoMapper grupoMapper;

    @Override
    public List<NotificacionInBrowser> toEntity(List<NotificacionInBrowserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NotificacionInBrowser> list = new ArrayList<NotificacionInBrowser>( dtoList.size() );
        for ( NotificacionInBrowserDTO notificacionInBrowserDTO : dtoList ) {
            list.add( toEntity( notificacionInBrowserDTO ) );
        }

        return list;
    }

    @Override
    public List<NotificacionInBrowserDTO> toDto(List<NotificacionInBrowser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NotificacionInBrowserDTO> list = new ArrayList<NotificacionInBrowserDTO>( entityList.size() );
        for ( NotificacionInBrowser notificacionInBrowser : entityList ) {
            list.add( toDto( notificacionInBrowser ) );
        }

        return list;
    }

    @Override
    public NotificacionInBrowserDTO toDto(NotificacionInBrowser notificacionInBrowser) {
        if ( notificacionInBrowser == null ) {
            return null;
        }

        NotificacionInBrowserDTO notificacionInBrowserDTO = new NotificacionInBrowserDTO();

        Long id = notificacionInBrowserUserId( notificacionInBrowser );
        if ( id != null ) {
            notificacionInBrowserDTO.setUserId( id );
        }
        Long id1 = notificacionInBrowserGrupoId( notificacionInBrowser );
        if ( id1 != null ) {
            notificacionInBrowserDTO.setGrupoId( id1 );
        }
        if ( notificacionInBrowser.getId() != null ) {
            notificacionInBrowserDTO.setId( notificacionInBrowser.getId() );
        }
        notificacionInBrowserDTO.setContenido( notificacionInBrowser.getContenido() );
        notificacionInBrowserDTO.setCreatedAt( notificacionInBrowser.getCreatedAt() );
        notificacionInBrowserDTO.setVisto( notificacionInBrowser.getVisto() );

        return notificacionInBrowserDTO;
    }

    @Override
    public NotificacionInBrowser toEntity(NotificacionInBrowserDTO notificacionInBrowserDTO) {
        if ( notificacionInBrowserDTO == null ) {
            return null;
        }

        NotificacionInBrowser notificacionInBrowser = new NotificacionInBrowser();

        notificacionInBrowser.setGrupo( grupoMapper.fromId( notificacionInBrowserDTO.getGrupoId() ) );
        notificacionInBrowser.setUser( userMapper.userFromId( notificacionInBrowserDTO.getUserId() ) );
        notificacionInBrowser.setId( notificacionInBrowserDTO.getId() );
        notificacionInBrowser.setContenido( notificacionInBrowserDTO.getContenido() );
        notificacionInBrowser.setCreatedAt( notificacionInBrowserDTO.getCreatedAt() );
        notificacionInBrowser.setVisto( notificacionInBrowserDTO.getVisto() );

        return notificacionInBrowser;
    }

    private Long notificacionInBrowserUserId(NotificacionInBrowser notificacionInBrowser) {
        if ( notificacionInBrowser == null ) {
            return null;
        }
        User user = notificacionInBrowser.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long notificacionInBrowserGrupoId(NotificacionInBrowser notificacionInBrowser) {
        if ( notificacionInBrowser == null ) {
            return null;
        }
        Grupo grupo = notificacionInBrowser.getGrupo();
        if ( grupo == null ) {
            return null;
        }
        Long id = grupo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
