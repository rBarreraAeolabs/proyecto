package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DerivacionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-14T20:35:13-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class DerivacionMapperImpl implements DerivacionMapper {

    @Override
    public List<Derivacion> toEntity(List<DerivacionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Derivacion> list = new ArrayList<Derivacion>( dtoList.size() );
        for ( DerivacionDTO derivacionDTO : dtoList ) {
            list.add( toEntity( derivacionDTO ) );
        }

        return list;
    }

    @Override
    public List<DerivacionDTO> toDto(List<Derivacion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DerivacionDTO> list = new ArrayList<DerivacionDTO>( entityList.size() );
        for ( Derivacion derivacion : entityList ) {
            list.add( toDto( derivacion ) );
        }

        return list;
    }

    @Override
    public DerivacionDTO toDto(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }

        DerivacionDTO derivacionDTO = new DerivacionDTO();

        Long id = derivacionDerivadoAUsuarioId( derivacion );
        if ( id != null ) {
            derivacionDTO.setDerivadoAUsuarioId( id );
        }
        Long id1 = derivacionDerivadoPorUsuarioId( derivacion );
        if ( id1 != null ) {
            derivacionDTO.setDerivadoPorUsuarioId( id1 );
        }
        Long id2 = derivacionDerivadoAGrupoId( derivacion );
        if ( id2 != null ) {
            derivacionDTO.setDerivadoAGrupoId( id2 );
        }
        Long id3 = derivacionProvidenciaId( derivacion );
        if ( id3 != null ) {
            derivacionDTO.setProvidenciaId( id3 );
        }
        Long id4 = derivacionDerivadoPorGrupoId( derivacion );
        if ( id4 != null ) {
            derivacionDTO.setDerivadoPorGrupoId( id4 );
        }
        derivacionDTO.setId( derivacion.getId() );
        derivacionDTO.setObservacion( derivacion.getObservacion() );
        derivacionDTO.setFechaDerivacion( derivacion.getFechaDerivacion() );
        derivacionDTO.setEstado( derivacion.getEstado() );
        derivacionDTO.setTipo( derivacion.getTipo() );

        return derivacionDTO;
    }

    @Override
    public Derivacion toEntity(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        Derivacion derivacion = new Derivacion();

        derivacion.setDerivadoPorUsuario( derivacionDTOToUser( derivacionDTO ) );
        derivacion.setDerivadoPorGrupo( derivacionDTOToGrupo( derivacionDTO ) );
        derivacion.setDerivadoAGrupo( derivacionDTOToGrupo1( derivacionDTO ) );
        derivacion.setProvidencia( derivacionDTOToProvidencia( derivacionDTO ) );
        derivacion.setDerivadoAUsuario( derivacionDTOToUser1( derivacionDTO ) );
        derivacion.setId( derivacionDTO.getId() );
        derivacion.setObservacion( derivacionDTO.getObservacion() );
        derivacion.setFechaDerivacion( derivacionDTO.getFechaDerivacion() );
        derivacion.setEstado( derivacionDTO.getEstado() );
        derivacion.setTipo( derivacionDTO.getTipo() );

        return derivacion;
    }

    private Long derivacionDerivadoAUsuarioId(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }
        User derivadoAUsuario = derivacion.getDerivadoAUsuario();
        if ( derivadoAUsuario == null ) {
            return null;
        }
        Long id = derivadoAUsuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long derivacionDerivadoPorUsuarioId(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }
        User derivadoPorUsuario = derivacion.getDerivadoPorUsuario();
        if ( derivadoPorUsuario == null ) {
            return null;
        }
        Long id = derivadoPorUsuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long derivacionDerivadoAGrupoId(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }
        Grupo derivadoAGrupo = derivacion.getDerivadoAGrupo();
        if ( derivadoAGrupo == null ) {
            return null;
        }
        Long id = derivadoAGrupo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long derivacionProvidenciaId(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }
        Providencia providencia = derivacion.getProvidencia();
        if ( providencia == null ) {
            return null;
        }
        Long id = providencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long derivacionDerivadoPorGrupoId(Derivacion derivacion) {
        if ( derivacion == null ) {
            return null;
        }
        Grupo derivadoPorGrupo = derivacion.getDerivadoPorGrupo();
        if ( derivadoPorGrupo == null ) {
            return null;
        }
        Long id = derivadoPorGrupo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User derivacionDTOToUser(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( derivacionDTO.getDerivadoPorUsuarioId() );

        return user;
    }

    protected Grupo derivacionDTOToGrupo(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        Grupo grupo = new Grupo();

        grupo.setId( derivacionDTO.getDerivadoPorGrupoId() );

        return grupo;
    }

    protected Grupo derivacionDTOToGrupo1(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        Grupo grupo = new Grupo();

        grupo.setId( derivacionDTO.getDerivadoAGrupoId() );

        return grupo;
    }

    protected Providencia derivacionDTOToProvidencia(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        Providencia providencia = new Providencia();

        providencia.setId( derivacionDTO.getProvidenciaId() );

        return providencia;
    }

    protected User derivacionDTOToUser1(DerivacionDTO derivacionDTO) {
        if ( derivacionDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( derivacionDTO.getDerivadoAUsuarioId() );

        return user;
    }
}
