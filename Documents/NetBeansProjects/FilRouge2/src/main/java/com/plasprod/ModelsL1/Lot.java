package com.plasprod.ModelsL1; /***********************************************************************
 * Module:  Lot.java
 * Author:  Antoine
 * Purpose: Defines the Class Lot
 ***********************************************************************/


import java.util.*;

public class Lot extends ModelCustom {
   public Date dateStock;
   public String reference;
   public float quantite;
   public Produit produit;

   public List<Emplacement> emplacements;

//    public Lot(Date dateStock, String reference, float quantite, Produit produit, List<Emplacement> emplacements) {
//        this.dateStock = dateStock;
//        this.reference = reference;
//        this.quantite = quantite;
//        this.produit = produit;
//        this.emplacements = emplacements;
//    }

    /**
     * Gets quantite.
     *
     * @return Value of quantite.
     */
    public float getQuantite() {
        return quantite;
    }

    /**
     * Sets new dateStock.
     *
     * @param dateStock New value of dateStock.
     */
    public void setDateStock(Date dateStock) {
        this.dateStock = dateStock;
    }

    /**
     * Sets new produit.
     *
     * @param produit New value of produit.
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Gets dateStock.
     *
     * @return Value of dateStock.
     */
    public Date getDateStock() {
        return dateStock;
    }

    /**
     * Sets new reference.
     *
     * @param reference New value of reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Sets new quantite.
     *
     * @param quantite New value of quantite.
     */
    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    /**
     * Gets produit.
     *
     * @return Value of produit.
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Sets new emplacements.
     *
     * @param emplacements New value of emplacement.
     */
    public void setEmplacements(List<Emplacement> emplacements) {
        getEmplacements().clear();
        if (emplacements != null) {
            emplacements.removeAll(Collections.singleton(null));
            for (Emplacement emplacement : emplacements) {
                emplacement.lot = this;
            }
            this.emplacements.addAll(emplacements);
        }
    }

    /**
     * Gets emplacements.
     *
     * @return Value of emplacement.
     */
    public List<Emplacement> getEmplacements() {
        if (emplacements == null) emplacements = new ArrayList<>();
        return emplacements;
    }

    public String toString() {
        return reference;
    }
}