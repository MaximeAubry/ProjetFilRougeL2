package com.plasprod.ModelsL1;

/***********************************************************************
 * Module:  Alerte.java
 * Author:  Antoine
 * Purpose: Defines the Class Alerte
 ***********************************************************************/

import java.util.*;


public class Alerte extends ModelCustom{

   private Date date;
   private boolean visible;
   private String libelle;
   private Service service;

    public Alerte(Date date, boolean visible, String libelle, Service service) {
        this.date = date;
        this.visible = visible;
        this.libelle = libelle;
        this.service = service;
    }

    /**
     * Sets new visible.
     *
     * @param visible New value of visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets service.
     *
     * @return Value of service.
     */
    public Service getService() {
        return service;
    }

    /**
     * Gets date.
     *
     * @return Value of date.
     */
    public Date getDate() {
        return date;
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
     * Sets new date.
     *
     * @param date New value of date.
     */
    public void setDate(Date date) {
        this.date = date;
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
     * Sets new service.
     *
     * @param service New value of service.
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Gets visible.
     *
     * @return Value of visible.
     */
    public boolean isVisible() {
        return visible;
    }

    public String toString() {
        return "Alerte{" +
                "date=" + date +
                ", visible=" + visible +
                ", libelle='" + libelle + '\'' +
                ", service=" + service +
                '}';
    }
}