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
    private int statutCommande;
    private int delaiExpedition;

    public Commande() {
        super();
        this.statutCommande = 0;
        this.delaiExpedition = 0;
    }

    public Commande(long id, Date dateDeCreation, long idCommercial, long idClient, int statutCommande, int delaiExpedition) {
        super(id, dateDeCreation, idCommercial, idClient);
        this.statutCommande = statutCommande;
        this.delaiExpedition = delaiExpedition;
    }

    public int getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(int statutCommande) {
        this.statutCommande = statutCommande;
    }

    public int getDelaiExpedition() {
        return delaiExpedition;
    }

    public void setDelaiExpedition(int delaiExpedition) {
        this.delaiExpedition = delaiExpedition;
    }

    @Override
    public String toString() {
        return "Commande{" + "statutCommande=" + statutCommande + ", delaiExpedition=" + delaiExpedition + '}';
    }
}
