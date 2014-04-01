/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

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
        Calendar dateEvenement = Calendar.getInstance();
        
        this.id = 0;
        this.typeRDV = null;
        this.commentaire = null;
        this.dateDeDebut = new Date(dateEvenement.getTimeInMillis());
        this.dateDeFin = new Date(dateEvenement.getTimeInMillis());
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

    public Date getDateDeDebut(Boolean avecHeure) {
        Calendar _dateDeDebut = Calendar.getInstance();
        _dateDeDebut.setTime(this.dateDeDebut);
        
        if (!avecHeure) {
            _dateDeDebut.set(Calendar.HOUR_OF_DAY, 0);
            _dateDeDebut.set(Calendar.MINUTE, 0);
            _dateDeDebut.set(Calendar.SECOND, 0);
            _dateDeDebut.set(Calendar.MILLISECOND, 0);
        }
        
        return new Date(_dateDeDebut.getTimeInMillis());
    }

    public void setDateDeDebut(Date dateDeDebut) {
        Calendar _dateDeDebut = Calendar.getInstance();
        _dateDeDebut.setTime(dateDeDebut);
        
        this.dateDeDebut = new Date(_dateDeDebut.getTimeInMillis());
    }

    public Time getHeureDeDebut() {
        Calendar _dateDeDebut = Calendar.getInstance();
        _dateDeDebut.setTime(this.getDateDeDebut(true));
        
        return new Time((_dateDeDebut.get(Calendar.HOUR_OF_DAY) - 1) * 60 * 60 * 1000);
    }

    public void setHeureDeDebut(Time heureDeDebut) {
        Calendar _heureDeDebut = Calendar.getInstance();
        _heureDeDebut.setTime(heureDeDebut);
        
        Calendar _dateDeDebut = Calendar.getInstance();
        _dateDeDebut.setTime(this.getDateDeDebut(false));
        _dateDeDebut.set(Calendar.HOUR_OF_DAY, _heureDeDebut.get(Calendar.HOUR_OF_DAY));
        
        this.setDateDeDebut(new Date(_dateDeDebut.getTime().getTime()));
    }

    public int getDuree() {
        Calendar _dateDeDebut = Calendar.getInstance();
        _dateDeDebut.setTime(this.getDateDeDebut(true));
        
        Calendar _dateDeFin = Calendar.getInstance();
        _dateDeFin.setTime(this.getDateDeFin(true));
        
        return (_dateDeFin.get(Calendar.HOUR_OF_DAY) - _dateDeDebut.get(Calendar.HOUR_OF_DAY));
    }

    public void setDuree(int duree) {
        Time _heureDeFin = new Time((duree - 1) * 60 * 60 * 1000);
        
        this.setDateDeFin(this.getDateDeDebut(true));
        this.setHeureDeFin(_heureDeFin);
    }
    
    public Date getDateDeFin(Boolean avecHeure) {
        Calendar _dateDeFin = Calendar.getInstance();
        _dateDeFin.setTime(this.dateDeFin);
        
        if (!avecHeure) {
            _dateDeFin.set(Calendar.HOUR_OF_DAY, 0);
            _dateDeFin.set(Calendar.MINUTE, 0);
            _dateDeFin.set(Calendar.SECOND, 0);
            _dateDeFin.set(Calendar.MILLISECOND, 0);
        }
        
        return new Date(_dateDeFin.getTimeInMillis());
    }

    public void setDateDeFin(Date dateDeFin) {
        Calendar _dateDeFin = Calendar.getInstance();
        _dateDeFin.setTime(dateDeFin);
        
        this.dateDeFin = new Date(_dateDeFin.getTimeInMillis());
    }
    
    public Time getHeureDeFin() {
        Calendar _dateDeFin = Calendar.getInstance();
        _dateDeFin.setTime(this.getDateDeFin(true));
        
        return new Time((_dateDeFin.get(Calendar.HOUR_OF_DAY) - 1) * 60 * 60 * 1000);
    }
    
    public void setHeureDeFin(Time heureDeFin) {
        Calendar _heureDeFin = Calendar.getInstance();
        _heureDeFin.setTime(heureDeFin);
        
        Calendar _dateDeFin = Calendar.getInstance();
        _dateDeFin.setTime(this.getDateDeDebut(false));
        _dateDeFin.set(Calendar.HOUR_OF_DAY, _heureDeFin.get(Calendar.HOUR_OF_DAY));
        
        this.setDateDeFin(new Date(_dateDeFin.getTime().getTime()));
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
        return this.getTypeRDV().toString();
    }
}
