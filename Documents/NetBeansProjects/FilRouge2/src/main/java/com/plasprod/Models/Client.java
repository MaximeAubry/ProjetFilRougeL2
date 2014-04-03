/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Antoine Demarly
 */
public class Client {
    @NotNull
    private long id;
    
    @NotNull
    @Size(
        min = 7,
        max = 17,
        message = "La référence doit comporter entre 7 et 17 caractères."
    )
    private String reference;
    
    @NotNull
    @Size(
        min = 1,
        max = 50,
        message = "La raison sociale doit comporter entre 1 et 50 caractères."
    )
    private String raisonSociale;
    
    @NotNull
    @Size(
        min = 1,
        max = 50,
        message = "Une adresse doit comporter entre 1 et 50 caractères."
    )
    private String adresse;
    
    @NotNull
    @Size(
        min = 5,
        max = 5,
        message = "Un code postal doit comporter 5 caractères."
    )
    private String codePostal;
    
    @NotNull
    @Size(
        min = 1,
        max = 50,
        message = "Une ville doit comporter entre 1 et 50 caractères."
    )
    private String ville;
    
    @NotNull
    @Size(
        min = 1,
        max = 50,
        message = "Un pays doit comporter entre 1 et 50 caractères."
    )
    private String pays;
    
    @NotNull
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
