package com.mercadolibre.adnmutant.service.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class AdnUtilTest {

    private AdnUtil adnUtil = new AdnUtil();

    /**
     * the matrix complies with the specified base of a DNA
     */
    @Test
    public void expectedDnaValidation() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateAdn(dna);
        assertEquals(meets, true);
    }

    /**
     * the matrix does not correspond to the specified base of a DNA
     */
    @Test
    public void expectedDnaUnexpectedValidation() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        boolean meets = adnUtil.validateAdn(dna);
        assertEquals(meets, false);
    }

    /**
     * the matrix is greater than the minimum expected length
     */
    @Test
    public void expectedminimalSequence() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.minimalSequence(dna);
        assertEquals(meets, true);
    }

    /**
     * the array is less than the minimum expected length
     */
    @Test
    public void minimalUnexpectedSequence() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.minimalSequence(dna);
        assertEquals(meets, true);
    }

    /**
     * The matrix meets the specification of N x N
     */
    @Test
    public void validateTheExpectedNxNMatrix() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateNxNMatrix(dna);
        assertEquals(meets, true);
    }

    /**
     * The matrix does not meet the N x N specification for DNA
     */
    @Test
    public void validateUnexpectedNxNMatrix() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATG");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateNxNMatrix(dna);
        assertEquals(meets, false);
    }

    /**
     * the matrix contains the identifiers corresponding to a DNA A, T, C, G
     */
    @Test
    public void validateNitrogenousDnaBaseTest() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateNitrogenousDnaBase(dna);
        assertEquals(meets, true);
    }

    /**
     * the matrix does not contain the identifiers corresponding to a DNA A, T, C, G
     */
    @Test
    public void unexpectedBasesNitrogenousDNA() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TPATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateNitrogenousDnaBase(dna);
        assertEquals(meets, false);
    }

    /**
     * the matrix is transpose from columns to rows to improve validation
     */
    @Test
    public void verticalDnaTest() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        List<String> obliqueAdn= adnUtil.verticalDna(dna);
        assertEquals(obliqueAdn.get(2), "GGAACA");
    }

    /**
     * We create a list of strings as a result of the obliques (from top left to bottom right)
     * and that meets the minimum sequence specifications
     */
    @Test
    public void obliqueDnaStringsFromLeftToRightTest() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        List<String> obliqueAdn= adnUtil.obliqueDnaStringsFromLeftToRight(dna);
        assertEquals(obliqueAdn.get(0), "AAAATG");
    }

    /**
     * We create a list of strings as a result of the obliques (from top right to bottom left)
     * and that meets the minimum sequence specifications
     */
    @Test
    public void obliqueDnaStringsFromRightToLeftTest() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        List<String> obliqueAdn= adnUtil.obliqueDnaStringsFromRightToLeft(dna);
        assertEquals(obliqueAdn.get(0), "AGTACT");
    }

    /**
     * The DNA to be validated belongs to a mutant
     */
    @Test
    public void validateIsMutant() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAATG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateIsMutant(dna);
        assertEquals(meets, true);
    }

    /**
     * The DNA to be validated belongs to a human
     */
    @Test
    public void validateIsNotMutantTest() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTCC");
        dna.add("TTATGT");
        dna.add("AGAATG");
        dna.add("CGCCTA");
        dna.add("TCACTG");
        boolean meets = adnUtil.validateIsMutant(dna);
        assertEquals(meets, false);
    }
}