package com.plasprod.Models;

import java.sql.Date;

/**
 *
 * @author Maxime
 */
public class Document {
    private long id;
    private Date dateDeCreation;
    private long idCommercial;
    private long idClient;

    public Document() {
        this.id = 0;
        this.dateDeCreation = null;
        this.idCommercial = 0;
        this.idClient = 0;
    }

    public Document(long id, Date dateDeCreation, long idCommercial, long idClient) {
        this.id = id;
        this.dateDeCreation = dateDeCreation;
        this.idCommercial = idCommercial;
        this.idClient = idClient;
    }

    public long getId() {
        return id;
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
