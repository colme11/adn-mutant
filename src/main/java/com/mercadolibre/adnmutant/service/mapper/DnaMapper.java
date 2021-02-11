package com.mercadolibre.adnmutant.service.mapper;

import com.mercadolibre.adnmutant.model.entity.Dna;
import com.mercadolibre.adnmutant.service.dto.DnaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DnaMapper {

    @Mappings({
            @Mapping(source = "id", target = "idDna"),
            @Mapping(source = "dna", target = "dnaPerson")
    })
    DnaDTO toDnaDTO(Dna dna);

    @InheritInverseConfiguration
    Dna toDna(DnaDTO dnaDTO);

    List<DnaDTO> toDnaDTOs(List<Dna> dnaList);
}
