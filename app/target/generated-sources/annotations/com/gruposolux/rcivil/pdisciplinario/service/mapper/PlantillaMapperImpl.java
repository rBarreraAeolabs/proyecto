package com.gruposolux.rcivil.pdisciplinario.service.mapper;

import com.gruposolux.rcivil.pdisciplinario.domain.Plantilla;
import com.gruposolux.rcivil.pdisciplinario.service.dto.PlantillaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-23T09:40:19-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class PlantillaMapperImpl implements PlantillaMapper {

    @Override
    public Plantilla toEntity(PlantillaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Plantilla plantilla = new Plantilla();

        plantilla.setId( dto.getId() );
        plantilla.setNombre( dto.getNombre() );
        plantilla.setContenido( dto.getContenido() );
        plantilla.setTipo( dto.getTipo() );
        plantilla.setEstado( dto.getEstado() );

        return plantilla;
    }

    @Override
    public PlantillaDTO toDto(Plantilla entity) {
        if ( entity == null ) {
            return null;
        }

        PlantillaDTO plantillaDTO = new PlantillaDTO();

        plantillaDTO.setId( entity.getId() );
        plantillaDTO.setNombre( entity.getNombre() );
        plantillaDTO.setContenido( entity.getContenido() );
        plantillaDTO.setTipo( entity.getTipo() );
        plantillaDTO.setEstado( entity.getEstado() );

        return plantillaDTO;
    }

    @Override
    public List<Plantilla> toEntity(List<PlantillaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Plantilla> list = new ArrayList<Plantilla>( dtoList.size() );
        for ( PlantillaDTO plantillaDTO : dtoList ) {
            list.add( toEntity( plantillaDTO ) );
        }

        return list;
    }

    @Override
    public List<PlantillaDTO> toDto(List<Plantilla> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PlantillaDTO> list = new ArrayList<PlantillaDTO>( entityList.size() );
        for ( Plantilla plantilla : entityList ) {
            list.add( toDto( plantilla ) );
        }

        return list;
    }
}
