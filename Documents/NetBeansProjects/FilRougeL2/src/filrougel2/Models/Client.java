/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filrougel2.Models;

/**
 *
 * @author Antoine Demarly
 */
public class Client {
    private final long id;
    private String reference;
    private String raisonSociale;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;

    public Client(long id, String reference, String raisonSociale, String adresse, String codePostal, String ville, String pays) {
        this.id = id;
        this.reference = reference;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", reference=" + reference + ", raisonSociale=" + raisonSociale + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", pays=" + pays + '}';
    }
    
}
