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
    private Enum statutCommande;
    private long delaiExpedition;

    public Commande() {
        super();
        this.statutCommande = null;
        this.delaiExpedition = 0;
    }

    public Commande(long id, Date dateDeCreation, long idCommercial, long idClient, Enum statutCommande, long delaiExpedition) {
        super(id, dateDeCreation, idCommercial, idClient);
        this.statutCommande = statutCommande;
        this.delaiExpedition = delaiExpedition;
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

    @Override
    public String toString() {
        return "Commande{" + "statutCommande=" + statutCommande + ", delaiExpedition=" + delaiExpedition + '}';
    }
}
