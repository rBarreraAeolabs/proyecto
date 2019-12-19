package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoInvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoInvestigacionSumariaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-19T14:58:45-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class MovimientoInvestigacionSumariaMapperImpl implements MovimientoInvestigacionSumariaMapper {

    @Autowired
    private InvestigacionSumariaMapper investigacionSumariaMapper;

    @Override
    public List<MovimientoInvestigacionSumaria> toEntity(List<MovimientoInvestigacionSumariaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MovimientoInvestigacionSumaria> list = new ArrayList<MovimientoInvestigacionSumaria>( dtoList.size() );
        for ( MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO : dtoList ) {
            list.add( toEntity( movimientoInvestigacionSumariaDTO ) );
        }

        return list;
    }

    @Override
    public List<MovimientoInvestigacionSumariaDTO> toDto(List<MovimientoInvestigacionSumaria> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MovimientoInvestigacionSumariaDTO> list = new ArrayList<MovimientoInvestigacionSumariaDTO>( entityList.size() );
        for ( MovimientoInvestigacionSumaria movimientoInvestigacionSumaria : entityList ) {
            list.add( toDto( movimientoInvestigacionSumaria ) );
        }

        return list;
    }

    @Override
    public MovimientoInvestigacionSumariaDTO toDto(MovimientoInvestigacionSumaria movimientoInvestigacionSumaria) {
        if ( movimientoInvestigacionSumaria == null ) {
            return null;
        }

        MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO = new MovimientoInvestigacionSumariaDTO();

        Long id = movimientoInvestigacionSumariaInvestigacionSumariaId( movimientoInvestigacionSumaria );
        if ( id != null ) {
            movimientoInvestigacionSumariaDTO.setInvestigacionSumariaId( id );
        }
        movimientoInvestigacionSumariaDTO.setId( movimientoInvestigacionSumaria.getId() );
        movimientoInvestigacionSumariaDTO.setEstadoAnterior( movimientoInvestigacionSumaria.getEstadoAnterior() );
        movimientoInvestigacionSumariaDTO.setEstadoNuevo( movimientoInvestigacionSumaria.getEstadoNuevo() );
        movimientoInvestigacionSumariaDTO.setFecha( movimientoInvestigacionSumaria.getFecha() );

        return movimientoInvestigacionSumariaDTO;
    }

    @Override
    public MovimientoInvestigacionSumaria toEntity(MovimientoInvestigacionSumariaDTO movimientoInvestigacionSumariaDTO) {
        if ( movimientoInvestigacionSumariaDTO == null ) {
            return null;
        }

        MovimientoInvestigacionSumaria movimientoInvestigacionSumaria = new MovimientoInvestigacionSumaria();

        movimientoInvestigacionSumaria.setInvestigacionSumaria( investigacionSumariaMapper.fromId( movimientoInvestigacionSumariaDTO.getInvestigacionSumariaId() ) );
        movimientoInvestigacionSumaria.setId( movimientoInvestigacionSumariaDTO.getId() );
        movimientoInvestigacionSumaria.setEstadoAnterior( movimientoInvestigacionSumariaDTO.getEstadoAnterior() );
        movimientoInvestigacionSumaria.setEstadoNuevo( movimientoInvestigacionSumariaDTO.getEstadoNuevo() );
        movimientoInvestigacionSumaria.setFecha( movimientoInvestigacionSumariaDTO.getFecha() );

        return movimientoInvestigacionSumaria;
    }

    private Long movimientoInvestigacionSumariaInvestigacionSumariaId(MovimientoInvestigacionSumaria movimientoInvestigacionSumaria) {
        if ( movimientoInvestigacionSumaria == null ) {
            return null;
        }
        InvestigacionSumaria investigacionSumaria = movimientoInvestigacionSumaria.getInvestigacionSumaria();
        if ( investigacionSumaria == null ) {
            return null;
        }
        Long id = investigacionSumaria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
