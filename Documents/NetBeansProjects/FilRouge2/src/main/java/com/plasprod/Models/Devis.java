package com.plasprod.Models;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Maxime
 */
public class Devis extends Document {
    private Date dateDeFinDeValidite;
    private int graduationDeDemande;
    private Boolean signe;
    private double montantTotalHT;
    private double remise;
    private double fraisDeTransport;
    private double tauxDeTva;
    private double montantTotalTTC;

    public Devis() {
        super();
        
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(this.getDateDeCreation());
        nextMonth.add(Calendar.MONTH, 1);
        
        this.dateDeFinDeValidite = new Date(nextMonth.getTimeInMillis());
        this.graduationDeDemande = 0;
        this.signe = false;
        this.montantTotalHT = 0;
        this.remise = 0;
        this.fraisDeTransport = 0;
        this.tauxDeTva = 0;
        this.montantTotalTTC = 0;
    }

    public Devis(long id, Date dateDeCreation, String reference, long idCommercial, long idClient, Date dateDeFinDeValidite, int graduationDeDemande, Boolean signe, double montantTotalHT, double remise, double fraisDeTransport, double tauxDeTva, double montantTotalTTC) {
        super(id, dateDeCreation, reference, idCommercial, idClient);
        this.dateDeFinDeValidite = dateDeFinDeValidite;
        this.graduationDeDemande = graduationDeDemande;
        this.signe = signe;
        this.montantTotalHT = montantTotalHT;
        this.remise = remise;
        this.fraisDeTransport = fraisDeTransport;
        this.tauxDeTva = tauxDeTva;
        this.montantTotalTTC = montantTotalTTC;
    }

    public Date getDateDeFinDeValidite() {
        return dateDeFinDeValidite;
    }

    public void setDateDeFinDeValidite(Date dateDeFinDeValidite) {
        this.dateDeFinDeValidite = dateDeFinDeValidite;
    }

    public int getGraduationDeDemande() {
        return graduationDeDemande;
    }

    public void setGraduationDeDemande(int graduationDeDemande) {
        this.graduationDeDemande = graduationDeDemande;
    }

    public double getMontantTotalHT() {
        return montantTotalHT;
    }

    public void setMontantTotalHT(double montantTotalHT) {
        this.montantTotalHT = montantTotalHT;
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

    public double getMontantTotalTTC() {
        return montantTotalTTC;
    }

    public void setMontantTotalTTC(double montantTotalTTC) {
        this.montantTotalTTC = montantTotalTTC;
    }

    @Override
    public String toString() {
        return this.getReference();
    }
}
