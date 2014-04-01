/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.Enums.TypeCommercial;

/**
 *
 * @author Maxime
 */
public class Commercial {
    private long id;
    private String reference;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String identifiant;
    private String motDePasse;
    private TypeCommercial typeCommercial;
    private Boolean actif;

    public Commercial() {
        this.id = 0;
        this.reference = null;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.telephone = null;
        this.identifiant = null;
        this.motDePasse = null;
        this.typeCommercial = null;
        this.actif = false;
    }
    
    public Commercial(long id, String reference, String nom, String prenom, String email, String telephone, String identifiant, String motDePasse, TypeCommercial typeCommercial, Boolean actif) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.typeCommercial = typeCommercial;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public TypeCommercial getTypeCommercial() {
        return typeCommercial;
    }

    public void setTypeCommercial(TypeCommercial typeCommercial) {
        this.typeCommercial = typeCommercial;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return this.getPrenom() + " " + this.getNom();
    }
}
