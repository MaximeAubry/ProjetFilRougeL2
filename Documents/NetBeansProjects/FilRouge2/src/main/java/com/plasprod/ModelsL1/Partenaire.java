package com.plasprod.ModelsL1;
/***********************************************************************
 * Module:  Partenaire.java
 * Author:  antoine
 * Purpose: Defines the Class Partenaire
 ***********************************************************************/

import models.enums.Type;
import java.util.*;


public class Partenaire {

    public Type type;

    public String nom;
    public String noSIRET;
    public String reference;
    public boolean actif;
    public Integer capital;

    public List<Commande> commandes;
    public List<CoordonneePostale> coordonneePostales;
    public List<Contact> contacts;


    /**
     * Gets coordonneePostales.
     *
     * @return Value of coordonneePostales.
     */
    public List<CoordonneePostale> getCoordonneePostales() {
        if (coordonneePostales == null) coordonneePostales = new ArrayList<>();
        return coordonneePostales;
    }

    /**
     * Sets new coordonneePostales.
     *
     * @param coordonneePostales New value of coordonneePostales.
     */
    public void setCoordonneePostales(List<CoordonneePostale> coordonneePostales) {
        getCoordonneePostales().clear();
        if (coordonneePostales != null) {
            coordonneePostales.removeAll(Collections.singleton(null));
            for (CoordonneePostale coordonneePostale : coordonneePostales) {
                coordonneePostale.partenaire = this;
            }
            this.coordonneePostales.addAll(coordonneePostales);
        }
    }


    /**
     * Sets new reference.
     *
     * @param reference New value of reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    public void deleteCoordonneePostales(){
        for (CoordonneePostale coordonneePostale : getCoordonneePostales()) {
            coordonneePostale.deleted = true;
        }
    }

    @Override
    public String toString() {
        return nom ;
    }
}