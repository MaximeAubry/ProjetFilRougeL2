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
public class Devis extends Document implements IValidation {
    private Date dateDeFinDeValidite;
    private int graduationDeDemande;
    private Boolean signe;
    private double montantTotalHT;
    private double remise;
    private double fraisDeTransport;
    private double tauxDeTva;
    private double montantTotalTTC;
    private Long idCommande;

    public Devis() {
        super();
        
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(this.getDateDeCreation());
        nextMonth.add(Calendar.MONTH, 1);
        
        this.setReference("DEV-" + Calendar.getInstance().getTime().getTime());
        
        this.dateDeFinDeValidite = new Date(nextMonth.getTimeInMillis());
        this.graduationDeDemande = 0;
        this.signe = false;
        this.montantTotalHT = 0;
        this.remise = 0;
        this.fraisDeTransport = 0;
        this.tauxDeTva = 0;
        this.montantTotalTTC = 0;
        this.idCommande = null;
    }

    public Devis(long id, String reference, Date dateDeCreation, long idCommercial, long idClient, Date dateDeFinDeValidite, int graduationDeDemande, Boolean signe, double montantTotalHT, double remise, double fraisDeTransport, double tauxDeTva, double montantTotalTTC, Long idCommande) {
        super(id, reference, dateDeCreation, idCommercial, idClient);
        
        this.setReference("DEV-" + Calendar.getInstance().getTime().getTime());
        
        this.dateDeFinDeValidite = dateDeFinDeValidite;
        this.graduationDeDemande = graduationDeDemande;
        this.signe = signe;
        this.montantTotalHT = montantTotalHT;
        this.remise = remise;
        this.fraisDeTransport = fraisDeTransport;
        this.tauxDeTva = tauxDeTva;
        this.montantTotalTTC = montantTotalTTC;
        this.idCommande = idCommande;
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

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
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
