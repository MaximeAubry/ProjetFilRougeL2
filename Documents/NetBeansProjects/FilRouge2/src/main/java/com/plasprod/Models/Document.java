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
        this.reference = null;
        this.dateDeCreation = new Date(now.getTimeInMillis());
        this.idCommercial = 0;
        this.idContact = 0;
    }

    public Document(long id, String reference, Date dateDeCreation, long idCommercial, long idContact) {
        this.id = id;
        this.reference = reference;
        this.dateDeCreation = dateDeCreation;
        this.idCommercial = idCommercial;
        this.idContact = idContact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
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
