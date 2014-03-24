/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import java.sql.Date;

/**
 *
 * @author Maxime AUBRY
 */
public class Evenement {
    private long id;
    private Enum typeRDV;
    private String commentaire;
    private Date dateDeDebut;
    private Date dateDeFin;
    private long idContact;
    private long idCommercial;

    public Evenement() {
        this.id = 0;
        this.typeRDV = null;
        this.commentaire = null;
        this.dateDeDebut = null;
        this.dateDeFin = null;
        this.idContact = 0;
        this.idCommercial = 0;
    }
    
    public Evenement(long id, Enum typeRDV, String commentaire, Date dateDeDebut, Date dateDeFin, long idContact, long idCommercial) {
        this.id = id;
        this.typeRDV = typeRDV;
        this.commentaire = commentaire;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.idContact = idContact;
        this.idCommercial = idCommercial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum getTypeRDV() {
        return typeRDV;
    }

    public void setTypeRDV(Enum typeRDV) {
        this.typeRDV = typeRDV;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.commentaire = Commentaire;
    }

    public Date getDateDeDebut() {
        return dateDeDebut;
    }

    public void setDateDeDebut(Date dateDeDebut) {
        this.dateDeDebut = dateDeDebut;
    }

    public Date getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(Date dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public long getIdContact() {
        return idContact;
    }

    public void setIdContact(long idContact) {
        this.idContact = idContact;
    }

    public long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(long idCommercial) {
        this.idCommercial = idCommercial;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", typeRDV=" + typeRDV + ", commentaire=" + commentaire + ", dateDeDebut=" + dateDeDebut + ", dateDeFin=" + dateDeFin + ", idContact=" + idContact + ", idCommercial=" + idCommercial + '}';
    }
}
