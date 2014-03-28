package com.plasprod.JDBC;

import com.plasprod.Models.Commande;
import com.plasprod.Models.Enums.StatutCommande;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCommande {
    public static void genererCommande(long Id, String reference) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "EXECUTE [dbo].[GenererCommande] " +
                                "@Id = ? " +
                                ",@reference = ? " +
                                ",@statutCommande = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            preparedStatement.setString(2,reference);
            preparedStatement.setString(3,StatutCommande.ENCOURS.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationCommande(Commande commande) {
        // Document
        DAODocument.modificationDocument(commande);
        
        ConnectionBDD.creerConnection();
        try {
            // Commande
            String requete = "UPDATE commande " +
                            "SET " +
                                "statutCommande = ? " +
                                ",delaiExpedition = ? " +
                            "WHERE IdDocument = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
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
            String requete = "SELECT Document.Id,reference,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition,IdDevis " +
                            "FROM Document " +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commande commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getString("referencce"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition"),
                    resultat.getLong("IdDevis")
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
            String requete = "SELECT Document.Id,reference,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition,IdDevis " +
                            "FROM Document " +
                            "INNER JOIN Commande ON Commande.IdDocument = Document.Id " +
                            "WHERE Document.Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                commande = new Commande(
                    resultat.getLong("Id"),
                    resultat.getString("referencce"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition"),
                    resultat.getLong("IdDevis")
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
            String requete = "SELECT Document.Id,reference,dateDeCreation,IdCommercial,IdContact,statutCommande,delaiExpedition,IdDevis " +
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
                    resultat.getString("referencce"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    StatutCommande.valueOf(resultat.getString("statutCommande")),
                    resultat.getLong("delaiExpedition"),
                    resultat.getLong("IdDevis")
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
