package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Derivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-09T09:11:26-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class DocumentoMapperImpl implements DocumentoMapper {

    @Autowired
    private ProvidenciaMapper providenciaMapper;
    @Autowired
    private DerivacionMapper derivacionMapper;
    @Autowired
    private MovimientoProvidenciaMapper movimientoProvidenciaMapper;
    @Autowired
    private RespuestaMapper respuestaMapper;

    @Override
    public DocumentoDTO toDto(Documento documento) {
        if ( documento == null ) {
            return null;
        }

        DocumentoDTO documentoDTO = new DocumentoDTO();

        Long id = documentoMovimientoProvidenciaId( documento );
        if ( id != null ) {
            documentoDTO.setMovimientoProvidenciaId( id );
        }
        Long id1 = documentoRespuestaId( documento );
        if ( id1 != null ) {
            documentoDTO.setRespuestaId( id1 );
        }
        Long id2 = documentoDerivacionId( documento );
        if ( id2 != null ) {
            documentoDTO.setDerivacionId( id2 );
        }
        Long id3 = documentoProvidenciaId( documento );
        if ( id3 != null ) {
            documentoDTO.setProvidenciaId( id3 );
        }
        documentoDTO.setId( documento.getId() );
        documentoDTO.setDescripcion( documento.getDescripcion() );
        documentoDTO.setContenido( documento.getContenido() );
        documentoDTO.setFechaCreado( documento.getFechaCreado() );
        documentoDTO.setArchivoNombre( documento.getArchivoNombre() );
        documentoDTO.setArchivoMimeType( documento.getArchivoMimeType() );
        documentoDTO.setArchivoSize( documento.getArchivoSize() );
        byte[] archivo = documento.getArchivo();
        if ( archivo != null ) {
            documentoDTO.setArchivo( Arrays.copyOf( archivo, archivo.length ) );
        }
        documentoDTO.setArchivoContentType( documento.getArchivoContentType() );
        documentoDTO.setAlfrescoNodeId( documento.getAlfrescoNodeId() );
        documentoDTO.setAlfrescoNodePath( documento.getAlfrescoNodePath() );
        documentoDTO.setLocalPath( documento.getLocalPath() );
        documentoDTO.setHash( documento.getHash() );
        documentoDTO.setNumeroResolucion( documento.getNumeroResolucion() );
        documentoDTO.setTipoPlantilla( documento.getTipoPlantilla() );
        documentoDTO.setVersion( documento.getVersion() );

        return documentoDTO;
    }

    @Override
    public Documento toEntity(DocumentoDTO documentoDTO) {
        if ( documentoDTO == null ) {
            return null;
        }

        Documento documento = new Documento();

        documento.setProvidencia( providenciaMapper.fromId( documentoDTO.getProvidenciaId() ) );
        documento.setRespuesta( respuestaMapper.fromId( documentoDTO.getRespuestaId() ) );
        documento.setDerivacion( derivacionMapper.fromId( documentoDTO.getDerivacionId() ) );
        documento.setMovimientoProvidencia( movimientoProvidenciaMapper.fromId( documentoDTO.getMovimientoProvidenciaId() ) );
        documento.setNumeroResolucion( documentoDTO.getNumeroResolucion() );
        documento.setId( documentoDTO.getId() );
        documento.setDescripcion( documentoDTO.getDescripcion() );
        documento.setContenido( documentoDTO.getContenido() );
        documento.setFechaCreado( documentoDTO.getFechaCreado() );
        documento.setArchivoNombre( documentoDTO.getArchivoNombre() );
        documento.setArchivoMimeType( documentoDTO.getArchivoMimeType() );
        documento.setArchivoSize( documentoDTO.getArchivoSize() );
        byte[] archivo = documentoDTO.getArchivo();
        if ( archivo != null ) {
            documento.setArchivo( Arrays.copyOf( archivo, archivo.length ) );
        }
        documento.setArchivoContentType( documentoDTO.getArchivoContentType() );
        documento.setAlfrescoNodeId( documentoDTO.getAlfrescoNodeId() );
        documento.setAlfrescoNodePath( documentoDTO.getAlfrescoNodePath() );
        documento.setLocalPath( documentoDTO.getLocalPath() );
        documento.setHash( documentoDTO.getHash() );
        documento.setTipoPlantilla( documentoDTO.getTipoPlantilla() );
        documento.setVersion( documentoDTO.getVersion() );

        return documento;
    }

    @Override
    public List<DocumentoDTO> toDto(List<Documento> documento) {
        if ( documento == null ) {
            return null;
        }

        List<DocumentoDTO> list = new ArrayList<DocumentoDTO>( documento.size() );
        for ( Documento documento1 : documento ) {
            list.add( toDto( documento1 ) );
        }

        return list;
    }

    @Override
    public List<Documento> toEntity(List<DocumentoDTO> documentoDTO) {
        if ( documentoDTO == null ) {
            return null;
        }

        List<Documento> list = new ArrayList<Documento>( documentoDTO.size() );
        for ( DocumentoDTO documentoDTO1 : documentoDTO ) {
            list.add( toEntity( documentoDTO1 ) );
        }

        return list;
    }

    private Long documentoMovimientoProvidenciaId(Documento documento) {
        if ( documento == null ) {
            return null;
        }
        MovimientoProvidencia movimientoProvidencia = documento.getMovimientoProvidencia();
        if ( movimientoProvidencia == null ) {
            return null;
        }
        Long id = movimientoProvidencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long documentoRespuestaId(Documento documento) {
        if ( documento == null ) {
            return null;
        }
        Respuesta respuesta = documento.getRespuesta();
        if ( respuesta == null ) {
            return null;
        }
        Long id = respuesta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long documentoDerivacionId(Documento documento) {
        if ( documento == null ) {
            return null;
        }
        Derivacion derivacion = documento.getDerivacion();
        if ( derivacion == null ) {
            return null;
        }
        Long id = derivacion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long documentoProvidenciaId(Documento documento) {
        if ( documento == null ) {
            return null;
        }
        Providencia providencia = documento.getProvidencia();
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
