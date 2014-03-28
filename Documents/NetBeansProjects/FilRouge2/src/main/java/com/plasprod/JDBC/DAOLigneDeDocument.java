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
            String requete = "INSERT INTO LigneDeDocument " +
                                "(numeroDeLigne,qte,prixUnitaire,remise,IdDocument,IdArticle) " +
                            "VALUES " +
                                "(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getNumeroDeLigne());
            preparedStatement.setInt(2,ligneDeDocument.getQte());
            preparedStatement.setDouble(3,ligneDeDocument.getPrixUnitaire());
            preparedStatement.setDouble(4,ligneDeDocument.getRemise());
            preparedStatement.setLong(5,ligneDeDocument.getIdDocument());
            preparedStatement.setLong(6,ligneDeDocument.getIdArticle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void modificationLigneDeDocument(LigneDeDocument ligneDeDocument) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE LigneDeDocument " +
                            "SET " +
                                "numeroDeLigne = ? " +
                                ",qte = ? " +
                                ",prixUnitaire = ? " +
                                ",remise = ? " +
                                ",IdArticle = ? " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getNumeroDeLigne());
            preparedStatement.setInt(2,ligneDeDocument.getQte());
            preparedStatement.setDouble(3,ligneDeDocument.getPrixUnitaire());
            preparedStatement.setDouble(4,ligneDeDocument.getRemise());
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
            String requete = "DELETE " +
                            "FROM LigneDeDocument " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,ligneDeDocument.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<LigneDeDocument> getListLignesDeDocument(long Id) {
        ArrayList<LigneDeDocument> lignesDeDocument = new ArrayList<LigneDeDocument>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,numeroDeLigne,qte,prixUnitaire,remise,IdDocument,IdArticle " +
                            "FROM LigneDeDocument " +
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
                    resultat.getDouble("prixUnitaire"),
                    resultat.getDouble("remise"),
                    resultat.getLong("IdDocument"),
                    resultat.getLong("IdArticle")
                );
                lignesDeDocument.add(ligneDeDocument);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return lignesDeDocument;
    }
    
    public static LigneDeDocument getLigneDeDocument(long Id) {
        LigneDeDocument ligneDeDocument = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,numeroDeLigne,qte,prixUnitaire,remise,IdDocument,IdArticle " +
                            "FROM LigneDeDocument " +
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
                    resultat.getDouble("prixUnitaire"),
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
