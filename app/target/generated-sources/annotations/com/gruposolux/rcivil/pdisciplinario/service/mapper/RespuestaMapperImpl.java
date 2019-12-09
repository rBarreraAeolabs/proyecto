package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.RespuestaDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-09T09:11:27-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class RespuestaMapperImpl implements RespuestaMapper {

    @Autowired
    private AdjuntoMapper adjuntoMapper;
    @Autowired
    private DocumentoMapper documentoMapper;

    @Override
    public List<Respuesta> toEntity(List<RespuestaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Respuesta> list = new ArrayList<Respuesta>( dtoList.size() );
        for ( RespuestaDTO respuestaDTO : dtoList ) {
            list.add( toEntity( respuestaDTO ) );
        }

        return list;
    }

    @Override
    public List<RespuestaDTO> toDto(List<Respuesta> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RespuestaDTO> list = new ArrayList<RespuestaDTO>( entityList.size() );
        for ( Respuesta respuesta : entityList ) {
            list.add( toDto( respuesta ) );
        }

        return list;
    }

    @Override
    public Respuesta toEntity(RespuestaDTO respuestaDTO) {
        if ( respuestaDTO == null ) {
            return null;
        }

        Respuesta respuesta = new Respuesta();

        respuesta.setId( respuestaDTO.getId() );
        respuesta.setComentario( respuestaDTO.getComentario() );
        respuesta.setProvidencia( providenciaDTOToProvidencia( respuestaDTO.getProvidencia() ) );
        respuesta.setAdjuntos( adjuntoDTOSetToAdjuntoSet( respuestaDTO.getAdjuntos() ) );
        respuesta.setDocumentos( documentoDTOSetToDocumentoSet( respuestaDTO.getDocumentos() ) );
        respuesta.setGuardada( respuestaDTO.getGuardada() );
        respuesta.setEstadoProvidencia( respuestaDTO.getEstadoProvidencia() );

        return respuesta;
    }

    @Override
    public RespuestaDTO toDto(Respuesta respuesta) {
        if ( respuesta == null ) {
            return null;
        }

        RespuestaDTO respuestaDTO = new RespuestaDTO();

        String firstName = respuestaUserFirstName( respuesta );
        if ( firstName != null ) {
            respuestaDTO.setUserName( firstName );
        }
        String lastName = respuestaUserLastName( respuesta );
        if ( lastName != null ) {
            respuestaDTO.setUserLastname( lastName );
        }
        Long id = respuestaUserId( respuesta );
        if ( id != null ) {
            respuestaDTO.setUserId( id );
        }
        respuestaDTO.setId( respuesta.getId() );
        respuestaDTO.setComentario( respuesta.getComentario() );
        respuestaDTO.setProvidencia( providenciaToProvidenciaDTO( respuesta.getProvidencia() ) );
        respuestaDTO.setEstadoProvidencia( respuesta.getEstadoProvidencia() );
        respuestaDTO.setAdjuntos( adjuntoSetToAdjuntoDTOSet( respuesta.getAdjuntos() ) );
        respuestaDTO.setDocumentos( documentoSetToDocumentoDTOSet( respuesta.getDocumentos() ) );
        respuestaDTO.setGuardada( respuesta.getGuardada() );

        return respuestaDTO;
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

    protected Entidad entidadDTOToEntidad(EntidadDTO entidadDTO) {
        if ( entidadDTO == null ) {
            return null;
        }

        Entidad entidad = new Entidad();

        entidad.setId( entidadDTO.getId() );
        entidad.setNombre( entidadDTO.getNombre() );

        return entidad;
    }

    protected Providencia providenciaDTOToProvidencia(ProvidenciaDTO providenciaDTO) {
        if ( providenciaDTO == null ) {
            return null;
        }

        Providencia providencia = new Providencia();

        providencia.setId( providenciaDTO.getId() );
        providencia.setNumeroDgdp( providenciaDTO.getNumeroDgdp() );
        providencia.setFolio( providenciaDTO.getFolio() );
        providencia.setNumeroDgd( providenciaDTO.getNumeroDgd() );
        providencia.setNumeroReferencia( providenciaDTO.getnumeroReferencia() );
        providencia.setNumeroProvidencia( providenciaDTO.getNumeroProvidencia() );
        providencia.setEstadoActual( providenciaDTO.getEstadoActual() );
        providencia.setEtapa( providenciaDTO.getEtapa() );
        providencia.setSubEtapa( providenciaDTO.getSubEtapa() );
        providencia.setRequisito( providenciaDTO.getRequisito() );
        providencia.setTipo( providenciaDTO.getTipo() );
        providencia.setComentario( providenciaDTO.getComentario() );
        providencia.setFechaSolicitud( providenciaDTO.getFechaSolicitud() );
        providencia.setFechaCreacion( providenciaDTO.getFechaCreacion() );
        providencia.setDocumentos( documentoDTOSetToDocumentoSet( providenciaDTO.getDocumentos() ) );
        providencia.setAdjuntos( adjuntoDTOSetToAdjuntoSet( providenciaDTO.getAdjuntos() ) );
        Collection<InstruccionesProvidencia> collection = providenciaDTO.getinstrucciones();
        if ( collection != null ) {
            providencia.setInstrucciones( new ArrayList<InstruccionesProvidencia>( collection ) );
        }
        else {
            providencia.setInstrucciones( null );
        }
        providencia.setFechaHasta( providenciaDTO.getFechaHasta() );
        providencia.setRunSolicitante( providenciaDTO.getRunSolicitante() );
        providencia.setNombreSolicitante( providenciaDTO.getNombreSolicitante() );
        providencia.setEntidadSolicitante( entidadDTOToEntidad( providenciaDTO.getEntidadSolicitante() ) );
        providencia.setRunImplicado( providenciaDTO.getRunImplicado() );
        providencia.setNombreImplicado( providenciaDTO.getNombreImplicado() );
        providencia.setEntidadImplicada( entidadDTOToEntidad( providenciaDTO.getEntidadImplicada() ) );
        providencia.setNombreFiscalAsignado( providenciaDTO.getNombreFiscalAsignado() );
        providencia.setStandby( providenciaDTO.getStandby() );

        return providencia;
    }

    private String respuestaUserFirstName(Respuesta respuesta) {
        if ( respuesta == null ) {
            return null;
        }
        User user = respuesta.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String respuestaUserLastName(Respuesta respuesta) {
        if ( respuesta == null ) {
            return null;
        }
        User user = respuesta.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private Long respuestaUserId(Respuesta respuesta) {
        if ( respuesta == null ) {
            return null;
        }
        User user = respuesta.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected EntidadDTO entidadToEntidadDTO(Entidad entidad) {
        if ( entidad == null ) {
            return null;
        }

        EntidadDTO entidadDTO = new EntidadDTO();

        entidadDTO.setId( entidad.getId() );
        entidadDTO.setNombre( entidad.getNombre() );

        return entidadDTO;
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

    protected ProvidenciaDTO providenciaToProvidenciaDTO(Providencia providencia) {
        if ( providencia == null ) {
            return null;
        }

        ProvidenciaDTO providenciaDTO = new ProvidenciaDTO();

        providenciaDTO.setId( providencia.getId() );
        providenciaDTO.setnumeroReferencia( providencia.getNumeroReferencia() );
        providenciaDTO.setNumeroProvidencia( providencia.getNumeroProvidencia() );
        providenciaDTO.setNumeroDgdp( providencia.getNumeroDgdp() );
        providenciaDTO.setFolio( providencia.getFolio() );
        providenciaDTO.setNumeroDgd( providencia.getNumeroDgd() );
        providenciaDTO.setEstadoActual( providencia.getEstadoActual() );
        providenciaDTO.setEtapa( providencia.getEtapa() );
        providenciaDTO.setSubEtapa( providencia.getSubEtapa() );
        providenciaDTO.setRequisito( providencia.getRequisito() );
        providenciaDTO.setTipo( providencia.getTipo() );
        providenciaDTO.setComentario( providencia.getComentario() );
        providenciaDTO.setFechaSolicitud( providencia.getFechaSolicitud() );
        providenciaDTO.setFechaCreacion( providencia.getFechaCreacion() );
        Collection<InstruccionesProvidencia> collection = providencia.getInstrucciones();
        if ( collection != null ) {
            providenciaDTO.setinstrucciones( new ArrayList<InstruccionesProvidencia>( collection ) );
        }
        else {
            providenciaDTO.setinstrucciones( null );
        }
        providenciaDTO.setAdjuntos( adjuntoSetToAdjuntoDTOSet( providencia.getAdjuntos() ) );
        providenciaDTO.setFechaHasta( providencia.getFechaHasta() );
        providenciaDTO.setRunSolicitante( providencia.getRunSolicitante() );
        providenciaDTO.setNombreSolicitante( providencia.getNombreSolicitante() );
        providenciaDTO.setEntidadSolicitante( entidadToEntidadDTO( providencia.getEntidadSolicitante() ) );
        providenciaDTO.setRunImplicado( providencia.getRunImplicado() );
        providenciaDTO.setNombreImplicado( providencia.getNombreImplicado() );
        providenciaDTO.setEntidadImplicada( entidadToEntidadDTO( providencia.getEntidadImplicada() ) );
        providenciaDTO.setNombreFiscalAsignado( providencia.getNombreFiscalAsignado() );
        providenciaDTO.setStandby( providencia.getStandby() );
        if ( providenciaDTO.getDocumentos() != null ) {
            Set<DocumentoDTO> set1 = documentoSetToDocumentoDTOSet( providencia.getDocumentos() );
            if ( set1 != null ) {
                providenciaDTO.getDocumentos().addAll( set1 );
            }
        }

        return providenciaDTO;
    }
}
