package com.mercadolibre.adnmutant.service;

import com.mercadolibre.adnmutant.service.dto.DnaDTO;
import com.mercadolibre.adnmutant.service.dto.DnaRequest;
import com.mercadolibre.adnmutant.service.utils.AdnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectMutantService {

    @Autowired
    private DnaService dnaService;

    @Autowired
    AdnUtil adnUtil;

    /**
     * validate DNA structure
     * @param dna
     * @return
     */
    public boolean validateAdn(DnaRequest dna){
        return adnUtil.validateAdn(dna.getDna());
    }

    /**
     * validate that the DNA is not registered in the database and that it corresponds to a mutant
     * @param dna
     * @return
     */
    public boolean isMutant(DnaRequest dna){
        String strDna = String.join(",", dna.getDna());
        List<DnaDTO> dnaResponse = dnaService.findByDna(strDna);
        if(dnaResponse.isEmpty()){
            boolean dnaMutant = adnUtil.validateIsMutant(dna.getDna());
            DnaDTO dnaDTO = new DnaDTO();
            dnaDTO.setDnaPerson(strDna);
            dnaDTO.setMutant(dnaMutant);
            dnaDTO = dnaService.save(dnaDTO);
            return dnaDTO.getMutant();
        }
        return dnaResponse.get(0).getMutant();
    }



}
