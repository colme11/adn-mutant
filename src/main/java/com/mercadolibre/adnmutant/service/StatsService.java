package com.mercadolibre.adnmutant.service;

import com.mercadolibre.adnmutant.service.dto.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Oscar Villarreal
 */
@Service
public class StatsService {

    @Autowired
    private DnaService dnaService;

    /**
     * Check the number of mutants and humans and their ratio
     * @return
     */
    public StatsDTO generateStatistics(){
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setCount_mutant_dna(dnaService.findByIsMutant(true).size());
        statsDTO.setCount_human_dna(dnaService.findByIsMutant(false).size());
        if (statsDTO.getCount_mutant_dna() <= 0 && statsDTO.getCount_human_dna() <= 0){
            statsDTO.setRatio(0);
            return statsDTO;
        }
        if (statsDTO.getCount_human_dna() == 0){
            statsDTO.setRatio(1);
            return statsDTO;
        }
        statsDTO.setRatio(statsDTO.getCount_mutant_dna() / statsDTO.getCount_human_dna());
        return statsDTO;
    }
}
