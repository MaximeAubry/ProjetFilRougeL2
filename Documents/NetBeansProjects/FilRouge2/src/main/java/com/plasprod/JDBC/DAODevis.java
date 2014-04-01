package com.plasprod.JDBC;

import com.plasprod.Models.Devis;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DAODevis {
    public static long ajoutDevis(Devis devis) {
        // Document
        long Id = DAODocument.ajoutDocument(devis);
        devis.setId(Id);
        
        try{
            // Devis
            ConnectionBDD.creerConnection();
            String requete = "INSERT INTO Devis " +
                                "(dateDeFinDeValidite,graduationDeDemande,signe,montantTotalHT,remise,fraisDeTransport,tauxDeTva,montantTotalTTC,IdDocument,IdCommande) " +
                            "VALUES " +
                                "(?,?,?,?,?,?,?,?,?,NULL);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setTimestamp(1,new Timestamp(devis.getDateDeFinDeValidite().getTime()));
            preparedStatement.setInt(2,devis.getGraduationDeDemande());
            preparedStatement.setBoolean(3,devis.isSigne());
            preparedStatement.setDouble(4,devis.getMontantTotalHT());
            preparedStatement.setDouble(5,devis.getRemise());
            preparedStatement.setDouble(6,devis.getFraisDeTransport());
            preparedStatement.setDouble(7,devis.getTauxDeTva());
            preparedStatement.setDouble(8,devis.getMontantTotalTTC());
            preparedStatement.setLong(9,devis.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return Id;
    }

    public static void modificationDevis(Devis devis) {
        // Document
        DAODocument.modificationDocument(devis);
        
        try {
            // Devis
            ConnectionBDD.creerConnection();
            String requete = "UPDATE Devis " +
                            "SET " +
                                "dateDeFinDeValidite = ? " +
                                ",graduationDeDemande = ? " +
                                ",signe = ? " +
                                ",montantTotalHT = ? " +
                                ",remise = ? " +
                                ",fraisDeTransport = ? " +
                                ",tauxDeTva = ? " +
                                ",montantTotalTTC = ? " +
                            "WHERE IdDocument = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setTimestamp(1,new Timestamp(devis.getDateDeFinDeValidite().getTime()));
            preparedStatement.setInt(2,devis.getGraduationDeDemande());
            preparedStatement.setBoolean(3,devis.isSigne());
            preparedStatement.setDouble(4,devis.getMontantTotalHT());
            preparedStatement.setDouble(5,devis.getRemise());
            preparedStatement.setDouble(6,devis.getFraisDeTransport());
            preparedStatement.setDouble(7,devis.getTauxDeTva());
            preparedStatement.setDouble(8,devis.getMontantTotalTTC());
            preparedStatement.setLong(9,devis.getId());
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
    
    public static ArrayList<Devis> getListDevis(long IdCommercial) {
        ArrayList<Devis> devis = new ArrayList<Devis>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,graduationDeDemande,montantTotalHT,signe,remise,fraisDeTransport,tauxDeTva,montantTotalTTC,IdCommande " +
                            "FROM Document " +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id " +
                            "WHERE IdCommercial = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(3,IdCommercial);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Devis _devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    new Date(resultat.getTimestamp("dateDeFinDeValidite").getTime()),
                    resultat.getInt("graduationDeDemande"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("montantTotalHT"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getDouble("montantTotalTTC"),
                    resultat.getLong("IdCommande")
                );
                devis.add(_devis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return devis;
    }
    
    public static ArrayList<Devis> getListDevis() {
        ArrayList<Devis> devis = new ArrayList<Devis>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,graduationDeDemande,montantTotalHT,signe,remise,fraisDeTransport,tauxDeTva,montantTotalTTC,IdCommande " +
                            "FROM Document " +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Devis _devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    new Date(resultat.getTimestamp("dateDeFinDeValidite").getTime()),
                    resultat.getInt("graduationDeDemande"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("montantTotalHT"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getDouble("montantTotalTTC"),
                    resultat.getLong("IdCommande")
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
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,graduationDeDemande,montantTotalHT,signe,remise,fraisDeTransport,tauxDeTva,montantTotalTTC,IdCommande " +
                            "FROM Document " +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id " +
                            "WHERE Document.Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    new Date(resultat.getTimestamp("dateDeFinDeValidite").getTime()),
                    resultat.getInt("graduationDeDemande"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("montantTotalHT"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getDouble("montantTotalTTC"),
                    resultat.getLong("IdCommande")
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
            String requete = "SELECT Document.Id,dateDeCreation,reference,IdCommercial,IdContact,dateDeFinDeValidite,graduationDeDemande,montantTotalHT,signe,remise,fraisDeTransport,tauxDeTva,montantTotalTTC,IdCommande " +
                            "FROM Document " +
                            "INNER JOIN Devis ON Devis.IdDocument = Document.Id " +
                            "WHERE IdCommercial = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Devis _devis = new Devis(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    new Date(resultat.getTimestamp("dateDeCreation").getTime()),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact"),
                    new Date(resultat.getTimestamp("dateDeFinDeValidite").getTime()),
                    resultat.getInt("graduationDeDemande"),
                    resultat.getBoolean("signe"),
                    resultat.getDouble("montantTotalHT"),
                    resultat.getDouble("remise"),
                    resultat.getDouble("fraisDeTransport"),
                    resultat.getDouble("tauxDeTva"),
                    resultat.getDouble("montantTotalTTC"),
                    resultat.getLong("IdCommande")
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
