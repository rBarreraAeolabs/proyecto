package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlazoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-22T15:08:11-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class PlazoMapperImpl implements PlazoMapper {

    @Override
    public PlazoDTO toDto(Plazo entity) {
        if ( entity == null ) {
            return null;
        }

        PlazoDTO plazoDTO = new PlazoDTO();

        plazoDTO.setId( entity.getId() );
        plazoDTO.setNombre( entity.getNombre() );
        plazoDTO.setDias( entity.getDias() );

        return plazoDTO;
    }

    @Override
    public List<Plazo> toEntity(List<PlazoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Plazo> list = new ArrayList<Plazo>( dtoList.size() );
        for ( PlazoDTO plazoDTO : dtoList ) {
            list.add( toEntity( plazoDTO ) );
        }

        return list;
    }

    @Override
    public List<PlazoDTO> toDto(List<Plazo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PlazoDTO> list = new ArrayList<PlazoDTO>( entityList.size() );
        for ( Plazo plazo : entityList ) {
            list.add( toDto( plazo ) );
        }

        return list;
    }

    @Override
    public Plazo toEntity(PlazoDTO plazoDTO) {
        if ( plazoDTO == null ) {
            return null;
        }

        Plazo plazo = new Plazo();

        plazo.setId( plazoDTO.getId() );
        plazo.setNombre( plazoDTO.getNombre() );
        plazo.setDias( plazoDTO.getDias() );

        return plazo;
    }
}
