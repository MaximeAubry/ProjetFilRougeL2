package com.plasprod.JDBC;

import com.plasprod.Models.Commande;
import com.plasprod.Models.Enums.StatutCommande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCommande {
    public static void creationCommande(Commande commande) {
        // Document
        long Id = DAODocument.ajoutDocument(commande);
        commande.setId(Id);
        
        // Commande
        ConnectionBDD.creerConnection();
        String requete = "INSERT INTO Commande " +
                            "(statutCommande,delaiExpedition,IdDocument) " +
                        "VALUES " +
                            "(?,?,?);";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,commande.getStatutCommande().toString());
            preparedStatement.setLong(2,commande.getDelaiExpedition());
            preparedStatement.setLong(3,commande.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationCommande(Commande commande) {
        // Document
        DAODocument.modificationDocument(commande);
        
        // Commande
        ConnectionBDD.creerConnection();
        String requete = "UPDATE commande " +
                        "SET " +
                        "statutCommande = ? " +
                        ",delaiExpedition = ? " +
                        "WHERE IdDocument = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,commande.getStatutCommande().toString());
            preparedStatement.setLong(2,commande.getDelaiExpedition());
            preparedStatement.setLong(3,commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionCommande(Commande commande) {
        // Document
        DAODocument.suppressionDocument(commande);
    }
    
    public static ArrayList<Commande> getListCommandes() {
        ArrayList<Commande> commandes = new ArrayList<Commande>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,statutCommande,delaiExpedition " +
                            "FROM Document " +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commande commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition")
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
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,statutCommande,delaiExpedition FROM Document " +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id " +
                            "WHERE Document.Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition")
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
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,statutCommande,delaiExpedition " +
                            "FROM Document " +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id " +
                            "WHERE IdCommercial = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commande commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition")
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
