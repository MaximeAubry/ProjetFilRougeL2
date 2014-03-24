package com.plasprod.JDBC;

import com.plasprod.Models.Commande;
import com.plasprod.Models.Commercial;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Antoine Demarly
 */
public class DAOCommande {
    public static void creationCommande(Commande commande) {
        ConnectionBDD.creerConnection();
        String requete = "INSERT INTO commande(statutCommande,delaiExpedition,IdDocument) VALUES (?,?,?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,commande.getDateDeCreation());
            preparedStatement.setLong(2,commande.getIdCommercial());
            preparedStatement.setLong(3,commande.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public void suppressionCommercial(Commercial commande) {
        ConnectionBDD.creerConnection();
        String requete = "DELETE FROM commande WHERE commande.IDCOMMERCIAL = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public void modificationCommercial(Commercial commande) {
        ConnectionBDD.creerConnection();
        String requete = "UPDATE commande SET reference = '?',nom = '?',prenom = '?',email = '?',telephone = '?',identifiant = '?',motDePasse = '?',actif = ? WHERE IDCOMMERCIAL = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,commande.getReference());
            preparedStatement.setString(2,commande.getNom());
            preparedStatement.setString(3,commande.getPrenom());
            preparedStatement.setString(4,commande.getEmail());
            preparedStatement.setString(5,commande.getTelephone());
            preparedStatement.setString(6,commande.getIdentifiant());
            preparedStatement.setString(7,commande.getMotDePasse());
            preparedStatement.setLong(8,commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public List<Commande> getCommandeParCommercial(Commercial commande) {

        List<Commande> listCommande = null;
        ConnectionBDD.creerConnection();
        String requete = "SELECT * FROM commande WHERE IDCOMMERCIAL = ?";
        PreparedStatement preparedStatement;
        try {
                preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
                preparedStatement.setLong(1,commande.getId());
                listCommande = (List<Commande>) preparedStatement.executeQuery();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return ((List<Commande>) listCommande);
    }
    
    public static Commande getLastCommandeParCommercial (Commercial commercial){
        List<Commande> listCommande = null;
        ConnectionBDD.creerConnection();
        String requete = "SELECT * FROM commande WHERE id = ? AND ROWNUM = 1 ";
        PreparedStatement preparedStatement;
        try {
                preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
                preparedStatement.setLong(1,commercial.getId());
                listCommande = (List<Commande>) preparedStatement.executeQuery();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return (Commande) listCommande.get(0);
    }
}
