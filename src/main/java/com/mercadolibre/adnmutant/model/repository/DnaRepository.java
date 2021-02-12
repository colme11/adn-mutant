package com.mercadolibre.adnmutant.model.repository;

import com.mercadolibre.adnmutant.model.crud.DnaCrudRepository;
import com.mercadolibre.adnmutant.model.entity.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class interacts with the adn table of our database
 */
@Repository
public class DnaRepository implements IDnaRepository{

    @Autowired
    private DnaCrudRepository dnaCrudRepository;

    @Override
    public List<Dna> getAll() {
        return (List<Dna>) dnaCrudRepository.findAll();
    }

    @Override
    public Dna findByDna(String dna) {
        return dnaCrudRepository.findByDna(dna);
    }

    @Override
    public Dna save(Dna dna) {
        return dnaCrudRepository.save(dna);
    }

    @Override
    public List<Dna> findByIsMutant(boolean isMutant) {
        return dnaCrudRepository.findByIsMutant(isMutant);
    }
}
