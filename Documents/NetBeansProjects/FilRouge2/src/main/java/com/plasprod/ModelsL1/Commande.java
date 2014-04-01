package com.plasprod.ModelsL1;
/***********************************************************************
 * Module:  Commande.java
 * Author:  antoine
 * Purpose: Defines the Class Commande
 ***********************************************************************/

import models.enums.Type;
import java.util.*;

public class Commande extends ModelCustom{

   public String reference;
   public Type type;
   public List<Reception> receptions;
   public List<CompositionCommande> compositionCommandes;
   public Partenaire partenaire;
   public List<StatutCommande> statutCommandes;



    /**
     * Gets statutCommandes.
     *
     * @return Value of statutCommandes.
     */
    public List<StatutCommande> getStatutCommandes() {
        return statutCommandes;
    }

    /**
     * Gets type.
     *
     * @return Value of type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets receptions.
     *
     * @return Value of receptions.
     */
    public List<Reception> getReceptions() {
        return receptions;
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
     * Gets partenaire.
     *
     * @return Value of partenaire.
     */
    public Partenaire getPartenaire() {
        return partenaire;
    }

    /**
     * Sets new statutCommandes.
     *
     * @param statutCommandes New value of statutCommandes.
     */
    public void setStatutCommandes(List<StatutCommande> statutCommandes) {
        this.statutCommandes = statutCommandes;
    }

    /**
     * Sets new compositionCommandes.
     *
     * @param compositionCommandes New value of compositionCommandes.
     */
    public void setCompositionCommandes(List<CompositionCommande> compositionCommandes) {
        getCompositionCommandes().clear();
        if (compositionCommandes != null) {
            compositionCommandes.removeAll(Collections.singleton(null));
            for (CompositionCommande compositionCommande : compositionCommandes) {
                compositionCommande.commande = this;
            }
            this.compositionCommandes.addAll(compositionCommandes);
        }
    }

    /**
     * Sets new type.
     *
     * @param type New value of type.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Sets new receptions.
     *
     * @param receptions New value of receptions.
     */
    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    /**
     * Sets new partenaire.
     *
     * @param partenaire New value of partenaire.
     */
    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    /**
     * Gets compositionCommandes.
     *
     * @return Value of compositionCommandes.
     */
    public List<CompositionCommande> getCompositionCommandes() {
        if (compositionCommandes == null) compositionCommandes = new ArrayList<>();
        return compositionCommandes;
    }

    public String toString() {
        return reference;
    }
}