package com.plasprod.Models;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Maxime
 */
public class Document {
    private long id;
    private Date dateDeCreation;
    private String reference;
    private long idCommercial;
    private long idContact;

    public Document() {
        Calendar now = Calendar.getInstance();
        
        this.id = 0;
        this.dateDeCreation = new Date(now.getTimeInMillis());
        this.reference = null;
        this.idCommercial = 0;
        this.idContact = 0;
    }

    public Document(long id, Date dateDeCreation, String reference, long idCommercial, long idContact) {
        this.id = id;
        this.dateDeCreation = dateDeCreation;
        this.reference = reference;
        this.idCommercial = idCommercial;
        this.idContact = idContact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(long idCommercial) {
        this.idCommercial = idCommercial;
    }

    public long getIdContact() {
        return idContact;
    }

    public void setIdContact(long idContact) {
        this.idContact = idContact;
    }

    @Override
    public String toString() {
        return this.getReference();
    }
}
