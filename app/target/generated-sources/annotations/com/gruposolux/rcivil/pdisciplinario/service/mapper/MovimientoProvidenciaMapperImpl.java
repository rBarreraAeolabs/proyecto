package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoProvidenciaDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-05T14:58:23-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class MovimientoProvidenciaMapperImpl implements MovimientoProvidenciaMapper {

    @Autowired
    private ProvidenciaMapper providenciaMapper;
    @Autowired
    private DocumentoMapper documentoMapper;
    @Autowired
    private AdjuntoMapper adjuntoMapper;

    @Override
    public List<MovimientoProvidencia> toEntity(List<MovimientoProvidenciaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MovimientoProvidencia> list = new ArrayList<MovimientoProvidencia>( dtoList.size() );
        for ( MovimientoProvidenciaDTO movimientoProvidenciaDTO : dtoList ) {
            list.add( toEntity( movimientoProvidenciaDTO ) );
        }

        return list;
    }

    @Override
    public List<MovimientoProvidenciaDTO> toDto(List<MovimientoProvidencia> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MovimientoProvidenciaDTO> list = new ArrayList<MovimientoProvidenciaDTO>( entityList.size() );
        for ( MovimientoProvidencia movimientoProvidencia : entityList ) {
            list.add( toDto( movimientoProvidencia ) );
        }

        return list;
    }

    @Override
    public MovimientoProvidenciaDTO toDto(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }

        MovimientoProvidenciaDTO movimientoProvidenciaDTO = new MovimientoProvidenciaDTO();

        Long id = movimientoProvidenciaPlazoId( movimientoProvidencia );
        if ( id != null ) {
            movimientoProvidenciaDTO.setPlazoId( id );
        }
        Integer dias = movimientoProvidenciaPlazoDias( movimientoProvidencia );
        if ( dias != null ) {
            movimientoProvidenciaDTO.setPlazoDias( dias );
        }
        String lastName = movimientoProvidenciaUserLastName( movimientoProvidencia );
        if ( lastName != null ) {
            movimientoProvidenciaDTO.setUserLastName( lastName );
        }
        String firstName = movimientoProvidenciaUserFirstName( movimientoProvidencia );
        if ( firstName != null ) {
            movimientoProvidenciaDTO.setUserFirstName( firstName );
        }
        Long id1 = movimientoProvidenciaUserId( movimientoProvidencia );
        if ( id1 != null ) {
            movimientoProvidenciaDTO.setUserId( id1 );
        }
        Long id2 = movimientoProvidenciaProvidenciaId( movimientoProvidencia );
        if ( id2 != null ) {
            movimientoProvidenciaDTO.setProvidenciaId( id2 );
        }
        movimientoProvidenciaDTO.setId( movimientoProvidencia.getId() );
        movimientoProvidenciaDTO.setEstadoAnterior( movimientoProvidencia.getEstadoAnterior() );
        movimientoProvidenciaDTO.setEstadoNuevo( movimientoProvidencia.getEstadoNuevo() );
        movimientoProvidenciaDTO.setFecha( movimientoProvidencia.getFecha() );
        movimientoProvidenciaDTO.setAccion( movimientoProvidencia.getAccion() );
        movimientoProvidenciaDTO.setDocumentos( documentoSetToDocumentoDTOSet( movimientoProvidencia.getDocumentos() ) );
        movimientoProvidenciaDTO.setAdjuntos( adjuntoSetToAdjuntoDTOSet( movimientoProvidencia.getAdjuntos() ) );
        movimientoProvidenciaDTO.setComentario( movimientoProvidencia.getComentario() );

        return movimientoProvidenciaDTO;
    }

    @Override
    public MovimientoProvidencia toEntity(MovimientoProvidenciaDTO movimientoProvidenciaDTO) {
        if ( movimientoProvidenciaDTO == null ) {
            return null;
        }

        MovimientoProvidencia movimientoProvidencia = new MovimientoProvidencia();

        movimientoProvidencia.setPlazo( movimientoProvidenciaDTOToPlazo( movimientoProvidenciaDTO ) );
        movimientoProvidencia.setUser( movimientoProvidenciaDTOToUser( movimientoProvidenciaDTO ) );
        movimientoProvidencia.setProvidencia( providenciaMapper.fromId( movimientoProvidenciaDTO.getProvidenciaId() ) );
        movimientoProvidencia.setId( movimientoProvidenciaDTO.getId() );
        movimientoProvidencia.setEstadoAnterior( movimientoProvidenciaDTO.getEstadoAnterior() );
        movimientoProvidencia.setEstadoNuevo( movimientoProvidenciaDTO.getEstadoNuevo() );
        movimientoProvidencia.setFecha( movimientoProvidenciaDTO.getFecha() );
        movimientoProvidencia.setAccion( movimientoProvidenciaDTO.getAccion() );
        movimientoProvidencia.setDocumentos( documentoDTOSetToDocumentoSet( movimientoProvidenciaDTO.getDocumentos() ) );
        movimientoProvidencia.setAdjuntos( adjuntoDTOSetToAdjuntoSet( movimientoProvidenciaDTO.getAdjuntos() ) );
        movimientoProvidencia.setComentario( movimientoProvidenciaDTO.getComentario() );

        return movimientoProvidencia;
    }

    private Long movimientoProvidenciaPlazoId(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        Plazo plazo = movimientoProvidencia.getPlazo();
        if ( plazo == null ) {
            return null;
        }
        Long id = plazo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer movimientoProvidenciaPlazoDias(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        Plazo plazo = movimientoProvidencia.getPlazo();
        if ( plazo == null ) {
            return null;
        }
        Integer dias = plazo.getDias();
        if ( dias == null ) {
            return null;
        }
        return dias;
    }

    private String movimientoProvidenciaUserLastName(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        User user = movimientoProvidencia.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String movimientoProvidenciaUserFirstName(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        User user = movimientoProvidencia.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private Long movimientoProvidenciaUserId(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        User user = movimientoProvidencia.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long movimientoProvidenciaProvidenciaId(MovimientoProvidencia movimientoProvidencia) {
        if ( movimientoProvidencia == null ) {
            return null;
        }
        Providencia providencia = movimientoProvidencia.getProvidencia();
        if ( providencia == null ) {
            return null;
        }
        Long id = providencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<DocumentoDTO> documentoSetToDocumentoDTOSet(Set<Documento> set) {
        if ( set == null ) {
            return null;
        }

        Set<DocumentoDTO> set1 = new HashSet<DocumentoDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Documento documento : set ) {
            set1.add( documentoMapper.toDto( documento ) );
        }

        return set1;
    }

    protected Set<AdjuntoDTO> adjuntoSetToAdjuntoDTOSet(Set<Adjunto> set) {
        if ( set == null ) {
            return null;
        }

        Set<AdjuntoDTO> set1 = new HashSet<AdjuntoDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Adjunto adjunto : set ) {
            set1.add( adjuntoMapper.toDto( adjunto ) );
        }

        return set1;
    }

    protected Plazo movimientoProvidenciaDTOToPlazo(MovimientoProvidenciaDTO movimientoProvidenciaDTO) {
        if ( movimientoProvidenciaDTO == null ) {
            return null;
        }

        Plazo plazo = new Plazo();

        plazo.setDias( movimientoProvidenciaDTO.getPlazoDias() );
        plazo.setId( movimientoProvidenciaDTO.getPlazoId() );

        return plazo;
    }

    protected User movimientoProvidenciaDTOToUser(MovimientoProvidenciaDTO movimientoProvidenciaDTO) {
        if ( movimientoProvidenciaDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( movimientoProvidenciaDTO.getUserId() );

        return user;
    }

    protected Set<Documento> documentoDTOSetToDocumentoSet(Set<DocumentoDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Documento> set1 = new HashSet<Documento>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DocumentoDTO documentoDTO : set ) {
            set1.add( documentoMapper.toEntity( documentoDTO ) );
        }

        return set1;
    }

    protected Set<Adjunto> adjuntoDTOSetToAdjuntoSet(Set<AdjuntoDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Adjunto> set1 = new HashSet<Adjunto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AdjuntoDTO adjuntoDTO : set ) {
            set1.add( adjuntoMapper.toEntity( adjuntoDTO ) );
        }

        return set1;
    }
}
