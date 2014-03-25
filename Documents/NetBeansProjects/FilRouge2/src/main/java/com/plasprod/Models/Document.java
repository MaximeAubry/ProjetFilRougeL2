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
    private long idClient;

    public Document() {
        Calendar now = Calendar.getInstance();
        
        this.id = 0;
        this.dateDeCreation = new Date(now.getTimeInMillis());
        this.reference = null;
        this.idCommercial = 0;
        this.idClient = 0;
    }

    public Document(long id, Date dateDeCreation, String reference, long idCommercial, long idClient) {
        this.id = id;
        this.dateDeCreation = dateDeCreation;
        this.reference = reference;
        this.idCommercial = idCommercial;
        this.idClient = idClient;
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

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", dateDeCreation=" + dateDeCreation + ", idCommercial=" + idCommercial + ", idClient=" + idClient + '}';
    }
}
