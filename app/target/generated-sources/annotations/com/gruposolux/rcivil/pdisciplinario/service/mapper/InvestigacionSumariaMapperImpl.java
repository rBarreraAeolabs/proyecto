package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.service.dto.InvestigacionSumariaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-19T15:40:48-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class InvestigacionSumariaMapperImpl implements InvestigacionSumariaMapper {

    @Override
    public InvestigacionSumariaDTO toDto(InvestigacionSumaria entity) {
        if ( entity == null ) {
            return null;
        }

        InvestigacionSumariaDTO investigacionSumariaDTO = new InvestigacionSumariaDTO();

        investigacionSumariaDTO.setId( entity.getId() );
        investigacionSumariaDTO.setCompoTres( entity.getCompoTres() );

        return investigacionSumariaDTO;
    }

    @Override
    public List<InvestigacionSumaria> toEntity(List<InvestigacionSumariaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<InvestigacionSumaria> list = new ArrayList<InvestigacionSumaria>( dtoList.size() );
        for ( InvestigacionSumariaDTO investigacionSumariaDTO : dtoList ) {
            list.add( toEntity( investigacionSumariaDTO ) );
        }

        return list;
    }

    @Override
    public List<InvestigacionSumariaDTO> toDto(List<InvestigacionSumaria> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<InvestigacionSumariaDTO> list = new ArrayList<InvestigacionSumariaDTO>( entityList.size() );
        for ( InvestigacionSumaria investigacionSumaria : entityList ) {
            list.add( toDto( investigacionSumaria ) );
        }

        return list;
    }

    @Override
    public InvestigacionSumaria toEntity(InvestigacionSumariaDTO investigacionSumariaDTO) {
        if ( investigacionSumariaDTO == null ) {
            return null;
        }

        InvestigacionSumaria investigacionSumaria = new InvestigacionSumaria();

        investigacionSumaria.setId( investigacionSumariaDTO.getId() );
        investigacionSumaria.setCompoTres( investigacionSumariaDTO.getCompoTres() );

        return investigacionSumaria;
    }
}
