package com.mercadolibre.adnmutant.service;

import com.mercadolibre.adnmutant.model.repository.IDnaRepository;
import com.mercadolibre.adnmutant.service.dto.DnaDTO;
import com.mercadolibre.adnmutant.service.mapper.DnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oscar Villarreal
 */
@Service
public class DnaService {

    @Autowired
    private IDnaRepository dnaRepository;

    @Autowired
    private DnaMapper mapper;

    /**
     * consult all DNAs
     * @return
     */
    public List<DnaDTO> getAll(){
        return mapper.toDnaDTOs(dnaRepository.getAll());
    }

    /**
     * consult a DNA
     * @param dna
     * @return
     */
    public List<DnaDTO> findByDna(String dna){
        return mapper.toDnaDTOs(dnaRepository.findByDna(dna));
    }

    /**
     * Create a new dna record
     * @param dnaDTO
     * @return
     */
    public DnaDTO save(DnaDTO dnaDTO){
        return mapper.toDnaDTO(dnaRepository.save(mapper.toDna(dnaDTO)));
    }

    /**
     * query all DNA from mutants or humans
     * @param isMutant
     * @return
     */
    public List<DnaDTO> findByIsMutant(boolean isMutant){
        return mapper.toDnaDTOs(dnaRepository.findByIsMutant(isMutant));
    }
}
