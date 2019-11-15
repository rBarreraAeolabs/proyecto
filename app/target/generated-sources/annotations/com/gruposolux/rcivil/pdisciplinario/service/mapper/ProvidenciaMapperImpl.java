package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.EntidadDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-14T20:35:13-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ProvidenciaMapperImpl implements ProvidenciaMapper {

    @Autowired
    private SumarioAdministrativoMapper sumarioAdministrativoMapper;
    @Autowired
    private InvestigacionSumariaMapper investigacionSumariaMapper;
    @Autowired
    private AdjuntoMapper adjuntoMapper;
    @Autowired
    private DocumentoMapper documentoMapper;

    @Override
    public List<Providencia> toEntity(List<ProvidenciaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Providencia> list = new ArrayList<Providencia>( dtoList.size() );
        for ( ProvidenciaDTO providenciaDTO : dtoList ) {
            list.add( toEntity( providenciaDTO ) );
        }

        return list;
    }

    @Override
    public List<ProvidenciaDTO> toDto(List<Providencia> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProvidenciaDTO> list = new ArrayList<ProvidenciaDTO>( entityList.size() );
        for ( Providencia providencia : entityList ) {
            list.add( toDto( providencia ) );
        }

        return list;
    }

    @Override
    public ProvidenciaDTO toDto(Providencia providencia) {
        if ( providencia == null ) {
            return null;
        }

        ProvidenciaDTO providenciaDTO = new ProvidenciaDTO();

        Long id = providenciaInvestigacionSumariaId( providencia );
        if ( id != null ) {
            providenciaDTO.setInvestigacionSumariaId( id );
        }
        Long id1 = providenciaSumarioAdministrativoId( providencia );
        if ( id1 != null ) {
            providenciaDTO.setSumarioAdministrativoId( id1 );
        }
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

    @Override
    public Providencia toEntity(ProvidenciaDTO providenciaDTO) {
        if ( providenciaDTO == null ) {
            return null;
        }

        Providencia providencia = new Providencia();

        providencia.setSumarioAdministrativo( sumarioAdministrativoMapper.fromId( providenciaDTO.getSumarioAdministrativoId() ) );
        providencia.setInvestigacionSumaria( investigacionSumariaMapper.fromId( providenciaDTO.getInvestigacionSumariaId() ) );
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

    private Long providenciaInvestigacionSumariaId(Providencia providencia) {
        if ( providencia == null ) {
            return null;
        }
        InvestigacionSumaria investigacionSumaria = providencia.getInvestigacionSumaria();
        if ( investigacionSumaria == null ) {
            return null;
        }
        Long id = investigacionSumaria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long providenciaSumarioAdministrativoId(Providencia providencia) {
        if ( providencia == null ) {
            return null;
        }
        SumarioAdministrativo sumarioAdministrativo = providencia.getSumarioAdministrativo();
        if ( sumarioAdministrativo == null ) {
            return null;
        }
        Long id = sumarioAdministrativo.getId();
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
}
