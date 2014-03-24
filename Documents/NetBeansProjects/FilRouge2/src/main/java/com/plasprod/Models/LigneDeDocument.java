/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

/**
 *
 * @author Maxime
 */
public class LigneDeDocument {
    private long id;
    private int numeroDeLigne;
    private int qte;
    private double remise;
    private long idDocument;
    private long idArticle;

    public LigneDeDocument() {
        this.id = 0;
        this.numeroDeLigne = 0;
        this.qte = 0;
        this.remise = 0;
        this.idDocument = 0;
        this.idArticle = 0;
    }

    public LigneDeDocument(long id, int numeroDeLigne, int qte, double remise, long idDocument, long idArticle) {
        this.id = id;
        this.numeroDeLigne = numeroDeLigne;
        this.qte = qte;
        this.remise = remise;
        this.idDocument = idDocument;
        this.idArticle = idArticle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroDeLigne() {
        return numeroDeLigne;
    }

    public void setNumeroDeLigne(int numeroDeLigne) {
        this.numeroDeLigne = numeroDeLigne;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(long idDocument) {
        this.idDocument = idDocument;
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public String toString() {
        return "ligneDeDocument{" + "id=" + id + ", numeroDeLigne=" + numeroDeLigne + ", qte=" + qte + ", remise=" + remise + ", idDocument=" + idDocument + ", idArticle=" + idArticle + '}';
    }
}
