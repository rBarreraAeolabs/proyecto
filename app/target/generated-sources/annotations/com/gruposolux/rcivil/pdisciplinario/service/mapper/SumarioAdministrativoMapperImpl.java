package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.SumarioAdministrativoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T08:22:53-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class SumarioAdministrativoMapperImpl implements SumarioAdministrativoMapper {

    @Autowired
    private InvestigacionSumariaMapper investigacionSumariaMapper;

    @Override
    public List<SumarioAdministrativo> toEntity(List<SumarioAdministrativoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SumarioAdministrativo> list = new ArrayList<SumarioAdministrativo>( dtoList.size() );
        for ( SumarioAdministrativoDTO sumarioAdministrativoDTO : dtoList ) {
            list.add( toEntity( sumarioAdministrativoDTO ) );
        }

        return list;
    }

    @Override
    public List<SumarioAdministrativoDTO> toDto(List<SumarioAdministrativo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SumarioAdministrativoDTO> list = new ArrayList<SumarioAdministrativoDTO>( entityList.size() );
        for ( SumarioAdministrativo sumarioAdministrativo : entityList ) {
            list.add( toDto( sumarioAdministrativo ) );
        }

        return list;
    }

    @Override
    public SumarioAdministrativoDTO toDto(SumarioAdministrativo sumarioAdministrativo) {
        if ( sumarioAdministrativo == null ) {
            return null;
        }

        SumarioAdministrativoDTO sumarioAdministrativoDTO = new SumarioAdministrativoDTO();

        Long id = sumarioAdministrativoInvestigacionSumariaId( sumarioAdministrativo );
        if ( id != null ) {
            sumarioAdministrativoDTO.setInvestigacionSumariaId( id );
        }
        sumarioAdministrativoDTO.setId( sumarioAdministrativo.getId() );
        sumarioAdministrativoDTO.setCampoUno( sumarioAdministrativo.getCampoUno() );

        return sumarioAdministrativoDTO;
    }

    @Override
    public SumarioAdministrativo toEntity(SumarioAdministrativoDTO sumarioAdministrativoDTO) {
        if ( sumarioAdministrativoDTO == null ) {
            return null;
        }

        SumarioAdministrativo sumarioAdministrativo = new SumarioAdministrativo();

        sumarioAdministrativo.setInvestigacionSumaria( investigacionSumariaMapper.fromId( sumarioAdministrativoDTO.getInvestigacionSumariaId() ) );
        sumarioAdministrativo.setId( sumarioAdministrativoDTO.getId() );
        sumarioAdministrativo.setCampoUno( sumarioAdministrativoDTO.getCampoUno() );

        return sumarioAdministrativo;
    }

    private Long sumarioAdministrativoInvestigacionSumariaId(SumarioAdministrativo sumarioAdministrativo) {
        if ( sumarioAdministrativo == null ) {
            return null;
        }
        InvestigacionSumaria investigacionSumaria = sumarioAdministrativo.getInvestigacionSumaria();
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
