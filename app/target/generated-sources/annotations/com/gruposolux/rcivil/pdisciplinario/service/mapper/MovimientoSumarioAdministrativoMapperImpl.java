package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoSumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.MovimientoSumarioAdministrativoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-05T14:58:23-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class MovimientoSumarioAdministrativoMapperImpl implements MovimientoSumarioAdministrativoMapper {

    @Autowired
    private SumarioAdministrativoMapper sumarioAdministrativoMapper;

    @Override
    public List<MovimientoSumarioAdministrativo> toEntity(List<MovimientoSumarioAdministrativoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MovimientoSumarioAdministrativo> list = new ArrayList<MovimientoSumarioAdministrativo>( dtoList.size() );
        for ( MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO : dtoList ) {
            list.add( toEntity( movimientoSumarioAdministrativoDTO ) );
        }

        return list;
    }

    @Override
    public List<MovimientoSumarioAdministrativoDTO> toDto(List<MovimientoSumarioAdministrativo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MovimientoSumarioAdministrativoDTO> list = new ArrayList<MovimientoSumarioAdministrativoDTO>( entityList.size() );
        for ( MovimientoSumarioAdministrativo movimientoSumarioAdministrativo : entityList ) {
            list.add( toDto( movimientoSumarioAdministrativo ) );
        }

        return list;
    }

    @Override
    public MovimientoSumarioAdministrativoDTO toDto(MovimientoSumarioAdministrativo movimientoSumarioAdministrativo) {
        if ( movimientoSumarioAdministrativo == null ) {
            return null;
        }

        MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO = new MovimientoSumarioAdministrativoDTO();

        Long id = movimientoSumarioAdministrativoSumarioAdministrativoId( movimientoSumarioAdministrativo );
        if ( id != null ) {
            movimientoSumarioAdministrativoDTO.setSumarioAdministrativoId( id );
        }
        movimientoSumarioAdministrativoDTO.setId( movimientoSumarioAdministrativo.getId() );
        movimientoSumarioAdministrativoDTO.setEstadoAnterior( movimientoSumarioAdministrativo.getEstadoAnterior() );
        movimientoSumarioAdministrativoDTO.setEstadoNuevo( movimientoSumarioAdministrativo.getEstadoNuevo() );
        movimientoSumarioAdministrativoDTO.setFecha( movimientoSumarioAdministrativo.getFecha() );

        return movimientoSumarioAdministrativoDTO;
    }

    @Override
    public MovimientoSumarioAdministrativo toEntity(MovimientoSumarioAdministrativoDTO movimientoSumarioAdministrativoDTO) {
        if ( movimientoSumarioAdministrativoDTO == null ) {
            return null;
        }

        MovimientoSumarioAdministrativo movimientoSumarioAdministrativo = new MovimientoSumarioAdministrativo();

        movimientoSumarioAdministrativo.setSumarioAdministrativo( sumarioAdministrativoMapper.fromId( movimientoSumarioAdministrativoDTO.getSumarioAdministrativoId() ) );
        movimientoSumarioAdministrativo.setId( movimientoSumarioAdministrativoDTO.getId() );
        movimientoSumarioAdministrativo.setEstadoAnterior( movimientoSumarioAdministrativoDTO.getEstadoAnterior() );
        movimientoSumarioAdministrativo.setEstadoNuevo( movimientoSumarioAdministrativoDTO.getEstadoNuevo() );
        movimientoSumarioAdministrativo.setFecha( movimientoSumarioAdministrativoDTO.getFecha() );

        return movimientoSumarioAdministrativo;
    }

    private Long movimientoSumarioAdministrativoSumarioAdministrativoId(MovimientoSumarioAdministrativo movimientoSumarioAdministrativo) {
        if ( movimientoSumarioAdministrativo == null ) {
            return null;
        }
        SumarioAdministrativo sumarioAdministrativo = movimientoSumarioAdministrativo.getSumarioAdministrativo();
        if ( sumarioAdministrativo == null ) {
            return null;
        }
        Long id = sumarioAdministrativo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
