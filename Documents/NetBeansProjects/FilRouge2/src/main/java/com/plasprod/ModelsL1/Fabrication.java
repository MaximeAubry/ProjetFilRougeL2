package com.plasprod.ModelsL1; /***********************************************************************
 * Module:  Fabrication.java
 * Author:  antoine
 * Purpose: Defines the Class Fabrication
 ***********************************************************************/

import java.util.*;

public class Fabrication extends ModelCustom{

    private long id;
    private Date dateCreation;
    private boolean termine;
    private float quantite;

     
    private Produit produit;

    public Fabrication(long id, Date dateCreation, boolean termine, float quantite, Produit produit) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.termine = termine;
        this.quantite = quantite;
        this.produit = produit;
    }


    /**
     * Gets quantite.
     *
     * @return Value of quantite.
     */
    public float getQuantite() {
        return quantite;
    }

    /**
     * Gets termine.
     *
     * @return Value of termine.
     */
    public boolean isTermine() {
        return termine;
    }

    /**
     * Gets dateCreation.
     *
     * @return Value of dateCreation.
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Sets new dateCreation.
     *
     * @param dateCreation New value of dateCreation.
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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
     * Sets new quantite.
     *
     * @param quantite New value of quantite.
     */
    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    /**
     * Sets new termine.
     *
     * @param termine New value of termine.
     */
    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    /**
     * Sets new produit.
     *
     * @param produit New value of produit.
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String toString() {
        return "Fabrication{" +
                "dateCreation=" + dateCreation +
                ", termine=" + termine +
                ", quantite=" + quantite +
                ", produit=" + produit +
                '}';
    }
}