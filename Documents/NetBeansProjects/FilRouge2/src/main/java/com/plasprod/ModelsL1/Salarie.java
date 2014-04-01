package com.plasprod.ModelsL1;
/***********************************************************************
 * Module:  Salarie.java
 * Author:  antoi_000
 * Purpose: Defines the Class Salarie
 ***********************************************************************/

import models.enums.RoleSalarie;

public class Salarie extends ModelCustom {

    public String identifiant;

    public String mdp;
    public String reference;
    public String nom;
    public String prenom;
    public String fonction;
    public String ligneFixe;
    public String courriel;
    public boolean actif;

    public RoleSalarie roleSalarie;

    public Service service;

    /**
     * Sets new reference.
     *
     * @param reference New value of reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}