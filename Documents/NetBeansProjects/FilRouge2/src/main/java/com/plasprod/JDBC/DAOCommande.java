package com.plasprod.JDBC;

import com.plasprod.Models.Commande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antoine Demarly
 */
public class DAOCommande {
    public static void creationCommande(Commande commande) {
        // Document
        long Id = DAODocument.ajoutDocument(commande);
        commande.setId(Id);
        
        // Commande
        ConnectionBDD.creerConnection();
        String requete = "INSERT INTO Commande\n" +
                            "(statutCommande,delaiExpedition,IdDocument)\n" +
                        "VALUES\n" +
                            "(?,?,?);";
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

    public void modificationCommande(Commande commande) {
        // Document
        DAODocument.modificationDocument(commande);
        
        // Commande
        ConnectionBDD.creerConnection();
        String requete = "UPDATE commande\n" +
                        "SET\n" +
                        "statutCommande = ?\n" +
                        ",delaiExpedition = ?\n" +
                        "WHERE IdDocument = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setInt(1,commande.getStatutCommande());
            preparedStatement.setInt(1,commande.getDelaiExpedition());
            preparedStatement.setLong(8,commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public void suppressionCommande(Commande commande) {
        // Document
        DAODocument.suppressionDocument(commande);
    }
    
    public static ArrayList<Commande> getListCommandes() {
        ArrayList<Commande> commandes = new ArrayList<Commande>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition\n" +
                            "FROM Document\n" +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commande commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getInt("statutCommande"),
                    resultat.getInt("delaiExpedition")
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commandes;
    }
    
    public static Commande getCommande(long Id) {
        Commande commande = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition FROM Document\n" +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id\n" +
                            "WHERE Document.Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getInt("statutCommande"),
                    resultat.getInt("delaiExpedition")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commande;
    }
    
    public ArrayList<Commande> getCommandesParCommercial(long Id) {
        ArrayList<Commande> commandes = new ArrayList<Commande>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition\n" +
                            "FROM Document\n" +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id\n" +
                            "WHERE IdCommercial = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commande commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getInt("statutCommande"),
                    resultat.getInt("delaiExpedition")
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commandes;
    }
}
