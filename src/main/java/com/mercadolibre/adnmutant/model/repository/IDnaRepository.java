package com.mercadolibre.adnmutant.model.repository;

import com.mercadolibre.adnmutant.model.entity.Dna;

import java.util.List;

public interface IDnaRepository {

    List<Dna> getAll();

    Dna findByDna(String dna);

    Dna save(Dna dna);

    List<Dna> findByIsMutant(boolean isMutant);
}
