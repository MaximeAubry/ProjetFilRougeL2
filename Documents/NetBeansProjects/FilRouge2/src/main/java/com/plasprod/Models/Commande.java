/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import java.sql.Date;

/**
 *
 * @author Maxime
 */
public class Commande extends Document {
    private long statutCommande;
    private long delaiExpedition;

    public Commande() {
        super();
        this.statutCommande = 0;
        this.delaiExpedition = 0;
    }

    public Commande(long statutCommande, long delaiExpedition, long id, Date dateDeCreation, long idCommercial, long idClient) {
        super(id, dateDeCreation, idCommercial, idClient);
        this.statutCommande = statutCommande;
        this.delaiExpedition = delaiExpedition;
    }

    public long getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(long statutCommande) {
        this.statutCommande = statutCommande;
    }

    public long getDelaiExpedition() {
        return delaiExpedition;
    }

    public void setDelaiExpedition(long delaiExpedition) {
        this.delaiExpedition = delaiExpedition;
    }

    @Override
    public String toString() {
        return "Commande{" + "statutCommande=" + statutCommande + ", delaiExpedition=" + delaiExpedition + '}';
    }
}
