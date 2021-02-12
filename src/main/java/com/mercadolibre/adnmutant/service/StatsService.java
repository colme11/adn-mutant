package com.mercadolibre.adnmutant.service;

import com.mercadolibre.adnmutant.service.dto.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        statsDTO.setCountMutantDna(dnaService.findByIsMutant(true).size());
        statsDTO.setCountHumanDna(dnaService.findByIsMutant(false).size());
        if (statsDTO.getCountMutantDna() <= 0 && statsDTO.getCountHumanDna() <= 0){
            statsDTO.setRatio(0);
            return statsDTO;
        }
        if (statsDTO.getCountHumanDna() == 0){
            statsDTO.setRatio(1);
            return statsDTO;
        }
        statsDTO.setRatio(statsDTO.getCountMutantDna() / statsDTO.getCountHumanDna());
        return statsDTO;
    }
}
