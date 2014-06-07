/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.IValidation.IValidation;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxime
 */
public class Commande extends Document implements IValidation {
    private Enum statutCommande;
    private long delaiExpedition;
    private Long idDevis;
    private Long idSatisfaction;

    public Commande() {
        super();
        
        this.setReference("COM-" + Calendar.getInstance().getTime().getTime());
        
        this.statutCommande = null;
        this.delaiExpedition = 0;
        this.idDevis = null;
        this.idSatisfaction = null;
    }

    public Commande(long id, String reference, Date dateDeCreation, long idCommercial, long idClient, Enum statutCommande, long delaiExpedition, Long idDevis, Long idSatisfaction) {
        super(id, reference, dateDeCreation, idCommercial, idClient);
        
        this.setReference("COM-" + Calendar.getInstance().getTime().getTime());
        
        this.statutCommande = statutCommande;
        this.delaiExpedition = delaiExpedition;
        this.idDevis = idDevis;
        this.idSatisfaction = idSatisfaction;
    }

    public Enum getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(Enum statutCommande) {
        this.statutCommande = statutCommande;
    }

    public long getDelaiExpedition() {
        return delaiExpedition;
    }

    public void setDelaiExpedition(long delaiExpedition) {
        this.delaiExpedition = delaiExpedition;
    }
    
    public Long getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    public Long getIdSatisfaction() {
        return idSatisfaction;
    }

    public void setIdSatisfaction(Long idSatisfaction) {
        this.idSatisfaction = idSatisfaction;
    }

    @Override
    public String toString() {
        return this.getReference();
    }
    
    /***************************************************************************
     * IValidation
     **************************************************************************/
    private Map<String, String> constraintViolations;
    
    @Override
    public Boolean isValid() {
        constraintViolations = new HashMap<String, String>();
        
        return constraintViolations.isEmpty();
    }

    @Override
    public Map<String, String> getConstraintViolations() {
        return constraintViolations;
    }
}
