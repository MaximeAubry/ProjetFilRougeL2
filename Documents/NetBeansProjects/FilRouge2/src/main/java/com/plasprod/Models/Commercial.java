/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.Enums.TypeCommercial;
import com.plasprod.Models.IValidation.IValidation;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxime
 */
public class Commercial implements IValidation {
    private long id;
    private String reference;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String identifiant;
    private String motDePasse;
    private TypeCommercial typeCommercial;
    private Boolean actif;

    public Commercial() {
        this.id = 0;
        this.reference = "COMMERCIAL-" + Calendar.getInstance().getTime().getTime();
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.telephone = null;
        this.identifiant = null;
        this.motDePasse = null;
        this.typeCommercial = null;
        this.actif = false;
    }
    
    public Commercial(long id, String reference, String nom, String prenom, String email, String telephone, String identifiant, String motDePasse, TypeCommercial typeCommercial, Boolean actif) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.typeCommercial = typeCommercial;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public TypeCommercial getTypeCommercial() {
        return typeCommercial;
    }

    public void setTypeCommercial(TypeCommercial typeCommercial) {
        this.typeCommercial = typeCommercial;
    }

    public Boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return this.getPrenom() + " " + this.getNom();
    }
    
    /***************************************************************************
     * IValidation
     **************************************************************************/
    private Map<String, String> constraintViolations;
    
    @Override
    public Boolean isValid() {
        constraintViolations = new HashMap<String, String>();
        
        // nom
        if (this.nom.isEmpty()) {
            constraintViolations.put("NomIsValid", "Le nom est obligatoire.");
        } else {
            if (this.nom.length() > 50) {
                constraintViolations.put("NomIsValid", "Le nom doit comporter 50 caractères maximum.");
            }
        }
        
        // prénom
        if (this.prenom.isEmpty()) {
            constraintViolations.put("PrenomIsValid", "Le prénom est obligatoire.");
        } else {
            if (this.prenom.length() > 50) {
                constraintViolations.put("PrenomIsValid", "Le prénom doit comporter 50 caractères maximum.");
            }
        }
        
        // email
        if (!this.email.isEmpty()) {
            if (this.email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                constraintViolations.put("EmailIsValid", "Le format de l'email n'est pas valide.");
            } else {
                if (this.email.length() > 50) {
                    constraintViolations.put("EmailIsValid", "L'email doit comporter 50 caractères maximum.");
                }
            }
        }
        
        // téléphone
        if (!this.telephone.isEmpty()) {
            if (this.telephone.matches("^[0-9].[0-9].[0-9].[0-9].[0-9]$")) {
                constraintViolations.put("TelephoneIsValid", "Le format de téléphone n'est pas valide.");
            }
        }
        
        // identifiant
        if (this.identifiant.isEmpty()) {
            constraintViolations.put("IdentifiantIsValid", "L'identifiant est obligatoire.");
        } else {
            if (this.prenom.length() > 50) {
                constraintViolations.put("IdentifiantIsValid", "L'identifiant doit comporter 50 caractères maximum.");
            }
        }
        
        // mot de passe
        if (this.motDePasse.isEmpty()) {
            constraintViolations.put("MotDePasseIsValid", "Le mot de passe est obligatoire.");
        } else {
            if (this.prenom.length() > 50) {
                constraintViolations.put("MotDePasseIsValid", "Le mot de passe doit comporter 50 caractères maximum.");
            }
        }
        
        return constraintViolations.isEmpty();
    }

    @Override
    public Map<String, String> getConstraintViolations() {
        return constraintViolations;
    }
}
