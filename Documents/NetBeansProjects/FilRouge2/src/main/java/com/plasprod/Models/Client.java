/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.IValidation.IValidation;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Antoine Demarly
 */
public class Client implements IValidation {
    private long id;
    private String reference;
    private String raisonSociale;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;
    private Boolean actif;

    public Client() {
        this.id = 0;
        this.reference = "CLIENT-" + Calendar.getInstance().getTime().getTime();
        this.raisonSociale = null;
        this.adresse = null;
        this.codePostal = null;
        this.ville = null;
        this.pays = null;
        this.actif = false;
    }
    
    public Client(long id, String reference, String raisonSociale, String adresse, String codePostal, String ville, String pays, Boolean actif) {
        this.id = id;
        this.reference = "CLIENT-" + Calendar.getInstance().getTime().getTime();
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.actif = actif;
    }

    public long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return this.getRaisonSociale();
    }
    
    /***************************************************************************
     * IValidation
     **************************************************************************/
    private Map<String, String> constraintViolations;
    
    @Override
    public Boolean isValid() {
        constraintViolations = new HashMap<String, String>();
        
        // raisonSociale
        if (this.raisonSociale.isEmpty()) {
            constraintViolations.put("RaisonSocialeIsValid", "La raison sociale est obligatoire.");
        } else {
            if (this.raisonSociale.length() > 50) {
                constraintViolations.put("RaisonSocialeIsValid", "La raison sociale doit comporter 50 caractères maximum.");
            }
        }
            
        // adresse
        if (this.adresse.isEmpty()) {
            constraintViolations.put("AdresseIsValid", "L'adresse est obligatoire.");
        } else {
            if (this.adresse.length() > 50) {
                constraintViolations.put("AdresseIsValid", "L'adresse doit comporter 50 caractères maximum.");
            }
        }
        
        // codePostal
        if (this.codePostal.isEmpty()) {
            constraintViolations.put("CodePostalIsValid", "Le code postal est obligatoire.");
        } else {
            if (this.codePostal.matches("^.*[^a-zA-Z0-9 ].*$")) {
                constraintViolations.put("CodePostalIsValid", "Le code postal doit être de type alphanumérique.");
            } else {
                if (this.codePostal.length() != 5) {
                    constraintViolations.put("CodePostalIsValid", "Le code postal doit comporter 5 caractères.");
                }
            }
        }
        
        // ville
        if (this.ville.isEmpty()) {
            constraintViolations.put("VilleIsValid", "La ville est obligatoire.");
        } else {
            if (this.ville.length() > 50) {
                constraintViolations.put("VilleIsValid", "La ville doit comporter 50 caractères maximum.");
            }
        }
        
        // pays
        if (this.pays.isEmpty()) {
            constraintViolations.put("PaysIsValid", "Le pays est obligatoire.");
        } else {
            if (this.pays.length() > 50) {
                constraintViolations.put("PaysIsValid", "Le pays doit comporter 50 caractères maximum.");
            }
        }
        
        return constraintViolations.isEmpty();
    }

    @Override
    public Map<String, String> getConstraintViolations() {
        return constraintViolations;
    }
}
