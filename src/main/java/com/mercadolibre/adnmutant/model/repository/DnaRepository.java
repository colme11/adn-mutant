package com.mercadolibre.adnmutant.model.repository;

import com.mercadolibre.adnmutant.model.crud.DnaCrudRepository;
import com.mercadolibre.adnmutant.model.entity.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DnaRepository implements IDnaRepository{

    @Autowired
    private DnaCrudRepository dnaCrudRepository;

    @Override
    public List<Dna> findByDna(String dna) {
        return dnaCrudRepository.findByDna(dna);
    }

    @Override
    public Dna save(Dna dna) {
        return dnaCrudRepository.save(dna);
    }
}
