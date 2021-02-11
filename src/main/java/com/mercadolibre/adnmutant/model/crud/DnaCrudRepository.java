package com.mercadolibre.adnmutant.model.crud;

import com.mercadolibre.adnmutant.model.entity.Dna;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DnaCrudRepository extends CrudRepository<Dna, Integer> {

    List<Dna> findByDna(String dna);

    List<Dna> findByIsMutant(boolean isMutant);
}
