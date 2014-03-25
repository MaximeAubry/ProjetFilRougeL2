package com.plasprod.JDBC;

import com.plasprod.Models.LigneDeDocument;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOLigneDeDocument {
    public static void ajoutLigneDeDocument(LigneDeDocument ligneDeDocument) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO LigneDeDocument\n" +
                                "(numeroDeLigne,qte,remise,IdDocument,IdArticle)\n" +
                            "VALUES\n" +
                                "(?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getNumeroDeLigne());
            preparedStatement.setInt(2,ligneDeDocument.getQte());
            preparedStatement.setDouble(3,ligneDeDocument.getRemise());
            preparedStatement.setLong(4,ligneDeDocument.getIdDocument());
            preparedStatement.setLong(5,ligneDeDocument.getIdArticle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void modificationLigneDeDocument(LigneDeDocument ligneDeDocument) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getNumeroDeLigne());
            preparedStatement.setInt(2,ligneDeDocument.getQte());
            preparedStatement.setDouble(3,ligneDeDocument.getRemise());
            preparedStatement.setLong(4,ligneDeDocument.getIdDocument());
            preparedStatement.setLong(5,ligneDeDocument.getIdArticle());
            preparedStatement.setLong(6,ligneDeDocument.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void suppressionLigneDeDocument(LigneDeDocument ligneDeDocument) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE\n" +
                            "FROM LigneDeDocument\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<LigneDeDocument> getListLigneDeDocuments(long Id) {
        ArrayList<LigneDeDocument> ligneDeDocuments = new ArrayList<LigneDeDocument>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,numeroDeLigne,qte,remise,IdDocument,IdArticle\n" +
                            "FROM LigneDeDocument\n" +
                            "WHERE IdDocument = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                LigneDeDocument ligneDeDocument = new LigneDeDocument(
                    resultat.getLong("Id"),
                    resultat.getInt("numeroDeLigne"),
                    resultat.getInt("qte"),
                    resultat.getDouble("remise"),
                    resultat.getLong("IdDocument"),
                    resultat.getLong("IdArticle")
                );
                ligneDeDocuments.add(ligneDeDocument);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return ligneDeDocuments;
    }
    
    public static LigneDeDocument getLigneDeDocument(long Id) {
        LigneDeDocument ligneDeDocument = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,note,commentaire,dateLigneDeDocument,IdDocument,IdEvenement\n" +
                            "FROM LigneDeDocument\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                ligneDeDocument = new LigneDeDocument(
                    resultat.getLong("Id"),
                    resultat.getInt("numeroDeLigne"),
                    resultat.getInt("qte"),
                    resultat.getDouble("remise"),
                    resultat.getLong("IdDocument"),
                    resultat.getLong("IdArticle")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return ligneDeDocument;
    }
}
