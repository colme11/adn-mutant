package com.mercadolibre.adnmutant.service.dto;

public class DnaDTO {

    private Integer idDna;

    private String dnaPerson;

    private Boolean isMutant;

    public Integer getIdDna() {
        return idDna;
    }

    public void setIdDna(Integer idDna) {
        this.idDna = idDna;
    }

    public String getDnaPerson() {
        return dnaPerson;
    }

    public void setDnaPerson(String dnaPerson) {
        this.dnaPerson = dnaPerson;
    }

    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }
}
