/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Maxime
 */
public class Commande extends Document {
    private Enum statutCommande;
    private long delaiExpedition;
    private Long idDevis;

    public Commande() {
        super();
        
        this.setReference("COM-" + Calendar.getInstance().getTime().getTime());
        
        this.statutCommande = null;
        this.delaiExpedition = 0;
        this.idDevis = null;
    }

    public Commande(long id, String reference, Date dateDeCreation, long idCommercial, long idClient, Enum statutCommande, long delaiExpedition, Long idDevis) {
        super(id, reference, dateDeCreation, idCommercial, idClient);
        
        this.setReference("COM-" + Calendar.getInstance().getTime().getTime());
        
        this.statutCommande = statutCommande;
        this.delaiExpedition = delaiExpedition;
        this.idDevis = idDevis;
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

    @Override
    public String toString() {
        return this.getReference();
    }
}
