/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models.CustomObjects;

import com.plasprod.Models.Evenement;
import java.sql.Time;

/**
 *
 * @author Maxime
 */
public class HeureEvenement {
    private Time heure;
    private Boolean enabled;
    private Evenement evenementActuel;

    public HeureEvenement(Time heure, Boolean enabled, Evenement evenementActuel) {
        this.heure = heure;
        this.enabled = enabled;
        this.evenementActuel = evenementActuel;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Evenement getEvenementActuel() {
        return evenementActuel;
    }

    public void setEvenementActuel(Evenement evenementActuel) {
        this.evenementActuel = evenementActuel;
    }

    @Override
    public String toString() {
        return this.getHeure().toString();
    }
}
