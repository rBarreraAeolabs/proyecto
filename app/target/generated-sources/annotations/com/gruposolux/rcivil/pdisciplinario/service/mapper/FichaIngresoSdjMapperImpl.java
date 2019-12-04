package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.FichaIngresoSdj;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FichaIngresoSdjDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T15:58:09-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class FichaIngresoSdjMapperImpl implements FichaIngresoSdjMapper {

    @Override
    public FichaIngresoSdjDTO toDto(FichaIngresoSdj entity) {
        if ( entity == null ) {
            return null;
        }

        FichaIngresoSdjDTO fichaIngresoSdjDTO = new FichaIngresoSdjDTO();

        fichaIngresoSdjDTO.setId( entity.getId() );
        fichaIngresoSdjDTO.setFechaInicio( entity.getFechaInicio() );
        fichaIngresoSdjDTO.setObservacion( entity.getObservacion() );
        fichaIngresoSdjDTO.setPlazo( entity.getPlazo() );
        fichaIngresoSdjDTO.setFechaHasta( entity.getFechaHasta() );
        fichaIngresoSdjDTO.setNumeroReferencia( entity.getNumeroReferencia() );
        fichaIngresoSdjDTO.setNumeroProvidencia( entity.getNumeroProvidencia() );
        fichaIngresoSdjDTO.setTipoSolicitud( entity.getTipoSolicitud() );
        Collection<InstruccionesProvidencia> collection = entity.getInstrucciones();
        if ( collection != null ) {
            fichaIngresoSdjDTO.setInstrucciones( new ArrayList<InstruccionesProvidencia>( collection ) );
        }
        else {
            fichaIngresoSdjDTO.setInstrucciones( null );
        }
        fichaIngresoSdjDTO.setAtentamente( entity.getAtentamente() );

        return fichaIngresoSdjDTO;
    }

    @Override
    public List<FichaIngresoSdj> toEntity(List<FichaIngresoSdjDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<FichaIngresoSdj> list = new ArrayList<FichaIngresoSdj>( dtoList.size() );
        for ( FichaIngresoSdjDTO fichaIngresoSdjDTO : dtoList ) {
            list.add( toEntity( fichaIngresoSdjDTO ) );
        }

        return list;
    }

    @Override
    public List<FichaIngresoSdjDTO> toDto(List<FichaIngresoSdj> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FichaIngresoSdjDTO> list = new ArrayList<FichaIngresoSdjDTO>( entityList.size() );
        for ( FichaIngresoSdj fichaIngresoSdj : entityList ) {
            list.add( toDto( fichaIngresoSdj ) );
        }

        return list;
    }

    @Override
    public FichaIngresoSdj toEntity(FichaIngresoSdjDTO fichaIngresoSdjDTO) {
        if ( fichaIngresoSdjDTO == null ) {
            return null;
        }

        FichaIngresoSdj fichaIngresoSdj = new FichaIngresoSdj();

        fichaIngresoSdj.setId( fichaIngresoSdjDTO.getId() );
        fichaIngresoSdj.setFechaInicio( fichaIngresoSdjDTO.getFechaInicio() );
        fichaIngresoSdj.setObservacion( fichaIngresoSdjDTO.getObservacion() );
        fichaIngresoSdj.setPlazo( fichaIngresoSdjDTO.getPlazo() );
        fichaIngresoSdj.setFechaHasta( fichaIngresoSdjDTO.getFechaHasta() );
        fichaIngresoSdj.setNumeroProvidencia( fichaIngresoSdjDTO.getNumeroProvidencia() );
        fichaIngresoSdj.setNumeroReferencia( fichaIngresoSdjDTO.getNumeroReferencia() );
        fichaIngresoSdj.setTipoSolicitud( fichaIngresoSdjDTO.getTipoSolicitud() );
        Collection<InstruccionesProvidencia> collection = fichaIngresoSdjDTO.getInstrucciones();
        if ( collection != null ) {
            fichaIngresoSdj.setInstrucciones( new ArrayList<InstruccionesProvidencia>( collection ) );
        }
        else {
            fichaIngresoSdj.setInstrucciones( null );
        }
        fichaIngresoSdj.setAtentamente( fichaIngresoSdjDTO.getAtentamente() );

        return fichaIngresoSdj;
    }
}
