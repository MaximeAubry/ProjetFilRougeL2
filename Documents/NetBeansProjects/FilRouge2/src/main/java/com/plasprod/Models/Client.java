/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

/**
 *
 * @author Antoine Demarly
 */
public class Client {
    private long id;
    private String reference;
    private String raisonSociale;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private Boolean actif;

    public Client() {
        this.id = 0;
        this.reference = null;
        this.raisonSociale = null;
        this.adresse = null;
        this.codePostal = null;
        this.ville = null;
        this.pays = null;
        this.actif = false;
    }
    
    public Client(long id, String reference, String raisonSociale, String adresse, String codePostal, String ville, String pays, Boolean actif) {
        this.id = id;
        this.reference = reference;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.actif = actif;
    }

    public long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return this.getRaisonSociale();
    }
}
