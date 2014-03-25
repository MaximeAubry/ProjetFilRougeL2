package com.plasprod.JDBC;

import com.plasprod.Models.Devis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODevis {
    public static void ajoutDevis(Devis devis) {
        // Document
        long Id = DAODocument.ajoutDocument(devis);
        devis.setId(Id);
        
        // Devis
        ConnectionBDD.creerConnection();
        String requete = "INSERT INTO Devis\n" +
                            "(dateDeFinDeValidite,signe,remise,fraisDeTransport,tauxDeTva,graduationDeDemande,IdDocument)\n" +
                        "VALUES\n" +
                            "(?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,devis.getDateDeFinDeValidite());
            preparedStatement.setBoolean(2,devis.isSigne());
            preparedStatement.setDouble(3,devis.getRemise());
            preparedStatement.setDouble(4,devis.getFraisDeTransport());
            preparedStatement.setDouble(5,devis.getTauxDeTva());
            preparedStatement.setInt(6,devis.getGraduationDeDemande());
            preparedStatement.setLong(7,devis.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationDevis(Devis devis) {
        // Document
        DAODocument.modificationDocument(devis);
        
        // Devis
        ConnectionBDD.creerConnection();
        String requete = "UPDATE Devis\n" +
                        "SET\n" +
                            "dateDeFinDeValidite = ?\n" +
                            ",signe = ?\n" +
                            ",remise = ?\n" +
                            ",fraisDeTransport = ?\n" +
                            ",tauxDeTva = ?\n" +
                            ",graduationDeDemande = ?\n" +
                        "WHERE IdDocument = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,devis.getDateDeFinDeValidite());
            preparedStatement.setBoolean(2,devis.isSigne());
            preparedStatement.setDouble(3,devis.getRemise());
            preparedStatement.setDouble(4,devis.getFraisDeTransport());
            preparedStatement.setDouble(5,devis.getTauxDeTva());
            preparedStatement.setInt(6,devis.getGraduationDeDemande());
            preparedStatement.setLong(7,devis.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionDevis(Devis devis) {
        // Document
        DAODocument.suppressionDocument(devis);
    }
    
    public static ArrayList<Devis> getListDevis() {
        ArrayList<Devis> devis = new ArrayList<Devis>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,signe,remise,fraisDeTransport,tauxDeTva,graduationDeDemande\n" +
                            "FROM Document\n" +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Devis _devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getDate("dateDeFinDeValidite"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getInt("graduationDeDemande")
                );
                devis.add(_devis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return devis;
    }
    
    public static Devis getDevis(long Id) {
        Devis devis = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,signe,remise,fraisDeTransport,tauxDeTva,graduationDeDemande\n" +
                            "FROM Document\n" +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id\n" +
                            "WHERE Document.Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getDate("dateDeFinDeValidite"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getInt("graduationDeDemande")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return devis;
    }
    
    public ArrayList<Devis> getDevisParCommercial(long Id) {
        ArrayList<Devis> devis = new ArrayList<Devis>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,signe,remise,fraisDeTransport,tauxDeTva,graduationDeDemande\n" +
                            "FROM Document\n" +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id\n" +
                            "WHERE IdCommercial = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Devis _devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getDate("dateDeCreation"),
                    resultat.getString("referencce"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdClient"),
                    resultat.getDate("dateDeFinDeValidite"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getInt("graduationDeDemande")
                );
                devis.add(_devis);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return devis;
    }
}
