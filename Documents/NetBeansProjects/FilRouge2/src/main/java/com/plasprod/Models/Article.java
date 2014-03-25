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
public class Article {
    private long id;
    private String reference;
    private String nom;
    private String couleur;
    private Double quantiteStock;
    private String unite;
    private Double prixUnitaire;
    private Boolean actif;
    
    public Article() {
        this.id = 0;
        this.reference = null;
        this.nom = null;
        this.couleur = null;
        this.quantiteStock = 0.0;
        this.unite = null;
        this.prixUnitaire = 0.0;
        this.actif = false;
    }
    
    public Article(long id, String reference, String nom, String couleur, Double quantiteStock, String unite, Double prixUnitaire, Boolean actif) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.couleur = couleur;
        this.quantiteStock = quantiteStock;
        this.unite = unite;
        this.prixUnitaire = prixUnitaire;
        this.actif = actif;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Double getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Double quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", reference=" + reference + ", nom=" + nom + ", couleur=" + couleur + ", quantiteStock=" + quantiteStock + ", unite=" + unite + ", prixUnitaire=" + prixUnitaire + ", actif=" + actif + '}';
    }
    
}
