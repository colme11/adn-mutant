package com.mercadolibre.adnmutant.model.repository;

import com.mercadolibre.adnmutant.model.entity.Dna;

import java.util.List;

public interface IDnaRepository {

    List<Dna> findByDna(String dna);

    Dna save(Dna dna);
}
