/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

/**
 *
 * @author Maxime
 */
public class Contact {
    private long id;
    private String reference;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Boolean actif;
    private long idClient;
    private long idCommercial;

    public Contact() {
        this.id = 0;
        this.reference = null;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.telephone = null;
        this.actif = false;
        this.idClient = 0;
        this.idCommercial = 0;
    }

    public Contact(long Id, String reference, String nom, String prenom, String email, String telephone, Boolean actif, long idClient, long idCommercial) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.actif = actif;
        this.idClient = idClient;
        this.idCommercial = idCommercial;
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

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(long idCommercial) {
        this.idCommercial = idCommercial;
    }

    @Override
    public String toString() {
        return this.getNom();
    }
}
