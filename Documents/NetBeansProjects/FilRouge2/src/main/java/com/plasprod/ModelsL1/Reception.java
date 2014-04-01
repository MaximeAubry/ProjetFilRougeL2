package com.plasprod.ModelsL1; /***********************************************************************
 * Module:  Reception.java
 * Author:  antoi_000
 * Purpose: Defines the Class Reception
 ***********************************************************************/

import java.util.*;

public class Reception extends ModelCustom{

    private float quantite;
    private Date date;

    private Commande commande;

    private Produit produit;


    /**
     * Gets quantite.
     *
     * @return Value of quantite.
     */
    public float getQuantite() {
        return quantite;
    }

    /**
     * Gets commande.
     *
     * @return Value of commande.
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * Sets new date.
     *
     * @param date New value of date.
     */
    public void setDate(Date date) {
        this.date = date;
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
     * Sets new commande.
     *
     * @param commande New value of commande.
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
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
     * Sets new produit.
     *
     * @param produit New value of produit.
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Gets date.
     *
     * @return Value of date.
     */
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "quantite=" + quantite +
                ", date=" + date +
                ", commande=" + commande +
                ", produit=" + produit +
                '}';
    }
}