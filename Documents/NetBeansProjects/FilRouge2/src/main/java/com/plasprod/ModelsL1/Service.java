package com.plasprod.ModelsL1;
/***********************************************************************
 * Module:  Service.java
 * Author:  antoi_000
 * Purpose: Defines the Class Service
 ***********************************************************************/

import java.util.*;

public class Service extends ModelCustom {
   public String libelle;

   public List<Salarie> salarie;

   public List<Alerte> alerte;

    public Service(String libelle, List<Salarie> salarie, List<Alerte> alerte) {
        this.libelle = libelle;
        this.salarie = salarie;
        this.alerte = alerte;
    }

    /**
     * Gets alerte.
     *
     * @return Value of alerte.
     */
    public List<Alerte> getAlerte() {
        return alerte;
    }

    /**
     * Sets new salarie.
     *
     * @param salarie New value of salarie.
     */
    public void setSalarie(List<Salarie> salarie) {
        this.salarie = salarie;
    }

    /**
     * Gets salarie.
     *
     * @return Value of salarie.
     */
    public List<Salarie> getSalarie() {
        return salarie;
    }

    /**
     * Gets libelle.
     *
     * @return Value of libelle.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Sets new libelle.
     *
     * @param libelle New value of libelle.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Sets new alerte.
     *
     * @param alerte New value of alerte.
     */
    public void setAlerte(List<Alerte> alerte) {
        this.alerte = alerte;
    }

    @Override
    public String toString() {
        return libelle;
    }
}