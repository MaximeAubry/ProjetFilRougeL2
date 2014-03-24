package com.plasprod.Models;

import java.sql.Date;

/**
 *
 * @author Maxime
 */
public class Devis extends Document {
    private Date dateDeFinDeValidite;
    private Boolean signe;
    private double remise;
    private double fraisDeTransport;
    private double tauxDeTva;
    private int graduationDeDemande;

    public Devis() {
        super();
        this.dateDeFinDeValidite = null;
        this.signe = false;
        this.remise = 0;
        this.fraisDeTransport = 0;
        this.tauxDeTva = 0;
        this.graduationDeDemande = 0;
    }

    public Devis(Date dateDeFinDeValidite, Boolean signe, double remise, double fraisDeTransport, double tauxDeTva, int graduationDeDemande, long id, Date dateDeCreation, long idCommercial, long idClient) {
        super(id, dateDeCreation, idCommercial, idClient);
        this.dateDeFinDeValidite = dateDeFinDeValidite;
        this.signe = signe;
        this.remise = remise;
        this.fraisDeTransport = fraisDeTransport;
        this.tauxDeTva = tauxDeTva;
        this.graduationDeDemande = graduationDeDemande;
    }

    public Date getDateDeFinDeValidite() {
        return dateDeFinDeValidite;
    }

    public void setDateDeFinDeValidite(Date dateDeFinDeValidite) {
        this.dateDeFinDeValidite = dateDeFinDeValidite;
    }

    public Boolean isSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getFraisDeTransport() {
        return fraisDeTransport;
    }

    public void setFraisDeTransport(double fraisDeTransport) {
        this.fraisDeTransport = fraisDeTransport;
    }

    public double getTauxDeTva() {
        return tauxDeTva;
    }

    public void setTauxDeTva(double tauxDeTva) {
        this.tauxDeTva = tauxDeTva;
    }

    public int getGraduationDeDemande() {
        return graduationDeDemande;
    }

    public void setGraduationDeDemande(int graduationDeDemande) {
        this.graduationDeDemande = graduationDeDemande;
    }

    @Override
    public String toString() {
        return "Devis{" + "dateDeFinDeValidite=" + dateDeFinDeValidite + ", signe=" + signe + ", remise=" + remise + ", fraisDeTransport=" + fraisDeTransport + ", tauxDeTva=" + tauxDeTva + ", graduationDeDemande=" + graduationDeDemande + '}';
    }
}
