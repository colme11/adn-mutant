package com.mercadolibre.adnmutant.controller;

import com.mercadolibre.adnmutant.model.entity.Dna;
import com.mercadolibre.adnmutant.model.repository.DnaRepository;
import com.mercadolibre.adnmutant.service.dto.DnaRequest;
import com.mercadolibre.adnmutant.service.dto.StatsDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MutantControllerTest {

    @Autowired
    private MutantController mutantController;

    @Mock
    private DnaRepository dnaRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    /**
     * the DNA to be analyzed belongs to a mutant, the test should return an http status 200
     */
    @Test
    public void isMutantTest() {
        DnaRequest dnaRequest = new DnaRequest();
        List<String> stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CAGTGC");
        stringList.add("TTATGT");
        stringList.add("AGAAGG");
        stringList.add("CCCCTA");
        stringList.add("TCACTG");
        dnaRequest.setDna(stringList);
        String strDna = String.join(",", dnaRequest.getDna());
        Dna dnaResponse = new Dna();
        Mockito.when(dnaRepository.findByDna(strDna)).thenReturn(dnaResponse);

        Dna dna = new Dna();
        dna.setDna(strDna);
        dna.setMutant(true);
        dna.setId(1);
        Mockito.when(dnaRepository.save(dna)).thenReturn(dna);

        //test
        ResponseEntity<String> httpResponse = mutantController.isMutant(dnaRequest);
        assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

    /**
     * the DNA to be analyzed belongs to a human, test should return 403 error
     */
    @Test
    public void isHumanTest() {
        DnaRequest dnaRequest = new DnaRequest();
        List<String> stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CTGTAC");
        stringList.add("TTATGT");
        stringList.add("AGAAGG");
        stringList.add("CCCCTA");
        stringList.add("TCACTG");
        dnaRequest.setDna(stringList);
        String strDna = String.join(",", dnaRequest.getDna());
        Dna dnaResponse = new Dna();
        Mockito.when(dnaRepository.findByDna(strDna)).thenReturn(dnaResponse);

        Dna dna = new Dna();
        dna.setDna(strDna);
        dna.setMutant(true);
        dna.setId(1);
        Mockito.when(dnaRepository.save(dna)).thenReturn(dna);

        //test
        ResponseEntity<String> httpResponse = mutantController.isMutant(dnaRequest);
        assertEquals(httpResponse.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    /**
     * DNA does not meet expected structure, test should return 417 error
     */
    @Test
    public void isInvalidDnaTest() {
        DnaRequest dnaRequest = new DnaRequest();
        List<String> stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CAGTGC");
        stringList.add("TTATGT");
        stringList.add("AGAAGG");
        dnaRequest.setDna(stringList);
        String strDna = String.join(",", dnaRequest.getDna());
        Dna dnaResponse = new Dna();
        Mockito.when(dnaRepository.findByDna(strDna)).thenReturn(dnaResponse);

        Dna dna = new Dna();
        dna.setDna(strDna);
        dna.setMutant(true);
        dna.setId(1);
        Mockito.when(dnaRepository.save(dna)).thenReturn(dna);

        //test
        ResponseEntity<String> httpResponse = mutantController.isMutant(dnaRequest);
        assertEquals(httpResponse.getStatusCode(), HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * Generates a status of the dna, the test should return an http status 200
     */
    @Test
    public void getStatsTest() {
        Dna dna = new Dna();
        List<Dna> dnaMutantList = new ArrayList<>();
        List<Dna> dnaHumanList = new ArrayList<>();

        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);
        dnaMutantList.add(dna);

        dnaHumanList.add(dna);
        dnaHumanList.add(dna);
        dnaHumanList.add(dna);

        Mockito.when(dnaRepository.findByIsMutant(true)).thenReturn(dnaMutantList);
        Mockito.when(dnaRepository.findByIsMutant(true)).thenReturn(dnaHumanList);

        //test
        ResponseEntity<StatsDTO> httpResponse = mutantController.getStats();
        assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }
}