package com.plasprod.ModelsL1; /***********************************************************************
 * Module:  Produit.java
 * Author:  antoi_000
 * Purpose: Defines the Class Produit
 ***********************************************************************/

import models.enums.TypeArticle;

public class Produit extends ModelCustom{

    private String nom;
    private String reference;
    public TypeArticle typeArticle;
    private String unite;
    private Float quantiteStock;
    private Float seuilQteMin;
    private Float seuilQteMax;
    private Float qteMaxEmp;
    private Float prixUnitaire;
    private String couleur;
    private boolean actif;

    public String getReference() {
        return reference;
    } 

    public String getNom() {
        return nom;
    }

    public TypeArticle getTypeArticle() {
        return typeArticle;
    }

    public String getUnite() {
        return unite;
    }

    public Float getQuantiteStock() {
        return quantiteStock;
    }

    public Float getSeuilQteMin() {
        return seuilQteMin;
    }

    public Float getSeuilQteMax() {
        return seuilQteMax;
    }

    public Float getQteMaxEmp() {
        return qteMaxEmp;
    }

    public Float getPrixUnitaire() {
        return prixUnitaire;
    }

    public String getCouleur() {
        return couleur;
    }

    public boolean isActif() {
        return actif;
    }
    
    
}