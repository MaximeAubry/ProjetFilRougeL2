/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.IValidation.IValidation;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxime
 */
public class Satisfaction implements IValidation {
    private long id;
    private Enum niveauSatisfaction;
    private String commentaire;
    private Date dateSatisfaction;
    private long idCommande;

    public Satisfaction() {
        this.id = 0;
        this.niveauSatisfaction = null;
        this.commentaire = null;
        this.dateSatisfaction = null;
        this.idCommande = 0;
    }

    public Satisfaction(long id, Enum niveauSatisfaction, String commentaire, Date dateSatisfaction, long idCommande) {
        this.id = id;
        this.niveauSatisfaction = niveauSatisfaction;
        this.commentaire = commentaire;
        this.dateSatisfaction = dateSatisfaction;
        this.idCommande = idCommande;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum getNiveauSatisfaction() {
        return niveauSatisfaction;
    }

    public void setNiveauSatisfaction(Enum niveauSatisfaction) {
        this.niveauSatisfaction = niveauSatisfaction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateSatisfaction() {
        return dateSatisfaction;
    }

    public void setDateSatisfaction(Date dateSatisfaction) {
        this.dateSatisfaction = dateSatisfaction;
    }

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }
    
    /***************************************************************************
     * IValidation
     **************************************************************************/
    private Map<String, String> constraintViolations;
    
    @Override
    public Boolean isValid() {
        constraintViolations = new HashMap<String, String>();
            
        // adresse
        if (this.commentaire.isEmpty()) {
            constraintViolations.put("CommentaireIsValid", "Le commentaire est obligatoire.");
        } else {
            if (this.commentaire.length() > 50) {
                constraintViolations.put("CommentaireIsValid", "Le commentaire doit comporter 50 caractères maximum.");
            }
        }
        
        return constraintViolations.isEmpty();
    }

    @Override
    public Map<String, String> getConstraintViolations() {
        return constraintViolations;
    }
}
