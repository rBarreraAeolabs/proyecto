package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-25T15:30:52-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class AdjuntoMapperImpl implements AdjuntoMapper {

    @Autowired
    private ProvidenciaMapper providenciaMapper;
    @Autowired
    private MovimientoProvidenciaMapper movimientoProvidenciaMapper;
    @Autowired
    private RespuestaMapper respuestaMapper;

    @Override
    public AdjuntoDTO toDto(Adjunto adjunto) {
        if ( adjunto == null ) {
            return null;
        }

        AdjuntoDTO adjuntoDTO = new AdjuntoDTO();

        Long id = adjuntoMovimientoProvidenciaId( adjunto );
        if ( id != null ) {
            adjuntoDTO.setMovimientoProvidenciaId( id );
        }
        Long id1 = adjuntoRespuestaId( adjunto );
        if ( id1 != null ) {
            adjuntoDTO.setRespuestaId( id1 );
        }
        Long id2 = adjuntoProvidenciaId( adjunto );
        if ( id2 != null ) {
            adjuntoDTO.setProvidenciaId( id2 );
        }
        adjuntoDTO.setId( adjunto.getId() );
        adjuntoDTO.setTipoAdjunto( adjunto.getTipoAdjunto() );
        adjuntoDTO.setNombre( adjunto.getNombre() );
        adjuntoDTO.setDescripcion( adjunto.getDescripcion() );
        adjuntoDTO.setFechaCreado( adjunto.getFechaCreado() );
        adjuntoDTO.setFechaSubido( adjunto.getFechaSubido() );
        adjuntoDTO.setArchivoNombre( adjunto.getArchivoNombre() );
        adjuntoDTO.setArchivoMimeType( adjunto.getArchivoMimeType() );
        adjuntoDTO.setArchivoSize( adjunto.getArchivoSize() );
        adjuntoDTO.setEstado( adjunto.getEstado() );
        byte[] archivo = adjunto.getArchivo();
        if ( archivo != null ) {
            adjuntoDTO.setArchivo( Arrays.copyOf( archivo, archivo.length ) );
        }
        adjuntoDTO.setArchivoContentType( adjunto.getArchivoContentType() );
        adjuntoDTO.setAlfrescoNodeId( adjunto.getAlfrescoNodeId() );
        adjuntoDTO.setAlfrescoNodePath( adjunto.getAlfrescoNodePath() );
        adjuntoDTO.setLocalPath( adjunto.getLocalPath() );
        adjuntoDTO.setHash( adjunto.getHash() );

        return adjuntoDTO;
    }

    @Override
    public Adjunto toEntity(AdjuntoDTO adjuntoDTO) {
        if ( adjuntoDTO == null ) {
            return null;
        }

        Adjunto adjunto = new Adjunto();

        adjunto.setProvidencia( providenciaMapper.fromId( adjuntoDTO.getProvidenciaId() ) );
        adjunto.setRespuesta( respuestaMapper.fromId( adjuntoDTO.getRespuestaId() ) );
        adjunto.setMovimientoProvidencia( movimientoProvidenciaMapper.fromId( adjuntoDTO.getMovimientoProvidenciaId() ) );
        adjunto.setId( adjuntoDTO.getId() );
        adjunto.setTipoAdjunto( adjuntoDTO.getTipoAdjunto() );
        adjunto.setNombre( adjuntoDTO.getNombre() );
        adjunto.setDescripcion( adjuntoDTO.getDescripcion() );
        adjunto.setFechaCreado( adjuntoDTO.getFechaCreado() );
        adjunto.setFechaSubido( adjuntoDTO.getFechaSubido() );
        adjunto.setArchivoNombre( adjuntoDTO.getArchivoNombre() );
        adjunto.setArchivoMimeType( adjuntoDTO.getArchivoMimeType() );
        adjunto.setArchivoSize( adjuntoDTO.getArchivoSize() );
        adjunto.setEstado( adjuntoDTO.getEstado() );
        byte[] archivo = adjuntoDTO.getArchivo();
        if ( archivo != null ) {
            adjunto.setArchivo( Arrays.copyOf( archivo, archivo.length ) );
        }
        adjunto.setArchivoContentType( adjuntoDTO.getArchivoContentType() );
        adjunto.setAlfrescoNodeId( adjuntoDTO.getAlfrescoNodeId() );
        adjunto.setAlfrescoNodePath( adjuntoDTO.getAlfrescoNodePath() );
        adjunto.setLocalPath( adjuntoDTO.getLocalPath() );
        adjunto.setHash( adjuntoDTO.getHash() );

        return adjunto;
    }

    @Override
    public List<AdjuntoDTO> toDto(List<Adjunto> adjunto) {
        if ( adjunto == null ) {
            return null;
        }

        List<AdjuntoDTO> list = new ArrayList<AdjuntoDTO>( adjunto.size() );
        for ( Adjunto adjunto1 : adjunto ) {
            list.add( toDto( adjunto1 ) );
        }

        return list;
    }

    @Override
    public List<Adjunto> toEntity(List<AdjuntoDTO> adjuntoDTO) {
        if ( adjuntoDTO == null ) {
            return null;
        }

        List<Adjunto> list = new ArrayList<Adjunto>( adjuntoDTO.size() );
        for ( AdjuntoDTO adjuntoDTO1 : adjuntoDTO ) {
            list.add( toEntity( adjuntoDTO1 ) );
        }

        return list;
    }

    private Long adjuntoMovimientoProvidenciaId(Adjunto adjunto) {
        if ( adjunto == null ) {
            return null;
        }
        MovimientoProvidencia movimientoProvidencia = adjunto.getMovimientoProvidencia();
        if ( movimientoProvidencia == null ) {
            return null;
        }
        Long id = movimientoProvidencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long adjuntoRespuestaId(Adjunto adjunto) {
        if ( adjunto == null ) {
            return null;
        }
        Respuesta respuesta = adjunto.getRespuesta();
        if ( respuesta == null ) {
            return null;
        }
        Long id = respuesta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long adjuntoProvidenciaId(Adjunto adjunto) {
        if ( adjunto == null ) {
            return null;
        }
        Providencia providencia = adjunto.getProvidencia();
        if ( providencia == null ) {
            return null;
        }
        Long id = providencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
