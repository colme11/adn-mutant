package com.mercadolibre.adnmutant.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdnUtil {

    private static final Predicate<String> MUTANT_DNA = s -> s.matches(".*(AAAA|TTTT|CCCC|GGGG).*");
    private static final int DNA_LENGTH_OF_MUTANT = 4;

    /**
     * validate DNA
     * @param adn
     * @return
     */
    public boolean validateAdn(List<String> adn){
        if (adn != null && minimalSequence(adn) && validateNxNMatrix(adn) && validateNitrogenousDnaBase(adn)) {
            return true;
        }
        return false;
    }

    /**
     * the nitrogenous base of the minimum DNA must be 4 characters
     * @param dna
     * @return
     */
    public boolean minimalSequence(List<String> dna){
        return dna.size() >= DNA_LENGTH_OF_MUTANT;
    }

    /**
     * valid DNA meets the characteristic of an N x N matrix
     * @param adn
     * @return
     */
    public boolean validateNxNMatrix(List<String> adn){
        final int rows = adn.size();
        return adn.stream().noneMatch(row -> row.length() != rows);
    }

    /**
     * the nitrogenous base of DNA must only have the characters A, T, C and G
     * @param adn
     * @return
     */
    public boolean validateNitrogenousDnaBase(List<String> adn) {
        return !adn.parallelStream().anyMatch(dnaRow -> !dnaRow.matches("^[ATCG]*$"));
    }

    /**
     * A human is a mutant, if more than one sequence of four identical letters is found, obliquely, horizontally or vertically.
     * @param adn
     * @return
     */
    public boolean validateIsMutant( List<String> adn){
        //Validate horizontally
        int mutantSequenceCount = adn.stream().filter(MUTANT_DNA).collect(Collectors.toList()).size();
        if (mutantSequenceCount > 1) return true;
        //Validate vertically
        mutantSequenceCount += verticalDna(adn).stream()
                .filter(MUTANT_DNA).collect(Collectors.toList()).size();
        if (mutantSequenceCount > 1) return true;
        //Validate obliquely from top left to bottom right
        mutantSequenceCount += obliqueDnaStringsFromLeftToRight(adn).stream()
                .filter(MUTANT_DNA).collect(Collectors.toList()).size();
        if (mutantSequenceCount > 1) return true;
        //Validate obliquely from top right to bottom left
        mutantSequenceCount += obliqueDnaStringsFromRightToLeft(adn).stream()
                .filter(MUTANT_DNA).collect(Collectors.toList()).size();
        return mutantSequenceCount > 1;
    }

    /**
     * Vertical processing needs transposing the string arrays at char level by position
     * @param dnaStrings
     * @return
     */
    public List<String> verticalDna(final List<String> dnaStrings) {
        List<String> verticalAdn= new ArrayList<>();
        for (int rows = 0; rows < dnaStrings.size(); rows++) {
            StringBuilder column = new StringBuilder(dnaStrings.size());
            for (String dnaString : dnaStrings) {
                column.append(dnaString.charAt(rows));
            }
            verticalAdn.add(column.toString());
        }
        return verticalAdn;
    }

    /**
     * oblique processing needs transposing the string arrays at char level by position - from top left to bottom right
     * @param dnaStrings
     * @return
     */
    public List<String> obliqueDnaStringsFromLeftToRight(final List<String> dnaStrings) {
        List<String> obliqueAdn= new ArrayList<>();
        for (int rows = 0; rows <= dnaStrings.size() - DNA_LENGTH_OF_MUTANT ; rows++) {
            if(rows == 0){
                for (int column = 0; column <= dnaStrings.size() - DNA_LENGTH_OF_MUTANT; column++) {
                    StringBuilder oblique = new StringBuilder(dnaStrings.size());
                    for (int charrRows = 0, charrColumn = column; charrRows <= dnaStrings.size() - (1+column); charrRows++, charrColumn++) {
                        oblique.append(dnaStrings.get(charrRows).charAt(charrColumn));
                    }
                    obliqueAdn.add(oblique.toString());
                }
            }else{
                StringBuilder oblique = new StringBuilder(dnaStrings.size());
                for (int charrRows = rows, charrColumn = 0; charrRows <= dnaStrings.size()-1; charrRows++, charrColumn++) {
                    oblique.append(dnaStrings.get(charrRows).charAt(charrColumn));
                }
                obliqueAdn.add(oblique.toString());
            }
        }
        return obliqueAdn;
    }

    /**
     * oblique processing needs transposing the string arrays at char level by position - from top right to bottom left
     * @param dnaStrings
     * @return
     */
    public List<String> obliqueDnaStringsFromRightToLeft(final List<String> dnaStrings) {
        List<String> obliqueAdn= new ArrayList<>();
        for (int rows = 0; rows <= dnaStrings.size() - DNA_LENGTH_OF_MUTANT ; rows++) {
            if(rows == 0){
                for (int column = dnaStrings.size()-1; column >= (DNA_LENGTH_OF_MUTANT-1); column--) {
                    StringBuilder oblique = new StringBuilder(dnaStrings.size());
                    for (int charAdn = 0; charAdn <= column; charAdn++) {
                        oblique.append(dnaStrings.get(charAdn).charAt(column-charAdn));
                    }
                    obliqueAdn.add(oblique.toString());
                }
            }else{
                StringBuilder oblique = new StringBuilder(dnaStrings.size());
                for (int charrRows = rows, charrColumn = dnaStrings.size()-1; charrRows <= dnaStrings.size() -1; charrRows++, charrColumn--) {
                    oblique.append(dnaStrings.get(charrRows).charAt(charrColumn));
                }
                obliqueAdn.add(oblique.toString());
            }
        }
        return obliqueAdn;
    }
}


