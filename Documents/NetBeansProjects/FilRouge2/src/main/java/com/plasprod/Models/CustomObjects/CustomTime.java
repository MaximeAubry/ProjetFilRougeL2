/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models.CustomObjects;

import java.sql.Time;

/**
 *
 * @author Maxime
 */
public class CustomTime {
    private Time heure;
    private Boolean enabled;

    public CustomTime(Time heure, Boolean enabled) {
        this.heure = heure;
        this.enabled = enabled;
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
}
