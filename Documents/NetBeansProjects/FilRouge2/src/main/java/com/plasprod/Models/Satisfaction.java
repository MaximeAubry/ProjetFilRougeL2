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
public class Satisfaction {
    private long id;
    private int note;
    private String commentaire;
    private Date dateSatisfaction;
    private long idDocument;
    private long idEvenement;

    public Satisfaction() {
        this.id = 0;
        this.note = 0;
        this.commentaire = null;
        this.dateSatisfaction = null;
        this.idDocument = 0;
        this.idEvenement = 0;
    }

    public Satisfaction(long id, int note, String commentaire, Date dateSatisfaction, long idDocument, long idEvenement) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.dateSatisfaction = dateSatisfaction;
        this.idDocument = idDocument;
        this.idEvenement = idEvenement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
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

    public long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(long idDocument) {
        this.idDocument = idDocument;
    }

    public long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(long idEvenement) {
        this.idEvenement = idEvenement;
    }

    @Override
    public String toString() {
        return "Satisfaction{" + "id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", dateSatisfaction=" + dateSatisfaction + ", idDocument=" + idDocument + ", idEvenement=" + idEvenement + '}';
    }
}
